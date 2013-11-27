//流程的历史任务集合
define([
    'jquery',
    'underscore',
    'backbone',
    './TaskHistoryView',
    'modules/base/collection/BaseCollection',
    'text!../tpl/TaskHistoryList.html'
],function($,_,Backbone, TaskHistoryView, collection, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"instance-history-task",
        template: _.template(tpl),
        initialize:function(){
            var that=this;
            this.collection=new collection();
            this.collection.url="workFlowQuery_findInstanceHistoryTask.action";
            this.collection.parse=function(response){
                return response.tasks;
            };
            this.collection.comparator=function(m){
                return m.get("createTime");
            };
            this.collection.on("reset",function(){
                that.load();
            });
        },
        load:function(){
            var that=this;
            if(this.collection.length===0){
                this.$el.html("");
            }else{
                this.$el.html(this.template(tpl));
                this.collection.each(function(m){
                    that.$("tbody").append(new TaskHistoryView({
                        model:m
                    }));
                });
            }

        },
        fetch:function(){
            var instanceId =  this.model.get("instanceId");
            if(instanceId){
                this.collection.fetch({
                    data:{
                        id:instanceId
                    }
                });
            }
        },
        render:function(){
            return this;
        }
    });
});