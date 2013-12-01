define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"knowledgeQuery_allKnowledge.action",
        parse:function(response){
            this.page=parseInt(response.page,10);
            this.pageSize=parseInt(response.pageSize,10);
            this.count=parseInt(response.count,10);
            this.countPage=parseInt(response.countPage,10);
            return response.knowledges;
        }
    });
});