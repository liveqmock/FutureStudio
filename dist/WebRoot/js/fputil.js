define([

],function(){
    "use strict";
    var util={
        dateToString:function(d){
            var k="";
            var year= d.getFullYear();
            var month= (d.getMonth()+1);
            if(month<10){
                month="0"+month;
            }
            var day= d.getDate();
            if(day<10){
                day="0"+day;
            }
            var hour= d.getHours();
            if(hour<10){
                hour="0"+hour;
            }
            var mins= d.getMinutes();
            if(mins<10){
                mins="0"+mins;
            }
            k=year+"-"+month+"-"+day+"-"+hour+"-"+mins;
            return k;
        },
        stringToDate:function(s){
            var year=parseInt(s.substring(0,4),10);
            var mon=parseInt(s.substring(5,7),10)-1;
            var day=parseInt(s.substring(8,10),10);
            var hour=parseInt(s.substring(11,13),10);
            var min=parseInt(s.substring(14,16),10);
            var sec=parseInt(s.substring(17,19),10);
            if(isNaN(year)){
                year=2013;
            }
            if(isNaN(mon)){
                mon=1;
            }
            if(isNaN(day)){
                day=1;
            }
            if(isNaN(hour)){
                hour=0;
            }
            if(isNaN(min)){
                min=0;
            }
            if(isNaN(sec)){
                sec=0;
            }
            var d = new Date();
            d.setYear(year);
            d.setMonth(mon);
            d.setDate(day);
            d.setHours(hour);
            d.setMinutes(min);
            d.setSeconds(sec);
            return d;
        }
    };
    return util;
});