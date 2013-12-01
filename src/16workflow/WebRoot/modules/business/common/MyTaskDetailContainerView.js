/**
 * User: Tony
 * Date: 13-4-6 --  下午4:30
 * 人工任务交互，容器view
 * 装载任务信息，任务涉及到的表单，任务流向button
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './view/TaskExamineView',
    './view/InstanceHistoryTaskListView',
    'text!./tpl/TaskContainer.html'
], function ($, _, Backbone, core, TaskExamineView,InstanceHistoryTaskListView,tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template:_.template(tpl),
        className:"business-task-container",
        events:{
            "click .cancel":"closeTask",
            "click .save":"submitTask"
        },
        initialize:function(){
            //var that=this;
            _.bindAll(this,"closeTask","submitTask");
            this.eb=_.extend({},Backbone.Events);
        },
        submitTask:function(){
            var that=this;
            //TODO
            if(this.examine){
                var transition=this.examine.getTransaction();
                var comments=this.examine.getText();
                $.ajax({
                    url:"workFlow_completeTask.action",
                    type:"post",
                    data:{
                        id:this.model.get("id"),
                        transition:transition,
                        comments:comments
                    },
                    success:function(response){
                        core.trigger("Message:Info","成功提交",response.message);
                        that.closeTask();
                    },
                    error:function(response){
                        core.trigger("Message:Error","失败",response.responseText);
                    }
                });
            }else{
                //TODO 这里是特殊表单的提交方式
            }
        },
        closeTask:function(){
            //TODO 向下发送remove事件，向上主动关闭容器
            var aId = this.$el.parent().attr("id");
            $("#"+aId+"-a").find(".close").trigger("click");
            this.unRender();
        },
        //需要加载的form的信息，当前位置。i从0开始
        requireFormSync:function(forms,i){
            var that=this;
            if(forms.length>i){
                var item=forms[i];
                var listItem=item.split(":");
                require([listItem[0]],function(View){
                    var view = new View({
                        businessId:listItem[1]
                    });
                    view.render();
                    that.$(".task-container-form").append(view.el);
                    i++;
                    that.requireFormSync(forms,i);
                });
            }
        },
        fetchForm:function(){
            var forms = this.model.get("forms");
            if(forms && forms.length>1){
                var listForms = forms.split(",");
                this.requireFormSync(listForms,0);
            }else{
                this.$(".task-container-form").html("");
            }
        },
        historyTask:function(){
            this.$(".task-container-history").html(new InstanceHistoryTaskListView({
                model:this.model
            }).render().el);
        },
        render:function(){
            this.$el.html(this.template(this.model.toJSON()));
            //获取历史任务信息 TODO
            this.historyTask();
            //获取已有表单信息
            this.fetchForm();
            //如果是特殊表单
            var formResource=this.model.get("formResourceName");
            if(formResource && formResource.length>1){
                this.$(".task-container-button").hide();
                //装载特殊view  这个view的开发规则是？
            }else{
                //不是特殊表单,装载审批view
                this.examine=new TaskExamineView({
                    model:this.model,
                    eb:this.eb
                });
                this.examine.render();
                this.$(".task-container-examine").append(this.examine.el);
            }
            return this;
        },
        unRender:function(){
            this.undelegateEvents();
            this.remove();
            this.eb.off();
        }
    });
});