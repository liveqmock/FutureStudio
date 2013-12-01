/**
 * Author：漆尧
 * Date: 4/1/13
 * Time: 4:49 PM
 * 显示请假申请的详细信息
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/base/model/BaseModel',
    'text!../tpl/VocationDetail.html'
],function($, _, Backbone, core, Model, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        className:"vocation-detail",
        initialize:function(options){
            this.businessId = options.businessId;
        },
        loadRequest:function(){
            var that=this;
            $.ajax({
                url:"oaVocation_vocationRequestDetail.action",
                dataType:"json",
                data:{
                    id:this.businessId
                },
                success:function(response){
                    that.$el.html(that.template(new Model(response.pvocation).toJSON()));
                },
                error:function(response){
                    var m=jQuery.parseJSON(response.responseText);
                     core.trigger("Message:Error","加载请假信息出错", m.message);
                }
            });
        },
        render:function(){
            this.loadRequest();
            return this;
        },
        unRender:function(){
            this.remove();
        }
    });
});