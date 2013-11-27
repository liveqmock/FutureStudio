define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"",
        parse:function(response){
            return response.tasks;
        },
        comparator:function(model){
            return model.get("starttime");
        }
    });
});