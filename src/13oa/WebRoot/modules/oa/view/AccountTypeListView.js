//这里显示的所有账户类型
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './AccountTypeView',
    './AccountTypeEditView',
    'modules/base/collection/PriorityCollection',
    'text!../tpl/account-type-list.html'
], function ($, _, Backbone,core, AccountTypeView, AccountTypeEditView, PriorityCollection, tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName: "div",
        template: _.template(tpl),
        events:{
            "click .botton-add":"add"
        },
        initialize: function (options) {
            var that=this;
            this.eb = options.eb;

            this.collection=new PriorityCollection();
            this.collection.url="oaAccount_findAllAccountType.action";
            this.collection.parse=function(response){
                return response.accountType;
            };
            this.collection.on("reset",function(){
                that.loaded();
            });
            this.eb.on("AccountType:Refresh",function(){
                that.render();
            });
            this.eb.on("AccountTYpe:MoveUp",function(m){
                that.moveUp(m);
            });
            this.eb.on("AccountTYpe:MoveDown",function(m){
                that.moveDown(m);
            });
        },
        moveUp:function(m){
            var io=this.collection.indexOf(m),
                m2;
            if(io===0){
                core.trigger("Message:Warning","警告","已经是最高优先级，不能再提升");
            }else{
                m2=this.collection.at(io+1);
                if(m2){
                    this.move(m,m2);
                }
            }
        },
        moveDown:function(m){
            var io=this.collection.indexOf(m),
                m2;
            if(io===this.collection.length-1){
                core.trigger("Message:Warning","警告","已经是最低优先级，不能再降低");
            }else{
                m2=this.collection.at(io-1);
                this.move(m,m2);
            }
        },
        move:function(m1,m2){
            var that=this;
            $.ajax({
                url:"oaAccount_switchAccountTypePriority.action",
                type:"post",
                data:{
                    id1:m1.get("id"),
                    id2:m2.get("id")
                },
                success:function(response){
                    core.trigger("Message:Info","成功",response.message);
                    that.eb.trigger("AccountType:Refresh");
                },
                error:function(response){
                    core.trigger("Message:Error","出错",response.responseText);
                }
            });
        },
        add:function(){
            new AccountTypeEditView({
                eb:this.eb,
                isNew:true,
                priority:parseInt(this.collection.at(this.collection.length-1).get("priority"),10)+1000
            }).render();
        },
        loaded:function(){
            var that=this;
            this.$("tbody").html("");
            this.collection.each(function(m){
                that.$("tbody").append(new AccountTypeView({
                    model:m,
                    eb:that.eb,
                    index:that.collection.indexOf(m),
                    length:that.collection.length
                }).render().el);
            });
        },
        render: function () {
            this.$el.html(this.template());
            this.collection.fetch();
            return this;
        }
    });
});