define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"workFlowQuery_findUserRequest.action",
        parse:function(response){
            this.totalPage=response.totalPage;
            return response.processInstances;
        },
        comparator:function(model){
            return model.get("startDate");
        }
    });
});