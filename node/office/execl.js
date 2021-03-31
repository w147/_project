const xlsx = require('node-xlsx');
const sheets = xlsx.parse('D:/types1.xlsx')
const fs = require('fs')

let set = new Set(); 
// 遍历 sheet
sheets.forEach(function(sheet){
    for(let rowId in sheet['data']){
        if(rowId == 0) continue
        let row = sheet['data'][rowId]
        let [appId, name, suject] = row
    }
});