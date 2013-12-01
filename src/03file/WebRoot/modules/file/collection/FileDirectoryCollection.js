define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"file_findDirectory.action",
        parse:function(response){
            return response.fileDirectories;
        },
        comparator:function(m){
            return m.get("priority");
        }
    });
});