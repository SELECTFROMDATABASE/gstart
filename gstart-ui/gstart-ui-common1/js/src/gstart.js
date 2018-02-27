function init() {

    var gstart = require("./core/core");

    require("./form/form")(gstart);
    require("./util/util")(gstart);
    return gstart
}
if(window.GSTART == undefined){
    var gstart = init();
    window.GSTART = gstart;
}