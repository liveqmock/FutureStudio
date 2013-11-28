define([
    "jquery",
    "underscore",
    "backbone",
    "text!./tpl/item.html"
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .btn-delete":"delete",
            "click .btn-income":"income"
        },
        initialize:function(options){
            this.eb = options.eb;

            this.listenTo(this.eb,"Item:Destroy",this.unRender);
        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        income:function(){
            var that=this;
            $.ajax({
                url:"/fs/finance/ProjectIncome/manage_addIncomeByPlan.action",
                dataType:"json",
                type:"post",
                data:{
                    id:this.model.get("id")
                },
                success:function(res){
                    alert(res.message);
                },
                error:function(res){
                    alert(JSON.parse(res.responseText).message);
                }
            });
        },
        delete:function(){
            var that=this;
            $.ajax({
                url:"/fs/finance/ProjectReturnPlan/manage_delete.action",
                dataType:"json",
                type:"post",
                data:{
                    id:this.model.get("id")
                },
                success:function(res){
                    that.eb.trigger("Item:Delete");
                    that.unRender();
                },
                error:function(res){

                }
            });
        },
        unRender:function(){
            this.remove();
        }
    });
});