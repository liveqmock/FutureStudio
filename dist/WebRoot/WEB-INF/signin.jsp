<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="cn.future.common.service.impl.ConfigHelperImpl"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String displayName = ConfigHelperImpl.getProperty("companyName", "无名字");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=displayName %> - 请登陆</title>
    <link href="css/bootstrapwrap.css?v=1.0.0" rel="stylesheet" media="screen">
    <link href="css/sign/main.css?v=1.0.0" rel="stylesheet" media="screen">
</head>
<body>
<div class="login clearfix">
    <div class="login-area right">
        <div class="login-screen">
            <div class="login-icon">
                <img src="<%=basePath%>img/sign/studio.png" alt="欢迎来到<%=displayName %>">
                <h4>欢迎来到<br/><strong><%=displayName %></strong></h4>
            </div>

            <div class="login-form">
                <div class="control-group">
                    <input type="text" class="login-field" value="" placeholder="帐户名" id="inputId">
                    <label class="login-field-icon icon-2x icon-user" for="inputId"></label>
                </div>

                <div class="control-group">
                    <input type="password" class="login-field" value="" placeholder="密码" id="inputPassword">
                    <label class="login-field-icon icon-2x icon-certificate" for="inputPassword"></label>
                </div>
                
                <div class="control-group">
                	<img class="verify-img" src="${verifyCode.imgUrl}" />
                    <input type="text" class="verify-field" value="" placeholder="验证码" id="verifyId">
                    <input type="hidden" class="verify-field" value="${verifyCode.vcodeKey}" id="verifyKeyId">
                </div>
                
    <div class="message alert alert-error hide fade in">
    <button class="message-close close" type="button">×</button>
    <strong class="message-content"></strong>
    </div>
                <a class="btn btn-primary sign-in-button btn-large btn-block">登陆</a>
            </div>
        </div>
    </div>
</div>

    <script type="text/javascript" src="js/libs/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/libs/bootstrap.js"></script>
    <script type="text/javascript" src="js/libs/jquery.cookie.js"></script>
    <script type="text/javascript" src="modules/sign/signin2.js"></script>
</body>
</html>