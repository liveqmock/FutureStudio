define([
    "jquery",
    "underscore",
    "backbone",
    "core",
    "text!../tpl/NotificationItem.html"
], function ($, _, Backbone, core, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"tr",
        template: _.template(tpl),
        events:{
            "click .read-button":"read"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;
            this.eb.on("NotificationItem:Destroy",function(){
                that.unRender();
            },this);
        },
        read:function(){
            var that=this;
            $.ajax({
                url:"/fs/notification/manage_readNotification.action",
                data:{
                    id:this.model.get("id")
                },
                success:function(response){
                    core.trigger("Message:Info","成功",response.message);
                    that.unRender();
                },
                error:function(response){
                    core.trigger("Message:Error","失败",jQuery.parseJSON(response.responseText).message);
                }
            });

        },
        render: function () {
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        unRender:function(){
            this.eb.off(null,null,this);
            this.undelegateEvents();
            this.remove();
        }
    });
});