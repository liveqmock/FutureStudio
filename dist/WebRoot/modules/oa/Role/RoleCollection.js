/** User:Tony   Date: 3/23/13 - 4:49 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        //可接受参数：userId
        model:Modal,
        url:"/fs/oa/roleQuery_list.action",

        comparator:function(model){
            return model.get("priority");
        },
        parse:function(response){
            return response.roles;
        }
    });
});