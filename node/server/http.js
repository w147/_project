var http = require("http");
var url = require("url");
var fs = require("fs");
var path = require("path");
var MIME_TYPE = {
    "css": "text/css",
    "gif": "image/gif",
    "html": "text/html",
    "ico": "image/x-icon",
    "jpeg": "image/jpeg",
    "jpg": "image/jpeg",
    "js": "text/javascript",
    "json": "application/json",
    "pdf": "application/pdf",
    "png": "image/png",
    "svg": "image/svg+xml",
    "swf": "application/x-shockwave-flash",
    "tiff": "image/tiff",
    "txt": "text/plain",
    "wav": "audio/x-wav",
    "wma": "audio/x-ms-wma",
    "wmv": "video/x-ms-wmv",
    "xml": "text/xml"
};

http.createServer(onRequest).listen(8888);

function onRequest(request, response) {
    var pathname = url.parse(request.url).pathname;
    var filePath;
    console.info(__dirname)
    request.setEncoding("utf8");
    filePath = `${__dirname}/../${url.parse(request.url).pathname}`;
    fs.exists(filePath, function (err) {
        if (!err) {
            response.writeHead(404, { 'content-type': 'text/plain' });
            response.write('The Resourse ' + pathname + ' was Not Found!');
            response.end();
        } else {//文件存在
            //获取文件的扩展名称，如果没有返回" "
            var ext = path.extname(filePath);
            //如果扩展名称为空，设置扩展名称为unknown
            ext = ext ? ext.slice(1) : 'unknown';
            //根据请求文件的扩展名称，设置请求的类型contentType
            var contentType = MIME_TYPE[ext] || "text/html";
            console.log(filePath);
            //因为有图片，默认读取文件是以utf8读取的，获取不到图片，需要读文件是和返回文件时都用binary编码，不然图片不能正常显示
            fs.readFile(filePath, "binary", function (err, data) {
                if (err) {
                    response.end("<h1>500</h1>服务器内部错误！");
                } else {//返回不同的页面
                    response.writeHead(200, { 'content-type': contentType });
                    response.end(data.toString(), "binary");
                }
            });
        }
    });
}