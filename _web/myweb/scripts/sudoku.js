const baseUrl = "/game/sudoku/"

let b1 = document.getElementById('b1');
let b2 = document.getElementById('b2');
let b3 = document.getElementById('b3');

b1.onclick = async function() {
    let datas = getDatas()
    let res = await sendPost(datas, "main")
    for(let y = 1; y<=9; y++){
        let rows = res[y-1]
        for(let x = 1; x<=9; x++){
            if(rows[x-1] == 0) continue
            let id = `n_${y}_${x}`
            let n = document.getElementById(id);
            n.innerText = rows[x-1]
        }
    }
}

b2.onclick = function() {
    location.reload() 
}

b3.onclick = async function() {
    let datas = getDatas()
    let res = await sendPost(datas, "tip")
    let {x=1,y=9,v=99} = res
    let id = `n_${y}_${x}`
    let obj = document.getElementById(id)
    obj.innerText = v
    obj.style.backgroundColor = "red"
}

function sendPost(datas, url){
    return new Promise((a, r) => {
        //第一步：创建需要的对象
        let httpRequest = new XMLHttpRequest();
        //第二步：打开连接
        httpRequest.open('POST', baseUrl+url, true);
        //设置请求头 注：post方式必须设置请求头（在建立连接后设置请求头）
        httpRequest.setRequestHeader("content-type","application/json");
        //发送请求 将情头体写在send中
        httpRequest.send(`{"d": ${JSON.stringify(datas)}}`);
        // 获取数据后的处理程序
        //请求后的回调接口，可将请求成功后要执行的程序写在其中
        httpRequest.onreadystatechange = function () {
            //验证请求是否发送成功
            if (httpRequest.readyState == 4 && httpRequest.status == 200) {
                //获取到服务端返回的数据
                let res = httpRequest.responseText;
                let json = JSON.parse(res)
                return a(json)
            }
        };
    })
}

function getDatas(){
    let datas = []
    for(let y = 1; y<=9; y++){
        let rows = []
        for(let x = 1; x<=9; x++){
            let id = `n_${y}_${x}`
            let n = document.getElementById(id);
            rows.push(parseInt(n.innerText || 0))
        }
        datas.push(rows)
    }
    return datas
}