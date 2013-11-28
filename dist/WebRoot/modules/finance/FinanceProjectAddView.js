define([
    "jquery",
    "underscore",
    "backbone"
], function ($, _, Backbone) {
    "use strict";
    return Backbone.View.extend({
        initialize: function () {
            this.eb = _.extend({},Backbone.Events);
        },
        render: function () {
            return this;
        },
        unRender:function(){
            this.remove();
        }
    });
});