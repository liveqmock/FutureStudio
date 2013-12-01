/** User:Tony   Date: 3/23/13 - 4:49 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    '../model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        comparator:function(model){
            return model.get("priority");
        }
    });
});