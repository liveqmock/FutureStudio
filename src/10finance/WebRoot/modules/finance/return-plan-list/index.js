define([
    "core",
    "jquery",
    "underscore",
    "backbone",
    "./ProjectReturnPlanCollection",
    "./item",
    "text!./tpl/index.html",
    'fputil',
    "bootstrap"
], function (core, $, _, Backbone, Collection, Item, tpl, fputil) {
    "use strict";
    /**
     * 列出项目的详细还款计划列表，需要参数：projectId
     */
    return Backbone.View.extend({
        template: _.template(tpl,null,{variable:"args"}),
        events:{
            "click .btn-save":"save"
        },
        initialize:function(options){
            this.projectId = options.projectId;
            this.collection = new Collection();
            this.listenTo(this.collection,"reset",this.loaded);
            this.eb = _.extend({},Backbone.Events);
            this.renderTime = 0;
        },
        render: function () {
            this.fetch();
            return this;
        },
        save:function(){
            var inputs = this.$(".new-returnplan").find("input"),
                selects = this.$(".new-returnplan").find("select"),
                $node,
                that = this,
                data = {};

            _.each(inputs,function(node){
                $node = $(node);
                data[$node.attr("data-property")]=$node.val();
            },this);

            _.each(selects,function(node){
                $node = $(node);
                data[$node.attr("data-property")]=$node.val();
            },this);

            data.id = this.collection.project.get("id");
            data.returnDate=fputil.stringToDate(this.$(".date-input").val()+" 12:00:00");

            $.ajax({
                url:"/fs/finance/ProjectReturnPlan/manage_add.action",
                dataType:"json",
                type:"post",
                data:JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                beforeSend:function(){

                },
                success:function(res){
                    that.fetch();
                },
                error:function(res){

                },
                complete:function(){


                }

            });
        },
        loaded:function(){
            var frag = document.createDocumentFragment();
            if(this.renderTime === 0){
                this.$el.html(this.template(this.collection.project.toJSON()));
                this.$(".datepicker-add").datepicker({
                    format: "yyyy-mm-dd hh:ii",
                    autoclose: true,
                    todayBtn: true,
                    pickerPosition: "bottom-left",
                    language:"zh-CN"
                });
                this.renderTime++;
            }

            this.eb.trigger("Item:Destroy");
            this.collection.each(function(m){
                frag.appendChild(new Item({
                    model:m,
                    eb:this.eb
                }).render().el);
            },this);
            this.$(".list-area").html(frag);
        },
        fetch:function(){
            this.collection.fetch({
                data:{
                    id:this.projectId
                }
            });
        }
    });
});