define([

],function(){
    "use strict";
    var util={
        findImageFileUrl:function(id){
            return 'imageUnfilter_image.action?id="+'+id+'+".jpg';
        }
    };
    return util;
});
