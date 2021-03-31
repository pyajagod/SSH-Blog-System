<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/4/8
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css" />
</head>
<body>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll layui-bg-green">
            <li class="layui-nav-item">
                <div class="layui-anim layui-anim-up layui-anim-rotate">
                    <img src="statics/images/dog.jpg" class="layui-nav-img" style="padding: 20px; margin-left: 60px"><br />
                    <span style="margin-left: 55px">
    <%--                    欢迎<s:property value="user.userName"/>登录！--%>
    <%--                    <c:out value="" />--%>
                    欢迎<span style="color: #00FFFF">${sessionScope.user.userName}</span>登录！<br />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="changeInformationAction.action?userId=${sessionScope.user.userId}" style="color: #00FFFF" >修改个人信息</a>
                    </span>
    
                </div>
    
            </li>
    
            <ul class="layui-nav layui-nav-tree layui-bg-green"  lay-filter="test">
    
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;" >资料管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="downloadFileAction.action" target="manList">下载资料</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">文章管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="searchBlogs.jsp" target="manList">查找文章</a></dd>
                        <dd><a href="selectArticleAction.action" target="manList">所有文章</a></dd>
                    </dl>
                </li>
    
            </ul>

            <c:if test="${sessionScope.user.userProsition == '管理员'}">
                <input id="userId" hidden value="${sessionScope.user.userId}"> 
                <button style="margin-left: 44px; margin-top: 40px" class="layui-btn  layui-btn-lg layui-btn-normal" onclick="tiao()">管理页面</button>
            </c:if>
        </div>
    </div>
    <div class="layui-body">
    <!-- 内容主体区域 -->
    <!--<div style="padding: 15px;">内容主体区域</div>-->
        <iframe class="layadmin-iframe ifrem_voice" src="searchBlogs.jsp" width="100%" height="700px" frameborder="0" name="manList" scrolling="no"></iframe>
    </div>

<%--    <div class="layui-footer">--%>
<%--        © njxzc-qwy-bolgs 前台显示--%>
<%--    </div>--%>

    <script src="statics/layui/layui.js" charset="utf-8"></script>
    <script src="statics/jquery.min.js"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块

            //监听导航点击
            element.on('nav(demo)', function(elem){
                //console.log(elem)
                layer.msg(elem.text());
            });
        });
        
        function tiao() {
            location.href = "backstage.jsp?userId="+document.getElementById("userId").value;
        }
    </script>
</body>
</html>
