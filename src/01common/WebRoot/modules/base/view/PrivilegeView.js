/** User:Tony   Date: 1/29/13 - 8:38 PM */
define([
    'jquery',
    'core',
    'modules/base/collection/BaseCollection'
],function($, core, BaseCollection){
    "use strict";
    var privilege = _.extend({},{
        initialize:function(){
            var that = this,
                ps;
            this.collection = new BaseCollection();
//            for(var p in core.sysInfo.privileges){
//                this.collection.add({
//                    id: p.id
//                })
//            }

        },
        hasRight:function(id){
            if(this.collection.get(id)){
                return true;
            }else{
                return false;
            }
        }

    });
    privilege.initialize();
    return privilege;
});
