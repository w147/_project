module.exports = {
    get: function (url){
        return new Promise(function(a,r){
            let http = require("http");
            if(url.includes("https")){
                http = require("https")
            }
            try{
                let req = http.get(url, function (res) {  
                    let bufs = [];
                    res.on('data',function(d){
                        bufs.push(d)
                    })
                    res.on('end', function(){
                        a(Buffer.concat(bufs));
                    });
                });
                req.on('error', (e) => {
                    r(e);
                });
                req.end();
            }catch (error) {
                console.info(url)
                console.error(error)
                a();
            }
        })
    }
}