<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/3
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>显示学生信息</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css">
    <script src="statics/layui/layui.js"></script>
</head>
<body>
    <form method="post" enctype="multipart/form-data">
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="200">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>学号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>权限</th>
                <th>可执行操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userBeans}" var="user" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.userSex}</td>
                    <td>${user.userProsition}</td>
                    <td>
                        <button class="layui-btn layui-btn-sm">
                            <a href="changePrositionAction.action?userId=${user.userId}&userProsition=${user.userProsition}" style="color: #00FFFF">修改权限</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>

            <tr style="text-align: center">
                <td colspan="8">
                    <c:if test="${curPage <= 1}">
                        首页
                        上一页
                    </c:if>
                    <c:if test="${curPage > 1}">
                        <a href="showUsersAction.action">首页</a>
                        <a href="showUsersAction.action?curPage=${curPage - 1}">上一页</a>
                    </c:if>

                    <c:if test="${curPage == totalPages}">
                        下一页
                        尾页
                    </c:if>

                    <c:if test="${curPage < totalPages}">
                        <a href="showUsersAction.action?curPage=${curPage + 1}">下一页</a>
                        <a href="showUsersAction.action?curPage=${totalPages}">尾页</a>
                    </c:if>
                    <br/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>

    <script src="statics/layui/layui.js"></script>
</body>
</html>
