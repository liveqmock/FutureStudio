define([
    'jquery',
    'underscore',
    'backbone',
    'text!./tpl/MailManage.html'
], function ($, _, Backbone, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        className:"mail-manage-view",
        template: _.template(tpl),
        events:{
            "click .send-mail":"sendMail"
        },
        sendMail:function(){
            $.ajax({
                url:"mailManage_sendMail.action",
                success:function(response){
                    console.log(response);
                }
            });
        },
        initialize: function () {

        },
        render: function () {
            this.$el.html(this.template());
            return this;
        },
        unRender: function () {
            this.undelegateEvents();
            this.remove();
        }
    });
});