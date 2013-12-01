/**
 * 基础模块：右上角浮动通知
 * @type opt{
 *  onClick: Function,
 *  body: string
 * }
 * color: string, 对应于不同的方法使用不同的颜色
 */
define([
    "jquery",
    "underscore",
    "backbone",
    "./Message"
], function ($, _, B, Message) {
    "use strict";
    var id = "message-container",
        $container = null,
        closeEvent = "message:close",
        messageList = [],
        lastMessage,
        index,
        between = 15,
        height,
        yPosition,
        message,
        controller = {
            info:function(opt){
                opt.color = "info";
                this._doMessage(opt);
            },
            error:function(opt){
                opt.color = "danger";
                this._doMessage(opt);
            },
            success:function(opt){
                opt.color = "success";
                this._doMessage(opt);
            },
            _doMessage:function(opt){
                this._prepareMessageNode();
                message = new Message(opt).render();
                this.addMessage(message);
            },
            addMessage:function(msg){
                yPosition = 50;
                if(messageList.length > 0){
                    lastMessage = messageList[messageList.length-1];
                    yPosition = lastMessage.getY() + 60 + between;
                }
                messageList.push(msg);

                msg.on(closeEvent,function(view){
                    controller.removeMessage(view);
                },this);

                $container.append(msg.el);
                msg.show(yPosition);
            },
            removeMessage:function(msg){
                index = _.indexOf(messageList,msg);
                height = msg.$el.height() + between;

                messageList.splice(index,1);
                for(index;index<messageList.length;index++){
                    messageList[index].addPosition(height*-1);
                }
            },
            _prepareMessageNode:function(){
                if(null===$container){
                    $container = $('<div id="'+id+'"></div>');
                    $("body").append($container);
                }
            }
        };
    return controller;
});