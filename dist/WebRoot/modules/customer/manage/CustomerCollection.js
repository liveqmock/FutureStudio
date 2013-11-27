define([
    'jquery',
    'underscore',
    'backbone',
    '../model/Customer'
],function($, _, Backbone, Customer){
    "use strict";
    return Backbone.Collection.extend({
        model:Customer,
        url:"/fs/customer/customerQuery_list.action",
        parse:function(response){
            this.count = response.count;
            this.page = response.page;
            this.pageSize = response.pageSize;
            return response.customers;
        },
        comparator:function(model){
            return model.get("accountName");
        }
    });
});