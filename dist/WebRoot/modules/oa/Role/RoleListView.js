define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/message/index',
    'modules/base/collection/BaseCollection',
    './RoleView'
],function($, _, Backbone, core, MessageView,
           BaseCollection, RoleView){
    "use strict";
    return Backbone.View.extend({
        tagName:"ul",
        className:"list-group",
        initialize:function(options){
            var that=this;
            this.eb = options.eb;

            this.roles=new BaseCollection();
            this.roles.url='oaRole_findRoles.action';
            this.roles.parse=function(res){
                return res.roles;
            };
            this.roles.comparator=function(m){
                return m.get("priority");
            };
            this.roles.on("reset",that.reload,this);
            this.eb.on("Role:Move:Up",this.moveUp,this);
            this.eb.on("Role:Move:Down",this.moveDown,this);
            this.eb.on("Role:List:Update",this.updateList,this);
        },
        updateList:function(){
            this.roles.fetch();
        },
        moveUp:function(model){
            var key = this.roles.indexOf(model),
                model2;
            if(key===0){
                return false;
            }
            model2 = this.roles.at(key-1);
            this.move(model,model2);
        },
        move:function(model1,model2){
            var that=this;
            $.ajax({
                url:"oaRole_switchPriority.action",
                type:"get",
                data:{
                    id1:model1.get("id"),
                    id2:model2.get("id"),
                    data1:model1.get("priority"),
                    data2:model2.get("priority")
                },
                beforeSend:function(){

                },
                success:function(response){
                    MessageView.success({
                        body:"移动成功"
                    });
                    that.roles.fetch();
                },
                error:function(response){
                    MessageView.error({
                        body:"移动失败"
                    });
                }
            });
        },
        moveDown:function(model){
            var key = this.roles.indexOf(model),
                model2;
            if((key+1)===this.roles.length){
                return false;
            }
            model2 = this.roles.at(key+1);
            this.move(model,model2);
        },
        reload:function(){
            var that=this;
            this.$el.html("");


            this.roles.each(function(m){
                that.$el.append(new RoleView({
                    model:m,
                    eb:that.eb
                }).render().el);
            });

        },
        render:function(){
            this.roles.fetch();
            return this;
        }
    });
});