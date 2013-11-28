define([
    'jquery',
    'underscore',
    'backbone',
    '../model/DepartmentModel'
],function($, _, Backbone, DepartmentModel){
    "use strict";
    return Backbone.Collection.extend({
        model:DepartmentModel,
        url:"oaAccount_findPositionAccount.action",
        parse:function(response){
            return response.accounts;
        },
        comparator:function(model){
            return model.get("pinyin");
        }
    });
});
