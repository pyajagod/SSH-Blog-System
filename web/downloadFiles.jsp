<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/25
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>文件下载</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css" media="all">

</head>
<body>
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="150">
                <col width="150">
            </colgroup>
            <thead>
            <tr>
                <th>序号</th>
                <th>文件名</th>
                <th>上传者</th>
                <th>可执行操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${fileBeans}" var="file" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${file.fileName}</td>
                    <td style="display: none">
                        <c:url value="downloadAction.action">
                            <c:param name="fileName" value="${file.fileName}"></c:param>
                        </c:url>
                    </td>
                    <td>${file.fileUploader}</td>
                    <td>
                        <a href="downloadAction.action?fileName=${file.fileName}">下载</a>
                    </td>
                </tr>
            </c:forEach>

            <tr style="text-align: center">
                <td colspan="4">
                    <c:if test="${curPage <= 1}">
                        首页
                        上一页
                    </c:if>
                    <c:if test="${curPage > 1}">
                        <a href="downloadFileAction.action">首页</a>
                        <a href="downloadFileAction.action?curPage=${curPage - 1}">上一页</a>
                    </c:if>

                    <c:if test="${curPage == totalPages}">
                        下一页
                        尾页
                    </c:if>

                    <c:if test="${curPage < totalPages}">
                        <a href="downloadFileAction.action?curPage=${curPage + 1}">下一页</a>
                        <a href="downloadFileAction.action?curPage=${totalPages}">尾页</a>
                    </c:if>
                    <br/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
