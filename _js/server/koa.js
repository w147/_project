
const Koa = require('koa')
const Router = require('koa-router')
const static = require('koa-static');
const bodyParser = require('koa-bodyparser')

const app = new Koa()
const router = Router()

app.use(static("D:/GitLab/_myproject/web"));
app.use(bodyParser())
app.use(async function(ctx, next){
    console.log(`Process ${ctx.request.method} ${ctx.request.url} `);
    await next()
})

const servlets = [
    ["sudoku", require("./servlet/sudoku")],
    ["grab", require("./servlet/grab")],
    ["liveclass", require("./servlet/liveclass")],
]

init(app, servlets)

app.listen(8003)

function init(app){
    for(let servlet of servlets){
        let [module, methods] = servlet
        for(let method of methods){
            router.post(`/${module}${method[0]}`, method[1])
            router.get(`/${module}${method[0]}`, method[1])
        }
    }
    app.use(router.routes())
}
