/** User:Tony   Date: 4/5/13 - 9:24 PM */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!./tpl/ceshi.html'
],function($,_,Backbone,core,tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        className:"business-ceshi-ceshiview",
        events:{
            "click .save-button":"save"
        },
        initialize:function(){
            _.bindAll(this,"save");
        },
        save:function(){
            $.ajax({
                url:"workflow_addTest.action",
                type:"post",
                data:{
                    definitionKey:"Oa_CeShi"
                },
                success:function(response){

                }
            });
        },
        render:function(){
            this.$el.html(this.template());
            return this;
        }
    });
});