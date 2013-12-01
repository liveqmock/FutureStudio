define([
    "jquery",
    "underscore",
    "backbone",
    "modules/base/view/BaseItemView",
    "text!./tpl/item.html"
], function ($, _, Backbone, BaseItemView, tpl) {
    "use strict";
    return BaseItemView.extend({
        events:{
            "click .btn-validate":"validate"
        },
        template: _.template(tpl,null,{variable:"args"}),
        validate:function(){
            $.ajax({
                url:"/fs/pay/payment/manage_checkRemote.action",
                dataType:"json",
                type:"post",
                data:{
                    id:this.model.get("id")
                },
                beforeSend:function(){

                },
                success:function(res){
                    alert(res.message);
                },
                error:function(res){
                    alert(JSON.parse(res.responseText).message);
                },
                complete:function(){

                }
            });
        }
    });
});