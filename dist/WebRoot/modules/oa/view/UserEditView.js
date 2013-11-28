/** User:Tony   Date: 1/21/13 - 9:28 PM */
//编辑用户账户
//TODO 用户的角色信息待定，编辑用户的部门信息
define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'text!../tpl/useredit.html',
    'modules/message/index',
    'bootstrap'
],function($, _, Backbone, core, tdTpl, MessageView ){
    "use strict";
    return Backbone.View.extend({
        tagName:"div",
        className:"user-edit",
        template:_.template(tdTpl,null,{variable:"args"}),
        events:{
            "click .button-save":"save",
            "click .button-cancel":"cancel",
            "blur .class-account":"accountCheck",
            "blur .class-employeeId":"employeeCheck"
        },
        initialize:function(options){
            this.isNew = options.isNew;
            this.department = options.department;
            this.eb = options.eb;

            var key={},
                acType,
                that=this;
            if(this.isNew){
                key={
                    title:"新增用户",
                    name:"",
                    employeeId:"",
                    account:"",
                    mobile:"",
                    email:"",
                    comments:"",
                    addTime:"2013-05-01",
                    statusCode:"1",
                    accountTypeId:"1000",
                    accountTypeName:"正式员工",
                    data1:"",
                    data2:"",
                    data3:"",
                    data4:"",
                    idcard:"",
                    birthday:"2013-05-01",
                    data5:"",
                    data6:"",
                    data7:"",
                    address:"",
                    familyAddress:"",
                    familyCall:"",
                    yearVocation:0,
                    content:""
                };
            }else{
                key=this.model.toJSON();
                key.title="修改用户信息";
            }
            this.$el.html(this.template(key));
            if(!this.isNew){
                this.$(".class-account").attr("disabled","disabled");
                this.$(".password-tr").remove();
            }
            if(parseInt(key.statusCode,10)===1){
                this.$(".status-1").addClass("active");
            }else{
                this.$(".status-0").addClass("active");
            }
            $.ajax({
                url:"oaAccount_findAllAccountType.action",
                type:"get",
                success:function(response){
                    for(var i=0;i<response.accountType.length;i++){
                        acType = '<option value="'+response.accountType[i].id+'">'+
                            response.accountType[i].name+
                            '</option>';
                        that.$(".accountType select").append(acType);
                    }
                }
            });
            this.$(".datepicker").datepicker({
                language:"zh-CN"
            });
        },
        employeeCheck:function(){
            var that=this;
            $.ajax({
                url:"oaAccount_employeeIdCheck.action",
                type:"get",
                data:{
                    employeeIdCheck:that.$(".class-employeeId").val()
                },
                success:function(response){
                    that.$(".input-alert-employeeId").html(response.message);
                },
                error:function(response){

                }
            });
        },
        accountCheck:function(){
            var that=this;
            $.ajax({
                url:"oaAccount_accountCheck.action",
                type:"get",
                data:{
                    data1:that.$(".class-account").val()
                },
                success:function(response){
                    that.$(".input-alert-account").html(response.message);
                },
                error:function(response){

                }
            });
        },
        cancel:function(){
            this.undelegateEvents();
            this.$(".modal").modal("hide");
        },
        unrender:function(){
            this.undelegateEvents();
            this.remove();
        },
        save:function(){
            var that=this,
                data={
                name:this.$(".class-name").val(),
                employeeId:this.$(".class-employeeId").val(),
                account:this.$(".class-account").val(),
                mobile:this.$(".class-mobile").val(),
                email:this.$(".class-email").val(),
                comments:this.$(".class-comments").val(),
                addTime:this.$(".class-addTime").val()+" 00:00:00",
                statusCode:0,
                statusName:"不可用",
                accountTypeId:this.$(".class-accountType").val(),
                accountTypeName:this.$(".class-accountType").find("option:selected").text(),
                yearVocation:parseInt(this.$(".class-yearVocation").val(),10),
                data1:this.$(".class-data1").val(),
                data2:this.$(".class-data2").val(),
                data3:this.$(".class-data3").val(),
                data4:this.$(".class-data4").val(),
                idcard:this.$(".class-idcard").val(),
                birthday:this.$(".class-birthday").val()+" 00:00:00",
                data5:this.$(".class-data5").val(),
                data6:this.$(".class-data6").val(),
                data7:this.$(".class-data7").val(),
                address:this.$(".class-address").val(),
                familyAddress:this.$(".class-familyAddress").val(),
                familyCall:this.$(".class-familyCall").val(),
                content:""
                };
            if(this.$(".status-1").hasClass("active")){
                data.statusCode=1;
                data.statusName="可用";
            }
            if(this.isNew){
                data.password=this.$(".class-password").val();
                data.departmentId=this.department.get("id");//TODO 这里是position传递过来的职位id
                $.ajax({
                    url:"oaAccount_accountAdd.action",
                    type:"post",
                    dataType:"json",
                    data:data,
                    success:function(response){
                        MessageView.success({
                            body:response.message
                        });
                        that.eb.trigger("User:Refresh:Middle");
                        that.cancel();
                    },
                    error:function(response){
                        MessageView.error({
                            body:response.responseText
                        });
                    }
                });
            }else{
                data.id=this.model.get("id");
                $.ajax({
                    url:"oaAccount_updateAccount.action",
                    type:"post",
                    data:data,
                    dataType:"json",
                    success:function(response){
                        MessageView.success({
                            body:response.message
                        });
                        that.eb.trigger("User:Refresh:Middle");
                        that.cancel();
                    },
                    error:function(reponse){
                        MessageView.error({
                            body:reponse.responseText
                        });
                    }
                });
            }
        },
        render:function(){
            var that=this;
            this.$("btn-group").button();
            $("body").append(this.el);
            this.$(".modal").modal({
                backdrop:"static",
                show:true
            });
            this.$('.modal').on('hidden', function () {
                that.unrender();
            });
            return this;
        }
    });
});