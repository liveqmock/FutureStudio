//组织管理视图
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    './view/DepartmentTreeView',
    './view/PositionView',
    './view/UserListView',
    './view/UserDetailView',
    './view/UserEditView',
    './UserModifyRole/ModifyRole',
    'text!modules/base/tpl/fluid228.html'
],function($, _, Backbone, core, DepartmentTreeView, PositionView,
           UserListView, UserDetailView, UserEditView, ModifyRole, fluid){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"privilege-department",
        template:_.template(fluid),
        initialize:function(){
            var that = this,
                view;
            this.$el.append(this.template());
            this.eb = _.extend({},Backbone.Events);
            this.eb.on("Department:Click",function(model){
                that.showMiddle(model);
            },this);
            this.eb.on("User:Click",function(model){
                that.showUserDetail(model);
            },this);
            this.eb.on("User:Edit",function(model){
                that.editUserDetail(model);
            },this);
            this.eb.on("User:Refresh:Middle",function(){
                that.showMiddle();
            },this);
            this.listenTo(this.eb,"User:RoleModify",function(user){
                view = new ModifyRole({model:user});
                this.showTab(view,"修改角色["+user.get("name")+"]");
            });
            this.left=null;
            this.middle=null;
            this.right=null;
        },
        showTab:function(view,name){
            view.render();
            var options={
                id:"menu",
                name:name,
                view:view
            };
            core.trigger("Tab:open",options);
        },
        editUserDetail:function(model){
            new UserEditView({
                model:model,
                eb:this.eb
            }).render();
        },
        showUserDetail:function(model){
            if(this.right){
                this.right.undelegateEvents();
                this.eb.off(null,null,this.right);
                this.right.remove();
            }
            this.right=new UserDetailView({
                model:model,
                eb:this.eb
            });
            this.right.render();
            this.$(".span8").html(this.right.el);
        },
        showMiddle:function(Model){
            if(Model){
                this.middleModel=Model;
            }
            if(this.middle){
                this.middle.undelegateEvents();
                this.middle.remove();
            }

            var type = this.middleModel.get("dlevel");
            this.middle=new PositionView({
                model:this.middleModel,
                eb:this.eb
            });
            this.$(".span2b").html(this.middle.render().el);
            //如果是职位，那么装载用户列表信息。
            if(parseInt(type,10) === 100){
                this.$(".span2b").append(new UserListView({
                    positionId:this.middleModel.get("id"),
                    eb:this.eb
                }).render().el);
            }
        },
        render:function(){
            var that = this;
            var dept1 = new DepartmentTreeView({
                eb:this.eb,
                treeOption:{
                    type:"none",
                    expend:true
                }
            }).render();
            this.$(".span2a").append(dept1.el);
            return this;
        },
        unRender:function(){
            this.eb.off();
            this.remove();
        }
    });
});