/** User:Tony   Date: 4/6/13 - 2:49 PM  业务流程审批view*/
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/TaskExamine.html',
    'modules/base/collection/BaseCollection',
    'bootstrap'
], function ($, _, Backbone, core, tpl) {
    "use strict";
    //审批view需要外部提供，taskid，然后查询流出选项,外部传入model
    //参数名：transacs
    //获取审批流向 getTransaction
    //获取审批意见 getText
    return Backbone.View.extend({
        tagName:"div",
        template:_.template(tpl),
        className:"business-task-container",
        initialize:function(){
            _.bindAll(this,"getTransaction","getText");
        },
        fetch:function(){
            var that=this;
            $.ajax({
                url:"workFlowQuery_findTaskTrans.action",
                type:"get",
                data:{
                    id:this.model.get("id")
                },
                success:function(response){
                    if(response.transitions){
                        for(var i=0;i<response.transitions.length;i=i+1){
                            that.$(".transaction-group").append(' <button type="button" class="btn-default btn">'+response.transitions[i]+'</button>&nbsp');
                        }
                        that.$(".transaction-group button").button();
                        that.$(".transaction-group button:first").addClass("active");
                    }
                },
                error:function(){

                }
            });
        },
        render:function(){
            this.$el.html(this.template());
            this.fetch();
            return this;
        },
        unrender:function(){
            this.undelegateEvents();
            this.remove();
        },
        getTransaction:function(){
            return this.$(".transaction-group .active").html();
        },
        getText:function(){
            return this.$(".examine-textarea").val();
        }
    });
});
