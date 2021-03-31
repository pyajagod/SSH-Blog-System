<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/10
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>阅读全文</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css" />
    <style type="text/css">
        #lay_all{
            /*border: 1px red solid;*/
        }

        #lay_title{
            /*border: 1px black solid;*/
            font-size: 40px;
            /*font-family: "Arial Black";*/
        }

        #lay_label{
            /*border: 1px gray solid;*/
            font-size: 16px;
            color: #00F7DE;
        }
        #lay_content{
            /*border-right: 1px green solid;*/
        }
    </style>
</head>
<body>
    <div id="lay_all" style="overflow-y: auto; overflow-x: auto; width: 1340px; height: 700px">
        <div id="lay_title">
            ${title}
        </div>
        <div id="lay_label" style="border-bottom: 4px pink solid">
            <table>
                <tr>
                    上传时间:
                </tr>
                ${blogUploadTime}&nbsp;
                <span style="color: lightseagreen">|</span>&nbsp;
                <tr>
                    浏览次数:
                </tr>
                ${blogReadTimes}&nbsp;
                <span style="color: lightseagreen">|</span>&nbsp;
                <tr>
                    作者:
                </tr>
                ${sessionScope.username}
            </table>
            &nbsp;&nbsp
        </div>

        <div id="lay_content" style="font-size: 20px; margin-top: 50px">
            ${content1}
        </div>
    </div>


</body>
</html>
