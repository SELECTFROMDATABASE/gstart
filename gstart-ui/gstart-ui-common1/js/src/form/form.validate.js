var helper = require("../core/core.helper");
var formUtil = require("./form.util");
var $ = helper.jquery;
module.exports = {
    isEmpty : function(selector){
        if(!helper.isArr(selector)){
                var type = formUtil.getFormType(selector);
                switch (type){
                    case "textarea" : case "text" :
                        return $(selector).val() == "";
                        break;

                    case "radio" : case "checkbox" :
                        return $(selector).filter(":checked").length == 0;
                        break;

                    case "select" :
                        return $(selector).filter(":selected").length == 0;
                        break;

                    default :
                        throw new Error("selector '"+selector+"' is not a formType");
                        break;

                }
        }else{
            throw new Error("selector is Array ");
        }
    },
    must : function (selector,prop) {
        if(helper.isArr(selector)){
            var flag = 0;
            for(var i in selector){
                if(this.isEmpty($(selector[i]))) flag = 1;
            }
            console.log(flag);
            if (flag == 1){
                console.log(2222);
                if(helper.isFunction(prop.fn)) {
                    prop.fn.call();
                    return false;
                }
            }else{
                return true;
            }
        }else{
            if(this.isEmpty(selector)){
                if(helper.isFunction(prop.fn)) {
                    prop.fn.call();
                    return false;
                }
                else throw new Error("'fn' is not a function");
            }else{
                return true;
            }
        }

    }
}