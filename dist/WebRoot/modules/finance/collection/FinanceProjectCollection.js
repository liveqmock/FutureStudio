define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"/fs/finance/projectQuery_projectsList.action",
        parse:function(response){
            this.count=parseInt(response.count,10);
            this.countPage=parseInt(response.countPage,10);
            return response.financeProjects;
        },
        comparator:function(m){
            return m.get("flowId")*-1;
        }
    });
});