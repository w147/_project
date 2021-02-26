const xlsx = require('node-xlsx');
const sheets = xlsx.parse('D:/Temp/各种资料/das_2021.csv')
const fs = require('fs')

let set = new Set(); 
// 遍历 sheet
sheets.forEach(function(sheet){
    for(let rowId in sheet['data']){
        if(rowId == 0) continue
        let row=sheet['data'][rowId]
        if(!row[1].includes("nzuoyes") && !row[2].includes("nzuoyes")){
            continue
        }
        set.add(row[2].replace(/[0-9]{1,20}/g,`0`))
    }
    
    let cache = Array.from(set)
    cache.sort()
    let str = cache.join("\r\n")
    fs.writeFileSync('D:/tmp.txt', str)
    // for(let d of cache){
    //     fs.appendFileSync('D:/tmp.txt', d)
    //     fs.appendFileSync('D:/tmp.txt', "\r\n")
    // }
});