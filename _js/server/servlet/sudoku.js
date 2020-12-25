const service = require("../service/sudokuService")

function test(ctx){
    ctx.response.body = 'Hello World';
}

function main(ctx){
    let data = ctx.request.body.d
    service.init(data)
    service.crack()
    let res = service.dealTmp()
    ctx.response.body = res
}

function tip(ctx){
    let data = ctx.request.body.d
    service.init(data)
    let res = service.tip()
    ctx.response.body = res
}

module.exports = [
    ["/main", main],
    ["/test", test],
    ["/tip", tip],
]