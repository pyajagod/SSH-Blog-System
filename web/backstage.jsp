<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/2
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css">
</head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">您好，<span><%=request.getParameter("userId")%></span>用户！</div>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="statics/images/dog.jpg" class="layui-nav-img">
                    <span><%=request.getParameter("userId")%></span>
                </a>
                <input id="userId" type="text" hidden value="<%=request.getParameter("userId")%>">

            </li>
            <li class="layui-nav-item"><a href="index.jsp">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">

                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;" >用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="showUsersAction.action" target="manList">所有用户</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">文章管理</a>
                    <dl class="layui-nav-child">
                        <dd><a onclick="shang()" target="manList">编写文章</a></dd>
<%--                        <dd><a href="writeBlog.jsp" target="manList">上传文章</a></dd>--%>
                        <dd><a href="selectArticlesByPageAction.action?userId=<%=request.getParameter("userId")%>" target="manList">管理文章</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item">
                    <a href="javascript:;">文件资料管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="fileUpload.jsp?userId=<%=request.getParameter("userId")%>" target="manList">上传资料</a></dd>
                        <dd><a href="manageFile.action?userId=<%=request.getParameter("userId")%>" target="manList">管理资料</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <!--<div style="padding: 15px;">内容主体区域</div>-->
        <iframe class="layadmin-iframe ifrem_voice" src="showUsersAction.action" width="100%" height="700px" frameborder="0" name="manList" scrolling="no"></iframe>
    </div>
    <div class="layui-footer">
        © njxzc-qwy-bolgs 后台管理
    </div>
</div>
    <script src="statics/layui/layui.js"></script>
    <script src="statics/jquery.min.js"></script>
    <script>
        function shang(){
            window.location = "writeBlog.jsp?userId="+document.getElementById("userId").value;
        }
        //JavaScript代码区域
        layui.use('element', function(){
        var element = layui.element;
        });


    </script>
</body>
</html>
