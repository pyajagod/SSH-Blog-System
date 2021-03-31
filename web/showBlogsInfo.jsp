<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/11
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>展示文章信息</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css">
</head>
<body>

    <form method="post" enctype="multipart/form-data">
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="70">
                <col width="150">
                <col width="100">
                <col width="200">
                <col width="100">
            </colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>标题</th>
                <th>标签</th>
                <th>上传时间</th>
                <th>阅读次数</th>
                <th>文章简介</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${article.blogTitle}</td>
                    <td>${article.blogLabel}</td>
                    <td>${article.blogUploadTime}</td>
                    <td>${article.blogReadTimes}</td>
                    <td>${article.blogIntroduction}</td>
                    <td>
                        <button class="layui-btn layui-btn-sm">
                            <a style="color: #00FFFF" href="deleteArticleAction.action?idd=${article.id}&userIdd=${article.blogUploader}">删除</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>

            <tr style="text-align: center">
                <td colspan="7">
                    <c:if test="${curPage <= 1}">
                        首页
                        上一页
                    </c:if>
                    <c:if test="${curPage > 1}">
                        <a href="selectArticlesByPageAction.action?userId=${userId}">首页</a>
                        <a href="selectArticlesByPageAction.action?curPage=${curPage - 1}&userId=${userId}">上一页</a>
                    </c:if>

                    <c:if test="${curPage == totalPages}">
                        下一页
                        尾页
                    </c:if>

                    <c:if test="${curPage < totalPages}">
                        <a href="selectArticlesByPageAction.action?curPage=${curPage + 1}&userId=${userId}">下一页</a>
                        <a href="selectArticlesByPageAction.action?curPage=${totalPages}&userId=${userId}">尾页</a>
                    </c:if>
                    <br/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>

    <script src="statics/layui/layui.js"></script>
    <script src="statics/jquery.min.js"></script>
</body>
</html>
