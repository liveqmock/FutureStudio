define([
    'core',
    'jquery',
    'underscore',
    'backbone',
    './manage/CustomerListView',
    './detail/index',
    'text!./manage/assets/CustomerManage.html'
], function (core, $, _, Backbone,
             CustomerListView,
             CustomerDetail,
             tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template: _.template(tpl,null,{variable:"args"}),
        initialize: function () {
            this.eb= _.extend({},Backbone.Events);
            this.listView = new CustomerListView({
                eb:this.eb
            });
            this.listenTo(this.eb,"Customer:Click",function(model){
                this.openDetailTab(model);
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.listView.render();
            this.$(".list").html(this.listView.el);
            return this;
        },
        openDetailTab:function(model){
            var tabContent = new CustomerDetail({
                id:model.get("id")
            }).render();
            core.trigger("tab:open",{
                name:"["+model.get("name")+"]详细信息",
                view:tabContent
            });
        },
        unRender:function(){
            this.listView.unRender();
            this.remove();
        }
    });
});