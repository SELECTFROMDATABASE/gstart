module.exports.default = module.exports = {
    protrol : 'http://',
    host:'localhost',
    port:'8080',
    manage : function(){
      return this.protrol + this.host + ":" + this.port + '/cms/apis'
    },
    login : function () {
      return this.protrol + this.host + ":" + this.port + '/upms/apis';
    }
  }
