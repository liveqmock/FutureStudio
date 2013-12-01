/**
 * User: Tony
 * Date: 13-4-6 --  下午4:30
 * 这里显示的是我待处理的任务
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!./tpl/MyTaskList.html',
    'modules/base/collection/BaseCollection',
    './view/TaskView'
], function ($, _, Backbone, core, tpl, BaseCollection, TaskView) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template:_.template(tpl),
        className:"business-my-task",
        events:{
            "input .search-query":"search",
            "click .refresh-button":"refresh"
        },
        initialize:function(){
            var that=this;
            _.bindAll(this,"search","refresh");
            this.collection=new BaseCollection();
            this.collection.url="workFlowQuery_myTask.action";//TODO
            this.eb=_.extend({},Backbone.Events);
            this.collection.parse=function(response){
                return response.tasks;
            };
            this.collection.comparator=function(model){
                return model.get("createTime");
            };
            this.collection.on("reset",function(){
                that.loaded();
            });
        },
        refresh:function(){
            this.fetch();
        },
        search:function(){
            this.eb.trigger("Task:Search",this.$(".search-query").val());
        },
        fetch:function(){
            this.collection.fetch();
        },
        loaded:function(){
            var that=this;
            this.eb.trigger("Task:Remove");
            this.$(".info").html("共有"+this.collection.length+"个待办任务");
            this.collection.each(function(model){
                that.$(".list-body").append(new TaskView({
                    model:model,
                    eb:that.eb
                }).render().el);
            });
        },
        render:function(){
            this.$el.html(this.template());
            this.fetch();
            return this;
        },
        unRender:function(){
            this.undelegateEvents();
            this.eb.trigger("Task:Remove");
            this.remove();
        }
    });
});