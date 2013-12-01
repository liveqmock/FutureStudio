define([
    "jquery",
    "underscore",
    "backbone",
    "./model",
    "text!./index.html"
], function ($, _, Backbone, Model, tpl) {
    "use strict";
    return Backbone.View.extend({
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .btn-edit":"edit",
            "click .btn-cancel":"cancel",
            "click .btn-save":"save"
        },
        initialize:function(options){
            this.model = new Model();
            this.model.set("id",options.id);
            this.listenTo(this.model,"change",this.loaded);
        },
        render: function () {
            this.fetch();
            return this;
        },
        edit:function(){

        },
        cancel:function(){

        },
        save:function(){

        },
        fetch:function(){
            this.model.fetch({
                data:{
                    id:this.model.get("id")
                }
            });
        },
        loaded:function(){
            this.$el.html(this.template(this.model.toJSON()));
        },
        unRender:function(){
            this.remove();
        }
    });
});