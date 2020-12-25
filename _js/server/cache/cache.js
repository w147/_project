'use strict'
const util = require('util');
const redis = require('redis');
const flatten = require('flat');
const config = require('../config');

/**
 * 对 redis 客户端的简单封装, 使用 redis 进行数据缓存.
 */
class Cache {
    /**
     * 构造函数.
     *
     * @param options - 初始化参数, 参阅 Cache.DEFAULT_OPTIONS 声明.
     */
    constructor(options) {
        this._options = Object.assign({}, Cache.DEFAULT_OPTIONS);
        this._options = Object.assign(this._options, config.redis || {});
        this._options = Object.assign(this._options, options || {});

        // 创建 redis 客户端.
        this._redis = redis.createClient({
            host: this._options.host,
            password: this._options.password,
            db: this._options.db,
            retry_strategy: (options) => {
                if (options.error && options.error.code === 'ECONNREFUSED') {
                    return new Error('The server refused the connection');
                }
                if (options.total_retry_time > 1000 * 60 * 60) {
                    return new Error('Retry time exhausted');
                }
                if (options.attempt > 100) {
                    return undefined;
                }
                return Math.min(options.attempt * 100, 3000);
            }
        });

        // 绑定 redis 客户端操 (命令) 作函数.
        [
            'scan', 'del', 'set', 'get', 'getset', 'expire', 'incr',
            'hget', 'hgetall', 'hmset', 'hdel',
            'mget', 'mset', 'pexpire',
            'lpush', 'lrange', "exists"
        ].forEach((method) => {
            this[`_redis_${method}`] = util.promisify(this._redis[method]).bind(this._redis);
        });

        // 默认常量.
        this.KEY = {
            LC_USER_COUNT: 'liveclass:userCount:'
        };

        this.TIMEOUT = {
            DEFAULT: 24 * 60 * 60,
            HALFHOUR: 30 * 60,
            ONEHOUT: 60 * 60,
            MINUTES: 60,
        };
    }

    /**
     * 构造一个新的 Cache 实例.
     *
     * @param options  - Cache 对象的初始化参数, 参阅 Cache 构造函数.
     * @return {Cache} - 返回一个新的 Cache 对象.
     */
    newInstance(options) {
        return new Cache(options);
    }

    /**
     * 返回匹配的所有 KEY.
     *
     * @param pattern - 支持 * 的正则表达式, 如果为空, 则返回所有的 KEY.
     * @return {Promise<*>}
     */
    async keys(pattern) {
        let cursor = 0;
        let data = []
        let result = [];

        do {
            [cursor, result] = await this['_redis_scan'](cursor, 'match', pattern || '*');
            data = data.concat(result)
        } while (cursor != 0)

        return data;
    }

    /**
     * 删除对应的 KEY.
     *
     * @param key - 需要删除的 KEY.
     * @return {Promise<void>}
     */
    async del(key) {
        return await this['_redis_del'](key);
    }

    /**
     * 将对象转成 JSON 字符串保存到 redis 中.
     *
     * @param key    - 保存数据的 KEY.
     * @param value  - 数据, 被 JSON.stringify() 转换.
     * @param expire - 过期时间, 如果 0 或未指定则不设置.
     * @return {Promise<void>}
     */
    async setJson(key, value, expire) {
        if (typeof value !== 'string') {
            value = JSON.stringify(value);
        }

        let result = await this['_redis_set'](key, value);
        if (!!expire) {
            await this['_redis_expire'](key, expire);
        }

        return result;
    }

    /**
     * 获取 redis 中的值, 并将其转换 (JSON.parse()) 成对象.
     *
     * @param key - 要查询的 KEY.
     * @return {Promise<any>}
     */
    async getJson(key) {
        return JSON.parse(await this['_redis_get'](key));
    }

    /**
     * 将值保存到 redis 中.
     *
     * @param key    - 保存字使用的 KEY.
     * @param value  - 要保存的值.
     * @param expire - 过期时间, 如果 0 或未指定则不设置.
     * @return {Promise<void>}
     */
    async set(key, value, expire) {
        let result = await this['_redis_set'](key, value);
        if (!!expire) {
            await this['_redis_expire'](key, expire);
        }

        return result;
    }

    async getset(key, value, expire) {
        let result = await this['_redis_getset'](key, value);
        if (!!expire) {
            await this['_redis_expire'](key, expire);
        }

        return result;
    }

