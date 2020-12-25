const cheerio = require("cheerio")

module.exports = {
    deal: function (data){
        if (!data) {
            return []
        }
        let $ = cheerio.load(data)
        let i_nodes = $("img")
        let imgs = []
        i_nodes.each(function(i, e) {
            imgs.push($(e).attr("src"))
        });
        return imgs
    },

}
