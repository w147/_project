const ZuoyeDB = require("../mongo/model/nzuoye")
const STATUS = require("../definition/js/macro/zuoye/Status")
const Org = require("../mysql/org");
const Log = require("../support/log")(__filename)
const MQ = require("../mq/homeWorkMq")
const orgInfoCache = require('../cache/orgCache')
const JsinternalRpc = require("../rpc/jsinternal")



function replaceTitle(title){
    if(!title) return title
    let s = "星期" + "日一二三四五六".charAt(new Date().getDay());
    for (let item of [/星期一/,/星期二/,/星期三/,/星期四/,/星期五/,/星期六/,/星期日/,/星期天/]){
        title = title.replace(item,s)
    }
    Log.info(title)
    return title
}

async function queryMongo(content){
    let {groupId} = content
    let groupAllZuoye = await ZuoyeDB.find({groupIds: groupId, status:{$nin: [STATUS.CHEXIAO, STATUS.DELETE]}})
    return groupAllZuoye
}
async function queryMysql(orgId) {
    orgId = parseInt(orgId)
    let oemName = (await Org.getOrgShortName(orgId));
    return oemName;
}
async function testMQ(zuoyeId, timing) {
    return MQ.timingHomeWork(zuoyeId,timing)
}
async function queryRedis(orgId, defaultVal) {
    return orgInfoCache.getInfo(orgId, defaultVal)
}
async function testRPC(token) {
    return JsinternalRpc.getDetail(token)
}
module.exports = {
    replaceTitle,
    queryMongo,
    queryMysql,
    testMQ,
    queryRedis,
    testRPC,
}
