define([
    "core",
    "jquery",
    "underscore",
    "backbone",
    "./model/FinanceProjectModel",
    "text!./tpl/FinanceProjectDetail.html",
    "./return-plan-list/index",
    "modules/message/index"
], function (core, $, _, Backbone, Model, tpl, ReturnPlanList, Message) {
    "use strict";

    var BID_RETURNED = 20, //客户已完成还款
        BID_RETURNING = 10, //客户还款中
        BID_SUCCESS = 5, //招满结束
        BIDDING = 1,  //投标中
        BID_CLOSED = 0;//投标关闭
    return Backbone.View.extend({
        tagName:"div",
        className:"",
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .button-over": "shutdownProject",
            "click .button-close":"closeProject",
            "click .button-open": "openProject",
            "click .button-returning":"returningProject",
            "click .button-returned":"returnedProject",
            "click .button-return-plan":"returnPlan"
        },
        initialize: function () {
            this.detailModel = new Model();
            this.listenTo(this.detailModel,"change",this.loaded);
        },
        loaded:function(){
            this.$el.html(this.template(this.detailModel.toJSON()));
        },
        fetch:function(){
            this.detailModel.fetch({
                data:{
                    id:this.model.get("id")
                }
            });
        },
        changeStatus:function(status){
            $.ajax({
                url:"/fs/finance/projectManage_updateStatus.action",
                type:"post",
                data:{
                    id:this.model.get("id"),
                    statusCode:status
                },
                beforeSend:function(){

                },
                success:function(res){
                    Message.success({
                        body:res.message
                    });
                },
                error:function(res){
                    var title = jQuery.parseJSON(res.responseText);
                    Message.error({
                        body:title.message
                    });
                },
                complete:function(){

                }

            });
        },
        returnPlan:function(){
            var view = new ReturnPlanList({
                model:this.model,
                projectId:this.model.get("id")
            });
            view.render();
            core.trigger("Tab:open",{
                id:"return-plan"+core.getCount(),
                name:'项目还款计划',
                view:view
            });

        },
        returningProject:function(){
            this.changeStatus(BID_RETURNING);
        },
        returnedProject:function(){
            this.changeStatus(BID_RETURNED);
        },
        shutdownProject:function(){
            this.changeStatus(BID_CLOSED);
        },
        closeProject:function(){
            this.changeStatus(BID_SUCCESS);
        },
        openProject:function(){
            this.changeStatus(BIDDING);
        },
        render: function () {
            this.fetch();
            return this;
        },
        unRender:function(){
            this.remove();
        }
    });
});