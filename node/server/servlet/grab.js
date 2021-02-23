const http = require("../util/HttpUtil")
const service = require("../service/grabService")

async function getPicture(ctx){
    let {url} = ctx.request.body
    let binaryHtml = await http.get(url)
    let imgUrls = service.deal(binaryHtml.toString())
    ctx.response.body = {code: 0, url: imgUrls}
}

module.exports = [
    ["/getPicture", getPicture],
]