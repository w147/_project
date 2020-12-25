var mysql = require('mysql2');
var conf = require("../config");
var DBR = mysql.createPool(conf.yxtr).promise();
var DBW = mysql.createPool(conf.yxtw).promise();

module.exports = {
    DBR,
    DBW,
};