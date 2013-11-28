define([
    "jquery",
    "underscore",
    "backbone",
    "./item"
], function ($, _, Backbone, Item) {
    "use strict";
    return Backbone.View.extend({
        tagName:"ul",
        className:"list-group",
        initialize:function(options){
            this.collection = options.collection;
            this.eb = options.eb;
        },
        render: function () {
            this.eb.trigger("ListGroup:Item:Destroy");
            var frag = document.createDocumentFragment();
            this.collection.each(function(m){
                frag.appendChild(new Item({
                    model:m,
                    eb:this.eb
                }).render().el);
            },this);
            this.$el.append(frag);
            return this;
        }
    });
});