define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!./tpl/WorkFLowDeploy.html'
],function($,_,Backbone,core, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"workflow-deploy",
        template: _.template(tpl),
        events:{
            "click .deploy-button":"deploy"
        },
        initialize:function(){
            _.bindAll(this,"deploy");
        },
        deploy:function(){
            var that=this;
            $.ajax({
                url:"workFlow_deployDefinition.action",
                data:{
                    definitionFileName:this.$(".definition").val()
                },
                success:function(response){
                    that.$(".info").html(response.message);
                },
                error:function(response){
                    that.$(".info").html(response.responseText);
                }
            });
        },
        unrender:function(){
            this.undelegateEvents();
            this.remove();
        },
        render:function(){
            this.$el.html(this.template());
            return this;
        }
    });
});