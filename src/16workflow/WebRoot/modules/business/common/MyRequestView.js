/**
 * 我的申请-- 这里显示的是，我提交的，正在流转的申请
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './view/MyRequestItemView',
    './collection/UserRequestCollection',
    'text!./tpl/MyRequest.html'
], function ($, _, Backbone, core, MyRequestItemView, MyRequestCollection, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(tpl),
        className:"my-request",
        events:{
            "click .pre-button":"prePage",
            "click .next-button":"nextPage",
            "click .refresh-button":"refresh"
        },
        initialize:function(){
            var that=this;
            _.bindAll(this,"prePage","nextPage","refresh");
            this.collection=new MyRequestCollection();
            this.collection.on("reset",function(){
                that.loaded();
            });
            this.eb= _.extend({},Backbone.Events);
            this.pageSize=20;
            this.page=1;
        },
        prePage:function(){
            if(this.page===1){
                return;
            }else{
                this.page=this.page-1;
                this.collection.fetch({
                    data:{
                        page:this.page,
                        pageSize:this.pageSize
                    }
                });
            }
        },
        nextPage:function(){
            if(parseInt(this.collection.totalPage,10)>this.page){
                this.page=this.page+1;
                this.collection.fetch({
                    data:{
                        page:this.page,
                        pageSize:this.pageSize
                    }
                });
            }else{
                return;
            }
        },
        refresh:function(){
            this.collection.fetch({
                data:{
                    page:this.page,
                    pageSize:this.pageSize
                }
            });
        },
        loaded:function(){
            var that=this;
            this.eb.trigger("RequestItem:Remove");
            this.$(".page-button").addClass("hide");
            if(parseInt(this.collection.totalPage,10)>this.page){
                this.$(".next-button").removeClass("hide");
            }
            if(this.page>1 && parseInt(this.collection.totalPage,10)>1){
                this.$(".pre-button").removeClass("hide");
            }
            this.collection.each(function(m){
                that.$(".data-tbody").append(new MyRequestItemView({
                    model:m,
                    eb:that.eb
                }).render().el);
            });
        },
        render:function(){
            this.collection.fetch({
                data:{
                    page:this.page,
                    pageSize:this.pageSize
                }
            });
            this.$el.html(this.template());
            return this;
        }
    });
});