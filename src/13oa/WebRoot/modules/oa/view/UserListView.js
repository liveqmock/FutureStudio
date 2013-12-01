define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './UserView',
    '../collection/UserCollection',
    'text!modules/base/tpl/table.html'
],function($, _, Backbone, core, UserView, UserCollection, tableTpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"privilege-user-list",
        template:_.template(tableTpl),
        initialize:function(options){
            this.eb = options.eb;
            this.positionId = options.positionId;
            this.collection = new UserCollection();
            this.collection.on("reset",this.loaded,this);
        },
        render:function(){
            this.$el.html(this.template());
            this.collection.fetch({
                data:{
                    id:this.positionId
                }
            });
            return this;
        },
        loaded:function(){
            var that = this;
            this.$el.html(this.template());
            that.$("table").addClass("table-striped table-bordered");
            this.collection.each(function(model){
                that.$("table").append(new UserView({
                model :model,
                eb:that.eb
                }).render().el);
            });
        }
    });
});