var xlsx = require('node-xlsx');

// 解析得到文档中的所有 sheet
var sheets = xlsx.parse('D:/Temp/各种资料/das_2021.csv');

// 遍历 sheet
sheets.forEach(function(sheet){
    console.log(sheet['name']);
    // 读取每行内容
    for(var rowId in sheet['data']){
        console.log(rowId);
        var row=sheet['data'][rowId];
        console.log(row);
    }
});