define([
    "jquery",
    "underscore",
    "backbone"
], function ($, _, Backbone) {
    "use strict";
    return Backbone.View.extend({
        tagName:"li",
        className:"list-group-item",
        initialize:function(options){
            this.eb = options.eb;
            this.listenTo(this.eb,"ListGroup:Item:Destroy",this.unRender);
        },
        render: function () {
            this.$el.html(this.model.get("name"));
            return this;
        },
        click:function(){
            this.eb.trigger("ListGroup:Click",this.model);
        },
        unRender:function(){
            this.close();
        }
    });
});