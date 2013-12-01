define([
    'jquery',
    'underscore',
    'backbone'
],function($, _, Backbone){
    "use strict";
    return Backbone.Model.extend({
        url:"/fs/finance/projectQuery_projectDetail.action",
        parse:function(response){
            return response.financeProject;
        }
    });
});