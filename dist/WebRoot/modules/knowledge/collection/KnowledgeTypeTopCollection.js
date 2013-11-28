define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"knowledgeTypeQuery_findAllKnowledgeTypeTop.action",
        parse:function(response){
            return response.knowledgeTypes;
        },
        comparator:function(m){
            return m.get("priority");
        }
    });
});