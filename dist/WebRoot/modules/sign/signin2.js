var globalAjax=false;
function resetButton(){
    "use strict";
    globalAjax = false;
    $(".sign-in-button").removeClass("disabled");
}
function showMessage(content){
    "use strict";
    $(".message-content").html(content);
    $(".message").removeClass("hide");
    setTimeout(function(){
        $(".message").addClass("hide");
    },8000);
}
function refreshVerifyCode(){
    "use strict";
	$.ajax({
        url:"oaUnfilter_findVerifyCode.action",
        type:"get",
        success:function(data){
            $(".verify-img").attr("src",data.verifyCode.imgUrl);
            $("#verifyKeyId").val(data.verifyCode.vcodeKey);
        }
    });
}
function signIn(){
    "use strict";
    if(!globalAjax && !$(".sign-in-button").hasClass("disabled")){
        $.ajax({
            url:"oaUnfilter_in.action",
            type:"post",
            data:{
                "account":$("#inputId").val(),
                "password":$("#inputPassword").val(),
                "vcodeKey":$("#verifyKeyId").val(),
                "vcode":$("#verifyId").val(),
                "signType":"w"
            },
            beforeSend:function(){
                $(".sign-in-button").addClass("disabled");
                globalAjax = true;
            },
            success:function(data){
                if(data.message === "success"){
                    window.location.href = "oaUnfilter_index.action";
                }else{
                    showMessage(data.message);
                    resetButton();
                }
            },
            error:function(){
                resetButton();
            }
        });
    }
}

$(function(){
    "use strict";
    if($.cookie("ci") && $.cookie("ca")){
        window.location.href = "oaUnfilter_index.action";
    }
    $(".sign-in-button").click(signIn);
    $(".verify-img").click(refreshVerifyCode);
    $(".message-close").click(function(){
        $(".message").addClass("hide");
    });
});

