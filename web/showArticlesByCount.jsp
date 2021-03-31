<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/10
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>所有文章</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css" media="all">
    <style type="text/css">
        #lay_all{
            /*border: 2px pink solid;*/
            margin-top: 50px;
            margin-left: 110px;
            margin-right: 80px;
        }
        #lay_con{
            /*border: 1px red solid;*/
            float: left;
        }
        #lay_writer{
            /*border: 1px #00FF00 solid;*/
            height: 169px;
        }
        #lay_title{
            /*border: 1px black solid;*/
            font-size: 30px;
            color: black;
            font-family: "Leelawadee UI Semilight";
            /*padding-right: 40px;*/
        }

        #lay_log{
            /*border: 1px gray solid;*/
        }

        #lay_context{
            /*border: 1px blue solid;*/
            height: 110px;
            width: 740px;
        }
    </style>
</head>
<body style="background-color: rgba(252, 247,197,1);">
<%--<form action="readArticleAction.action" method="post">--%>
<div id="lay_all" style="overflow-y: auto; overflow-x: auto; width: 1150px; height: 650px; background-color: rgba(252, 247,197,1);">
    <c:forEach items="${articles}" var="article">
        <div id="lay_con">
            <div id="lay_title">
                    ${article.blogTitle}--QWY
            </div>

            <div id="lay_log" style="color: lightseagreen; font-size: 20px;">
                <table>
                    <tr>
                        上传时间:
                    </tr>
                        ${article.blogUploadTime}&nbsp;
                    <span style="color: lightseagreen">|</span>&nbsp;
                    <tr>
                        浏览次数:
                    </tr>
                        ${article.blogReadTimes}&nbsp;
                    <span style="color: lightseagreen">|</span>&nbsp;
                    <tr>
                        标签:
                    </tr>
                        ${article.blogLabel}
                </table>
            </div>

            <div id="lay_context">

                <div style="font-size: 16px; color: #00F7DE">
                        ${article.blogIntroduction}
                </div>

                <div style="padding-left: 600px; margin-top: 5px">
                        <%--                        <input name="id" hidden value="${article.id}">--%>
                        <%--                        <button  >阅读全文</button>--%>
                    <button type="button" class="layui-btn layui-btn-normal"><a style="color: #00FFFF" href="readArticleAction.action?id=${article.id}">阅读全文</a></button>


                </div>
            </div>
        </div>
    </c:forEach>
    <c:forEach items="${ids}" var="id">
        <div id="lay_writer" style="border-bottom: 6px solid pink;">
            <img src="statics/images/erha.png" class="layui-nav-img" style="padding: 20px; margin-left: 60px"><br />

            <div style="margin-left: 820px; font-size: 16px; color: lightseagreen">
                作者:${id}
            </div>

        </div>
    </c:forEach>
    <div style=" font-size: 20px;">
        <c:if test="${curPage <= 1}">
            首页
            上一页
        </c:if>
        <c:if test="${curPage > 1}">
            <a href="selectByWordAction.action?word=${word}">首页</a>
            <a href="selectByWordAction.action?curPage=${curPage - 1}&word=${word}">上一页</a>
        </c:if>

        <c:if test="${curPage >= totalPages}">
            下一页
            尾页
        </c:if>

        <c:if test="${curPage < totalPages}">
            <a href="selectByWordAction.action?curPage=${curPage + 1}&word=${word}">下一页</a>
            <a href="selectByWordAction.action?curPage=${totalPages}&word=${word}">尾页</a>
        </c:if>
    </div>

</div>
<%--</form>--%>
<script src="statics/layui/layui.js" charset="utf-8"></script>
<script src="statics/jquery.min.js"></script>
</body>
</html>
