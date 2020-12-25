const {DBR, DBW} = require("../config/connection")

module.exports = {
    getLcIdByName: async function (name) {
        let sql = `SELECT l.id, l.topic, l.createAt, l.vendorType FROM \`liveclass\` l WHERE l.topic = ? `
        let res = await DBR.query(sql, [name]);
        return res[0]
    },
    updateLc: async function (id) {
        let sql = `UPDATE liveclass SET vendorType = 3 WHERE id = ?`
        let res = await DBW.query(sql, [id])
        return res[0]
    },
}