const cheerio = require("cheerio")
const fs = require("fs")
const Http = require("./HttpUtil")
// const url = "http://www.plaso.cn"
const url = "https://www.lagou.com/jobs/list_%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91?kd=%E5%89%8D%E7%AB%AF%E5%BC%80%E5%8F%91&spc=1&pl=&gj=&xl=&yx=&gx=&st=&labelWords=label&lc=&workAddress=&city=%E5%85%A8%E5%9B%BD&requestId=&pn=1"
const localDir = "D:/Temp/picture/"

main()

async function main(){
    let binaryHtml = await Http.get(url)
    let imgUrls = deal(binaryHtml.toString())
    for(let uimgUrl of imgUrls){
        try{
            let fileName = /.*\/([\w.-]{1,}).*/.exec(uimgUrl)[1]
            let binaryImg = await request(uimgUrl)
            fs.writeFileSync(localDir + fileName, binaryImg)
        }catch(e){
            console.error(uimgUrl)
            continue
        }
    }
    console.info(`download ${imgUrls.length} pictures`)
}

function deal(data){
    if (!data) {
        return []
    }
    let $ = cheerio.load(data)
    let i_nodes = $("img")
    let imgs = []
    i_nodes.each(function(i, e) {
        imgs.push($(e).attr("src"))
    });
    // let v_nodes = $("a")
    // let videos = []
    // v_nodes.each(function(i, e) {
    //     let url = $(e).attr("href")
    //     if(url && url.includes(".mp4")){
    //         videos.push(url)
    //     }
    //     console.info(url)
    // })
    return imgs
}