var Cache = require('./cache')

const KEY = {
    LC_INFO: 'liveclass:info:',
}


module.exports = {
    delLiveInfo: async function (lcIds) {
        if (!Array.isArray(lcIds)) {
            lcIds = [lcIds]
        }
        let keys = []
        for (let lcId of lcIds) {
            keys.push(`${KEY.LC_INFO}${lcId}`)
        }
        await Cache.del(keys)
    },
}