var helper = require("../core/core.helper");
var $ = helper.jquery;
module.exports = {
    getFormType : function (object) {
        var a = object;
        if (!(object instanceof jQuery)){
            a = $(a);
        }
        return $(a)[0].type;
    }
}