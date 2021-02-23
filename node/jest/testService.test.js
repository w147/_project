describe("testService test suit", () => {
    require('./setup')
    const Org = require('../mysql/org');
    jest.mock('../mysql/org')
    const ZuoyeDB = require("../mongo/model/nzuoye")
    jest.mock("../mongo/model/nzuoye")
    const MQ = require("../mq/homeWorkMq")
    jest.mock("../mq/homeWorkMq")
    const orgInfoCache = require('../cache/orgCache')
    jest.mock('../cache/orgCache')
    const JsinternalRpc = require("../rpc/jsinternal")
    jest.mock('../rpc/jsinternal')
    const testService = require("../service/testService");

    test("replaceTitle", async () => {
        const result = await testService.replaceTitle('星期二')
        let s = "星期" + "日一二三四五六".charAt(new Date().getDay());
        return expect(result).toBe(s)
    })
    test("mock mongo", async () => {
        ZuoyeDB.find.mockResolvedValue({'test': 'ttt'})
        const result = await testService.queryMongo({groupId: 123})
        return expect(result).toEqual({"test": "ttt"})
    })
    test("mock mysql", async () => {
        Org.getOrgShortName.mockResolvedValue('testname')
        const result = await testService.queryMysql('123')
        return expect(result).toBe('testname')
    })
    test("mock mq", async () => {
        MQ.timingHomeWork.mockResolvedValue('OK')
        const result = await testService.testMQ(1,1)
        return expect(result).toBe('OK')
    })
    test("mock redis", async () => {
        let val = {orgId:1,orgName:'testOrg'}
        orgInfoCache.getInfo.mockResolvedValue(val)
        const result = await testService.queryRedis(1,1)
        return expect(result).toEqual(val)
    })
    test("mock RPC", async () => {
        JsinternalRpc.getDetail.mockResolvedValue('OK')
        const result = await testService.testRPC(1)
        return expect(result).toBe('OK')
    })

});
