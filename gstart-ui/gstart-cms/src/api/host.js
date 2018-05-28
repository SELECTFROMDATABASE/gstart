module.exports.default = module.exports = {
    protrol : 'http://',
    host:'localhost',
    manage : function(){
      return this.protrol + this.host + ":" + "8889"
    },
    login : function () {
      return this.protrol + this.host + ":" + "33333";
    }
  }
