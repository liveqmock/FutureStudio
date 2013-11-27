define([
    'jquery',
    'underscore',
    'backbone',
    '../model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        fsParse:function(res){
            return res.items;
        },
        parse:function(res){
            this.page = res.page;
            this.count = res.count;
            this.pageSize = res.pageSize;

            return this.fsParse(res);
        }
    });
});