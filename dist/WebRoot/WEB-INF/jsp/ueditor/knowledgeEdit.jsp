<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用Studio编辑器</title>

</head>
<body>
    <textarea name="content" id="myEditor"> <s:property value="content" /></textarea>
    <br/>
    <button class="submit-button" type="button">提交</button>
    <script type="text/javascript" src="js/libs/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="ueditor/editor_config.js"></script>
    <script type="text/javascript" src="ueditor/editor_all_min.js"></script>
    <script type="text/javascript">
    var editor = new UE.ui.Editor();
    editor.render("myEditor");
    //1.2.4以后可以使用一下代码实例化编辑器
    //UE.getEditor('myEditor')
    $(function(){
    $(".submit-button").click(function(){
        console.log(editor.getContent());
    $.ajax({
    url:"knowledgeManage_updateKnowledgeContent.action",
    dataType:"json",
    type:"post",
    data:{
        id:'<s:property value="id" />',
        content:editor.getContent()
    },

    success:function(response){
        alert(response.message);
    },
    error:function(response){
        alert(response.responseText);
    },
    complete:function(){

    }
    });
    });
    });
    </script>

</body>
</html>