    /**
     * 返回 redis 中 KEY 对应的值.
     *
     * @param key - 要查询的 KEY.
     * @return {Promise<void>}
     */
    async get(key) {
        return await this['_redis_get'](key);
    }

    /**
     * 向 redis 中保存一个对象, 在写之前, 将其进行扁平化处理.
     *
     * @param key    - 保存对象使用的 KEY.
     * @param value  - 要保存的对象.
     * @param expire - 过期时间, 如果 0 或未指定则不设置.
     * @return {Promise<void>}
     */
    async setObj(key, value, expire) {
        await this['_redis_del'](key);

        let result = await this['_redis_hmset'](key, flatten(value));
        if (!!expire) {
            await this['_redis_expire'](key, expire);
        }

        return result;
    }

    /**
     * 从 redis 中读取一个对象.
     *
     * @param key       - 要查询的 KEY.
     * @param generator - 对象生成器, 如果 key 没有查到数据, 则调用该函数来生成一个对象,
     *                    并将生成的对象存储到 redis 中.
     * @param expire    - 过期时间, 如果 0 或未指定则不设置.
     * @return {Promise<*>}
     */
    async getObj(key, generator, expire) {
        let result = await this['_redis_hgetall'](key);
        if (!result && typeof generator === 'function') {
            result = generator();
            if (!result) {
                return;
            }

            this.setObj(key, result, expire);
        }

        return flatten.unflatten(result);
    }

    /**
     * 将 redis 中的某个整型数值递增.
     *
     * @param key    - 整型数值存储的 KEY.
     * @param expire - 过期时间, 如果 0 或未指定则不设置.
     * @return {Promise<void>}
     */
    async incr(key, expire) {
        let result = await this['_redis_incr'](key);

        if (!!expire) {
            await this['_redis_expire'](key, expire);
        }

        return result;
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param keys
     * @return {Promise<void>}
     */
    async mget(keys) {
        return await this['_redis_mget'](keys);
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param infos
     * @return {Promise<void>}
     */
    async mset(infos) {
        return await this['_redis_mset'](infos);
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key
     * @param value
     * @return {Promise<void>}
     */
    async lpush(key, value) {
        return await this['_redis_lpush'](key, value);
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key
     * @param start
     * @param stop
     * @return {Promise<void>}
     */
    async lrange(key, start, stop) {
        return await this['_redis_lrange'](key, start, stop);
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key1
     * @param key2
     * @param value
     * @param expire
     * @return {Promise<void>}
     */
    async hmset(key1, key2, value, expire) {
        let result = await this['_redis_hmset'](key1, key2, value);

        if (expire) {
            await this['_redis_expire'](key1, expire);
        }

        return result;
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key1
     * @param key2
     * @return {Promise<void>}
     */
    async hget(key1, key2) {
        return await this['_redis_hget'](key1, key2);
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key1
     * @param key2
     * @return {Promise<void>}
     */
    async hdel(key1, key2) {
        return await this['_redis_hdel'](key1, key2);
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key
     * @param expireT 单位秒
     * @return {Promise<void>}
     */
    async expire(key, expireT) {
        if (expireT) {
            return await this['_redis_expire'](key, expireT);
        }
    }

    /**
     * 参阅 redis 操作手册.
     *
     * @param key
     * @param ms 单位毫秒
     * @return {Promise<void>}
     */
    async pexpire(key, ms) {
        return await this['_redis_pexpire'](key, ms)
    }

    /**
     * 参阅 redis 操作手册.
     * key不存在时设置value，存在则不操作返回null
     * @param key
     * @param value
     * @param expire 单位秒
     * @returns {Promise<*>}
     */
    async setNx(key, value, expire) {
        if (typeof value !== 'string') {
            value = JSON.stringify(value);
        }
        let result;
        if (expire) {
            result = await this['_redis_set'](key, value, 'EX', expire, 'NX');
        } else {
            result = await this['_redis_set'](key, value, 'NX');
        }

        return result;
    }

    async exists(key) {
        let result = await this['_redis_exists'](key);
        return result === 1;
    }
}

/**
 * Cache 对象构造器默认参数.
 */
Cache.DEFAULT_OPTIONS = {
    host: 'localhost',
    port: 6379,
    password: 'plaso',
    db: 1
};

module.exports = new Cache({});