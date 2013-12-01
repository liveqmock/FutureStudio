define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './VocationHistoryItemView',
    'modules/base/collection/BaseCollection',
    'text!../tpl/VocationHistoryList.html'
],function($, _, Backbone, core, VocationHistoryItemView, BaseCollection, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"vocation-history-list",
        template: _.template(tpl),
        initialize:function(){
            var that=this;
            this.collection=new BaseCollection();//存放历史请假信息
            this.collection.url="oaVocation_findUserVocationHistory.action";
            this.collection.parse=function(response){
                return response.vocations;
            };
            this.collection.on("reset",function(){
                that.loadHisotry();
            },this);
        },
        loadHisotry:function(){
            var that=this;
            this.collection.each(function(m){
                that.$("tbody").append(new VocationHistoryItemView({
                    model:m
                }).render().el);
            });
        },
        fetch:function(data){
            this.collection.fetch({
                data:data
            });
        },
        render:function(){
            return this;
        }
    });
});