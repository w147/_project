const liveClassDB = require("../dao/LiveClassDB")
const Cache = require('../cache/liveclass')

async function getLcByName(ctx) {
    let {name} = ctx.query || ctx.request.body
    let lc = await liveClassDB.getLcIdByName(name)
    ctx.response.body = {code: 0, obj: lc}
}

async function updateLc(ctx) {
    let {id} = ctx.query || ctx.request.body
    await Cache.delLiveInfo(id)
    let res = await liveClassDB.updateLc(id)
    ctx.response.body =  {code: 0, obj: res}
}

module.exports = [
    ["/nc/getLcByName", getLcByName],
    ["/nc/updateLc", updateLc],
]