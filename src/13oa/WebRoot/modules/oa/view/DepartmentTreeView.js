//部门tree view，对外暴露几个接口。
//点击叶子结点
//重置叶子结点信息
//通过EB进行回调：Department:Click
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    '../collection/DepartmentCollection',
    'modules/base/model/BaseModel',
    'text!../tpl/departmenttree.html',
    'jquery.ztree'
],function($, _, Backbone, core, DepartmentCollection, BaseModel, deptTree){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"privilege-dept-tree",
        template:_.template(deptTree),
        initialize:function(options){
            var that = this;
            this.eb = options.eb;
            this.treeOption = options.treeOption;
            this.dataOption = options.dataOption;

            this.collection = new DepartmentCollection();
            this.treeOption=this.treeOption;
            this.dataOption=this.dataOption?this.dataOption:{
                nocheck:-1
            };
            this.setting={
                callback:{
                    onClick:function(event, treeId, treeNode, clickFlag){
                        that.eb.trigger("Department:Click",new BaseModel(treeNode));
                    }
                }
            };
            if(this.treeOption.type!=="none"){
                this.setting.check={
                    enable:true
                };
                if(this.treeOption.type==="radio"){
                    this.setting.check.chkStyle="radio";
                    this.setting.check.radioType="all";
                    this.setting.callback.onCheck=function(event,treeId,treeNode){
                        this.checkedRadio=new BaseModel(treeNode);
                    };
                }else{
                    this.setting.check.chkStyle="checkbox";
                }
            }
            this.collection.on("reset",function(){
                that.$el.html(that.template());
                var countId = core.getCount();
                that.$(".ztree").attr("id",countId);
                $.fn.zTree.init(that.$("#"+countId),that.setting,that.collection.toJSON());
                that.tree= $.fn.zTree.getZTreeObj(""+countId);
                if(that.treeOption.expend){
                    that.tree.expandAll(true);
                }
            });
            this.eb.on("DepartmentTree:Fresh",function(){
                that.fresh();
            });
        },
        findChecked:function(){
            var nodes = this.tree.getCheckedNodes(true),
                collection=new DepartmentCollection();
            collection.add(nodes);
            return collection;
        },
        findRadioChecked:function(){
            return this.checkedRadio;
        },
        fresh:function(){
            this.collection.fetch({
                data:this.dataOption
            });
        },
        render:function(){
            this.$el.append(this.template());
            this.fetch();
            return this;
        },
        unRender:function(){
            this.tree.destroy();
            this.undelegateEvents();
            this.remove();
        },
        fetch:function(){
            this.collection.fetch({
                data:this.dataOption
            });
        }
    });
});