const fs = require("fs")

let caches = {}
let content = fs.readFileSync("C:\\Users\\Administrator\\Desktop\\account_gray-2021-02-22.log")
content = content.toString()
let lines = content.split("\n")
for(let line of lines){
    if(!line.includes("exit")){
        continue
    }
    let p = line.split(" ")
    let key = p[4]
    let time = getMS(p[5])
    caches[key] = (caches[key] || 0) + time
}
let total = 0
for(let key in caches){
    let val = parseInt(caches[key])
    total += val
    fs.appendFileSync("C:\\Users\\Administrator\\Desktop\\temp.log", `${val}   ${key}   \n`)
}
console.info(total)
fs.appendFileSync("C:\\Users\\Administrator\\Desktop\\temp.log", `total: ${total}`)

function getMS(str){
    str = str.trim()
    str = str.substr(1, str.length - 2)
    let ms = 0
    if(str.includes("ms")){
        let t1 = str.split("ms")
        ms = parseFloat(t1[0])
    }else if(str.includes("m") && str.includes("s")){
        let t1 = str.split("m")
        let min = parseInt(t1[0])
        let t2 = t1[1].split("s")
        let sec = parseFloat(t2[0])
        ms = min * 60000 + sec * 1000
    }else if(str.includes("m")){
        let t1 = str.split("m")
        let min = parseInt(t1[0])
        let sec = parseFloat(t1[1])
        ms = min * 60000 + sec * 1000
    }else if(str.includes("s")){
        let t1 = str.split("s")
        let sec = parseFloat(t1[0])
        ms = sec * 1000
    }else{
        console.error(str)
    }
    return ms
}