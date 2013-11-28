define([
    "jquery",
    "core",
    "underscore",
    "backbone",
    "./view/FinanceProjectListView",
    "./FinanceProjectDetailView",
    "./FinanceProjectAddView",
    "css!./finance.css"
], function ($, core, _, Backbone, FinanceProjectListView, FinanceProjectDetailView, FinanceProjectAddView) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"finance-project-manage",
        initialize: function () {
            var that=this;
            this.eb= _.extend({},Backbone.Events);
            this.eb.on("FinanceProject:Detail",function(m){
                that.openDetail(m);
            },this);
            this.eb.on("Finance:Add",function(){
                that.openAdd();
            },this);
            this.list=new FinanceProjectListView({
                eb:this.eb
            });
        },
        openDetail:function(m){
            var detail = new FinanceProjectDetailView({
                model:m
            });
            detail.render();
            core.trigger("Tab:open",{
                id:"detail"+core.getCount(),
                name:'理财项目明细',
                view:detail
            });
        },
        openAdd:function(){
            var detail = new FinanceProjectAddView();
            detail.render();
            core.trigger("Tab:open",{
                id:"detail"+core.getCount(),
                name:'新增理财项目',
                view:detail
            });
        },
        render: function () {
            this.list.render();
            this.$el.html(this.list.el);
            return this;
        },
        unRender:function(){
            this.eb.off();
            this.remove();
        }
    });
});