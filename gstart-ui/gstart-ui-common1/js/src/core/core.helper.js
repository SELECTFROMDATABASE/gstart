module.exports = {
    isArr : function (param) {
        return Object.prototype.toString.call(param) === "[object Array]";
    },
    isFunction : function (param) {
        if (param == null) return false;
        return typeof param == "function";
    },
    jquery : require("jquery")
}