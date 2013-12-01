define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"/fs/notification/query_findUserNotification.action",
        parse:function(response){
            return response.sysNotifications;
        },
        comparator:function(model){
            return model.get("submitDate");
        }
    });
});