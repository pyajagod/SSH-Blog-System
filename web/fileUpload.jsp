<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/24
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文件上传</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css">
</head>
<body>
    <form id="upfile" action="uploadFileAction.action" method="post" enctype="multipart/form-data">\
        <div style="margin-top: 150px; margin-left: 550px">
            <input name="uploaderId" hidden value="<%=request.getParameter("userId")%>">
            资料：<input type="file" id="file" name="upfile" multiple="multiple" />
            <br />
            <button style="margin-left: 65px; margin-top: 15px" id="upload">上传</button>
        </div>
    </form>

    <script src="statics/layui/layui.js"></script>
    <script src="statics/jquery.min.js"></script>
</body>
</html>
