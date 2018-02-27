var express = require('express');
var router = express.Router();
var request = require('sync-request');
/* GET home page. */
router.get('/', function(req, res, next) {
  var response = request('GET','http://localhost:9090/test/a');
  console.log(response);
  res.render('index', { title: response.getBody().toString()});
});

module.exports = router;
