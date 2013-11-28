define([
    'jquery',
    'underscore',
    'backbone',
    'modules/base/model/BaseModel'
],function($, _, Backbone, Modal){
    "use strict";
    return Backbone.Collection.extend({
        model:Modal,
        url:"",
        parse:function(response){
            return response.files;
        },
        comparator:function(m){
            return m.get("pinyin");
        }
    });
});