define([
    'jquery',
    'underscore',
    'backbone',
    'text!./assets/roleright.html',
    'modules/base/collection/BaseCollection',
    './RoleFunctionView',
    './RoleOperationView',
    './RoleDetailView',
    'bootstrap'
],function($, _, Backbone, roleright, BaseCollection,
           RoleFunctionView, RoleOperationView, RoleDetailView
    ){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        template: _.template(roleright),
        className:"role-right",
        initialize:function(options){
            this.eb = options.eb;

            this.allmenu=new BaseCollection();
            this.alloperation=new BaseCollection();
            var comparator=function(m){
                return m.get("priority");
            };
            this.allmenu.comparator=comparator;
            this.alloperation.comparator=comparator;
        },
        allMenuLoad:function(){
            var that=this,
                k=0;
            this.$(".all-menu").html("");

            this.allmenu.each(function(m){
                that.$(".all-menu").append(new RoleFunctionView({eb:that.eb,model:m}).render().el);
                k++;
                if(k===3){
                    k=0;
                    that.$(".all-menu").append("<br/>");
                }
            });
            this.$(".all-menu").button();
        },
        allOperationLoad:function(){
            var that=this,
                k=0;
            this.$(".all-operation").html("");

            this.alloperation.each(function(m){
                that.$(".all-operation").append(new RoleOperationView({eb:that.eb,model:m}).render().el);
                k++;
                if(k===3){
                    k=0;
                    that.$(".all-menu").append("<br/>");
                }
            });
            this.$(".all-operation").button();
        },
        loadRolePrivilege:function(){
            var that=this;
            this.modelMenu.each(function(m){
                that.eb.trigger("Role:Function:"+ m.get("id"));
            });
            this.modelOperation.each(function(m){
                that.eb.trigger("Role:Operation:"+ m.get("id"));
            });
        },
        loadRole:function(model){
            this.eb.trigger("Role:Function:Clear");
            this.eb.trigger("Role:Operation:Clear");
            this.resetPrivilege();
            var detail=new RoleDetailView({eb:this.eb,model:model}),
                that=this;
            detail.render();
            this.$(".role-base-info").html(detail.el);
            this.modelMenu=new BaseCollection();
            this.modelOperation=new BaseCollection();
            //TODO 加载权限操作信息，
            $.ajax({
                url:"oaPrivilege_findPrivilegeByRoleId.action",
                type:"get",
                data:{
                    id:model.get("id")
                },
                beforeSend:function(){

                },
                success:function(response){
                    that.modelMenu.add(response.roleMenus);
                    that.modelOperation.add(response.roleOperations);
                    that.loadRolePrivilege();
                },
                error:function(response){

                },
                complete:function(){

                }
            });
        },
        resetPrivilege:function(){
            this.$("button .active").removeClass("active");
        },
        setSysFunction:function(){
            //TODO 设置已有菜单
        },
        setOperation:function(){
           //TODO 设置已有操作
        },
        render:function(){
            var that = this;
            this.$el.html(this.template());
            $.ajax({
                url:"oaPrivilege_findAllPrivilege.action",
                type:"get",
                beforeSend:function(){

                },
                success:function(response){
                    that.allmenu.add(response.roleMenus);
                    that.alloperation.add(response.roleOperations);
                    that.allMenuLoad();
                    that.allOperationLoad();
                },
                error:function(response){

                },
                complete:function(){

                }
            });
            return this;
        }
    });
});