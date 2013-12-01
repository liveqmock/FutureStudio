//这是一个加载通知公告信息的view
define([
    'jquery',
    'underscore',
    'backbone',
    'modules/notification/MyNotificationView'
],function($, _, Backbone, MyNotificationView){
    "use strict";
    return Backbone.View.extend({
        id:"div",
        initialize:function(){

        },
        render:function(){
            this.$el.append(new MyNotificationView().render().el);
            return this;
        },
        close:function(){
            this.undelegateEvents();
            this.remove();
        },
        removeClose:function(){
        }
    });
});