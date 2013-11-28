define([
    "jquery",
    "underscore",
    "backbone",
    "./view/KnowledgeTypeTopListView",
    "./view/KnowledgeTypeChildrenListView",
    "./view/KnowledgeListView",
    "./view/KnowledgeAddView",
    "./view/KnowledgeModifyView",
    "text!modules/base/tpl/fluid228.html"
], function ($, _, Backbone,
             KnowledgeTypeTopListView,
             KnowledgeTypeChildrenListView,
             KnowledgeListView,
             KnowledgeAddView,
             KnowledgeModifyView,
             tpl) {
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"knowledge-manage",
        template: _.template(tpl),
        initialize: function () {
            var that=this;
            this.eb= _.extend({},Backbone.Events);
            this.eb.on("TopType:Select",function(model){
                that.loadTopChildren(model);
            },this);
            this.eb.on("ChildType:Select",function(model){
                that.loadList(model);
            },this);
            this.eb.on("Knowledge:Add",function(knowledgeType){
                this.addKnowledge(knowledgeType);
            },this);
            this.eb.on("Knowledge:Add:Over",function(){
                that.temp.unRender();
                that.$(".span8").html(that.right.el);
                that.right.loadList();
            },this);
            this.eb.on("Knowledge:Modify",function(model){
                that.modifyKnowledge(model);
            },this);
            this.eb.on("Knowledge:Modify:Over",function(){
                that.$(".span8").html(that.right.el);
                that.right.loadList();
            },this);
            this.eb.on("Knowledge:Modify:Cancel",function(){
                that.$(".span8").html(that.right.el);
            },this);
            this.left=new KnowledgeTypeTopListView({
                eb:this.eb
            });
            this.left.render();
            this.leftChild=null;
            this.right=null;
        },
        refreshRightList:function(){
            if(this.right){
                this.$(".span8").html(this.right.el);
                this.right.refresh();
            }
        },
        render: function () {
            this.$el.html(this.template());
            this.$(".span2a").html(this.left.el);
            return this;
        },
        loadTopChildren:function(m){
            if(this.leftChild){
                this.leftChild.unRender();
            }
            this.leftChild=new KnowledgeTypeChildrenListView({
                eb:this.eb
            }).render();
            this.$(".span2b").html(this.leftChild.el);
            this.leftChild.loadTopChildren(m);
        },
        loadList:function(m){
            if(this.right){
                this.right.unRender();
            }
            this.right=new KnowledgeListView({
                eb:this.eb
            });
            this.right.render();
            this.$(".span8").html(this.right.el);
            this.right.loadList(m);
        },
        unRender:function(){
            this.eb.trigger("All:Destroy");
            this.eb.off(null,null,this);
            this.remove();
            this.left=null;
            this.leftChild=null;
            this.right=null;
        },
        addKnowledge:function(knowledgeType){
            if(this.temp){
                this.temp.unRender();
            }
            this.temp=new KnowledgeAddView({
                knowledgeType:knowledgeType,
                eb:this.eb
            }).render();
            this.detachRight();
            this.$(".span8").html(this.temp.el);
        },
        detachRight:function(){
            if(this.right){
                this.right.$el.detach();
            }
        },
        modifyKnowledge:function(model){
            if(this.temp){
                this.temp.unRender();
            }
            this.temp=new KnowledgeModifyView({
                model:model,
                eb:this.eb
            }).render();
            this.detachRight();
            this.$(".span8").html(this.temp.el);
        }
    });
});