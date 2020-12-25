const fs = require("fs")
const cheerio = require('cheerio');

let content = fs.readFileSync("./index.html")

var $ = cheerio.load(content); //cheerio模块开始处理 DOM处理

let tmp = $("h3")
tmp.each(function (e) {
    console.log(e);
});