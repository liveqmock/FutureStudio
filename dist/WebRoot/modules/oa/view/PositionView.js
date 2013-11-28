//部门tree view，对外暴露几个接口。
//点击叶子结点
//重置叶子结点信息
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'privilege',
    './UserEditView',
    'text!../tpl/position.html'
],function($, _, Backbone, core, privilege,UserEditView, tpl){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"privilege-dept",
        events:{
            "click .editDepartment":"editDepartment",
            "click .addDepartment":"addDepartment",
            "click .addUser":"addUser"
        },
        template:_.template(tpl),
        initialize:function(options){
            this.eb = options.eb;
            this.$el.html(this.template({
                name:this.model.get("name"),
                username:this.model.get("principal").name
            }));
            var k=0;
            if(parseInt(this.model.get("dlevel"),10) === 100){
                this.$('.addDepartment').remove();
            }else{
                this.$(".addUser").remove();
            }
            //添加组织权限
            if(!privilege.hasRight("300040")){
                this.$(".addDepartment").remove();
                k=1;
            }
            //添加用户
            if(!privilege.hasRight("300035")){
                this.$(".addUser").remove();
                k=1;
            }
            //编辑组织权限
            if(!privilege.hasRight("300045")){
                this.$(".editDepartment").remove();
                k++;
            }
            if(k===2){
               //这表明下方已经没有可以操作的按钮了。
                this.$(".position-tr-operation-button").remove();
            }
        },
        render:function(){
            return this;
        },
        addUser:function(){
            new UserEditView({
                isNew:true,
                eb:this.eb,
                department:this.model
            }).render();
        },
        addDepartment:function(){
            console.log("addDepartment");
        },
        editDepartment:function(){
            console.log("editDepartment");
        }
    });
});