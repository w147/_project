const cheerio = require("cheerio");
const Http = require("./HttpUtil")
const url = "http://tieba.baidu.com/p/3205263090"

main()

async function main(){
    let binaryHtml = await Http.get(url)
    deal(binaryHtml.toString())
}

function deal(data){
    if (!data) {
        return []
    }
    let $ = cheerio.load(data)
    var jobs = [];
    $(".item_con_list").each(function(){   //对页面岗位栏信息进行处理  每个岗位对应一个 li  ,各标识符到页面进行分析得出
        var job = {};
        job.company = $(this).find(".hot_pos_r div").eq(1).find("a").html(); //公司名
        job.period = $(this).find(".hot_pos_r span").eq(1).html(); //阶段
        job.scale = $(this).find(".hot_pos_r span").eq(2).html(); //规模

        job.name = $(this).find(".hot_pos_l a").attr("title"); //岗位名
        job.src = $(this).find(".hot_pos_l a").attr("href"); //岗位链接
        job.city = $(this).find(".hot_pos_l .c9").html(); //岗位所在城市
        job.salary = $(this).find(".hot_pos_l span").eq(1).html(); //薪资
        job.exp = $(this).find(".hot_pos_l span").eq(2).html(); //岗位所需经验
        job.time = $(this).find(".hot_pos_l span").eq(5).html(); //发布时间

        console.log(job.name);  //控制台输出岗位名
        jobs.push(job);  
    });
    return jobs
}