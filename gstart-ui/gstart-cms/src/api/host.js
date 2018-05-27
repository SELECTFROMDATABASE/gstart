module.exports.default = module.exports = {
    protrol : 'http://',
    host:'localhost',
    port:'33333',
    prefix : function(){
      return this.protrol + this.host + ":" + this.port
    }
  }
