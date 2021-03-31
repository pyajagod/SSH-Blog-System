<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/6/14
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查找博客</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css" media="all"/>
    <style type="text/css">
        #all{
            /*border: 1px red solid;*/
        }
        #tp{
            /*border: 1px black solid;*/
            padding-left: 490px;
            margin-top: 60px;
        }
        #kuang{
            /*border: 1px red solid;*/
            width: 500px;
            padding-left: 260px;
            margin-top: 20px;
            float: left;
        }
    </style>
</head>
<body>
    <div class="layui-form-item" id="all">
        <form action="selectByWordAction.action" method="post">
            <div id="tp">
                <img src="statics/images/mypg.jpg" width="249" height="249">
            </div>
            <div class="layui-input-block" id="kuang">
                <input type="text" id="userId" name="word" lay-verify="required" placeholder="请输入文章关键字" autocomplete="off" class="layui-input" onblur="checkUseId()">
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="submit" style="margin-top: 20px" value="搜索" class="layui-btn" />
        </form>

    </div>

    <script src="statics/layui/layui.js"></script>
    <script src="statics/jquery.min.js"></script>
<%--    <script type="text/javascript">--%>
<%--        function searchArticle() {--%>
<%--            $.ajax({--%>

<%--            })--%>
<%--        }--%>
<%--    </script>--%>
</body>
</html>
