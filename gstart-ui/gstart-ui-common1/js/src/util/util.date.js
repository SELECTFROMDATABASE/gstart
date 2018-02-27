var helper = require("../core/core.helper");
var $ = helper.jquery;
Object.prototype
function init(){
    Date.prototype.mills2Second = function (millis){
        return Math.floor(millis/1000);
    }
    Date.prototype.mills2Minute = function (millis){
        return Math.floor(this.mills2Second(millis)/60);
    }
    Date.prototype.mills2Hours = function (millis){
        return Math.floor(this.mills2Minute(millis)/60);
    }
    Date.prototype.mills2Days =function (millis){
        return Math.floor(this.mills2Hours(millis)/24);
    }
    Date.prototype.mills2Month =function (millis){
        return Math.floor(this.mills2Days(millis)/30);
    }
    Date.prototype.format = function(format)
    {
        var o =
            {
                "M+" : this.getMonth()+1, //month
                "d+" : this.getDate(),    //day
                "h+" : this.getHours(),   //hour
                "H+" : this.getHours(),   //hour
                "m+" : this.getMinutes(), //minute
                "s+" : this.getSeconds(), //second
                "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
                "S" : this.getMilliseconds() //millisecond
            };
        if(/(y+)/.test(format))
            format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o){
            if(new RegExp("("+ k +")").test(format)){
                format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
            }
        }
        return format;
    };
}
init();
module.exports = {
    addDays:function (strdate,strdays)
    {
        var d = strdate.replace(/-/g,'/');
        var now = new Date(d);
        var values = new Date(Date.parse(now) + (86400000 * strdays));
        var strt= values.format("yyyy-MM-dd");
        return strt;
    },
    isLater:function (startDate,endDate){
        if(!!startDate)
            startDate = new Date(startDate.replace(/-/g,'/'));
        if(!!endDate)
            endDate = new Date(endDate.replace(/-/g,'/'));
        if(!!startDate&&!!endDate){
            var days = Date.parse(endDate) - Date.parse(startDate);
            return days > 0;
        }
    },
    getDaySAndHoursBetween : function (start,end){
        var start = start.replace(/-/g,'/');
        var end = end.replace(/-/g,'/');
        var times = new Date(end) - new Date(start);
        return times;
    },
    betweenMills2Format : function (format) {
        //多个单位
        var years = Math.floor(format.time / 1000 / 60 / 60 / 24 / 30 / 12);
        var month = Math.floor(format.time / 1000 / 60 / 60 / 24 / 30) - years * 12;
        var days = Math.floor(format.time / 1000 / 60 / 60 / 24) - years * 12 * 30 - month * 30;
        var hours = Math.floor(format.time / 1000 / 60 / 60) - years * 12 * 30 *24 - month * 30 * 24 - days * 24;
        var minute = Math.floor(format.time / 1000 / 60) - years * 12 * 30 * 24 * 60 - month * 30 * 24 * 60 - days * 24 * 60 - hours * 60;
        var second = Math.floor(format.time / 1000 ) - years * 12 * 30 * 24 * 60 * 60 - month * 30 * 24 * 60 * 60 - days * 24 * 60 * 60 - hours * 60 * 60 - minute * 60;
        if(format.format == undefined){
            throw new Error("Unexcept format Type");
        }
        //用一个单位
        if (format.type == "single" || format.type == undefined) {
            var dateString = format.format == undefined ? '': format.format;
            if (format.format) {
                if (format.format.indexOf("yyyy") >= 0) return dateString.replace("yyyy", years);
                if (format.format.indexOf("MM") >= 0) return dateString.replace("MM", month + years * 12);
                if (format.format.indexOf("dd") >= 0) return dateString.replace("dd", years * 12 * 30 + month * 30 + days);
                if (format.format.indexOf("HH") >= 0) return dateString.replace("HH", years * 12 * 30 *24 + month * 30 * 24 + days * 24 + hours);
                if (format.format.indexOf("mm") >= 0) return dateString.replace("mm", years * 12 * 30 *24 * 60 + month * 30 * 24 * 60 + days * 24 * 60 + hours * 60 + minute);
                if (format.format.indexOf("ss") >= 0) return dateString.replace("ss", years * 12 * 30 * 24 * 60 * 60 + month * 30 * 24 * 60 * 60 + days * 24 * 60 * 60 + hours * 60 * 60 + minute * 60);
            }
        }else if (format.type == "complex") {
            //用多个单位
            var dateString = format.format == undefined ? '': format.format;
            if (format.format) {
                if (format.format.indexOf("yyyy") >= 0) dateString = dateString.replace("yyyy", years);
                if (format.format.indexOf("MM") >= 0) dateString = dateString.replace("MM", month);
                if (format.format.indexOf("dd") >= 0) dateString = dateString.replace("dd", days);
                if (format.format.indexOf("HH") >= 0) dateString = dateString.replace("HH", hours);
                if (format.format.indexOf("mm") >= 0) dateString = dateString.replace("mm", minute);
                if (format.format.indexOf("ss") >= 0) dateString = dateString.replace("ss", second);
            }
        }
        return dateString;
    }
}