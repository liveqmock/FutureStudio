<%@ page language="java" import="java.util.*,java.lang.*,cn.future.common.pojo.*,cn.future.common.service.impl.ConfigHelperImpl,cn.future.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String companyName = CookieUtil.findCookies(request).get(CookieUtil.USERCOMPANYNAME);
    String userName = CookieUtil.findCookies(request).get(CookieUtil.USERNAME);
    Calendar today = Calendar.getInstance();
    today.setTime(new Date());
    int year = today.get(Calendar.YEAR);
    String dispalyName=ConfigHelperImpl.getProperty("companyName", "无名字");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=dispalyName %> - 企业管理系统</title>
    <meta name="description" content="">
    <meta name="author" content="Tony">
    <link href="css/main.css?v=1.0.0" rel="stylesheet">
    <%--<link rel="shortcut icon" href="ico/favicon.png">--%>
	<style type="text/css">
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <script src="js/libs/require.js?version=2.1.2"></script>
    <script src="js/main.js?v=1.0.0"></script>
    <script type="text/javascript">
        var sysInfo={
            basePath:'<%= basePath %>%>'
        }
    </script>
</head>
<body>


<%--navbar-inverse--%>
	<div class="navbar navbar-fixed-top studio-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          
          <a class="brand company-name" ><%=dispalyName %></a>
          <div class="nav-collapse collapse studio-nav-menu">
            

              <div class="btn-group pull-right">
                <a class="btn" href="#"><i class="icon-user icon-white"></i><%=userName %></a>
              	<button class="btn dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
              	<ul class="dropdown-menu pull-right">
              		<li><a class="myInfo"><i class="icon-group"></i>我的信息</a></li>
                    <li><a class="systemAbout"><i class="icon-hand-right"></i>关于</a></li>
                    <li><a class="signOut"><i class="icon-off"></i>退出</a></li>
              	</ul>
              </div>

          </div><!--/.nav-collapse -->
          
        </div>
      </div>
    </div>
    
    <div class="container-fluid" id="studio-container">
        <div class="tab-content" id="studio-tab-content"></div>
    </div>
    <hr>
    <footer>
    <p>&copy; Future Studio <%=year %> &nbsp</p>
    </footer>
   
</body>
</html>