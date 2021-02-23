const config = require("../config")
const redis = require("redis")
const client = redis.createClient(config.redis)

const {promisify} = require("util")
const get = promisify(client.get).bind(client);
const set = promisify(client.set).bind(client);
const end = promisify(client.end).bind(client);
const expire = promisify(client.expire).bind(client);
const keys = promisify(client.keys).bind(client);
const scan = promisify(client.scan).bind(client);
const del = promisify(client.del).bind(client);

module.exports = {
    get: async function(key){
        await get(key)
    },
    set: async function(key, value){
        await set(key, value)
    },
    end: async function(){
        await end()
    },
    expire: async function(){
        await expire(key,3)
    },
    keys: async function(key){
        return await keys(key)
    },
    scan: async function(){
        return await scan()
    },
    del: async function(key){
        await del(key)
    },
}