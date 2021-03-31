<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>编辑文章</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="statics/layui/css/layui.css">
    <link rel="stylesheet" href="statics/kindeditor-4.1.7/themes/default/default.css" />
    <link rel="stylesheet" href="statics/kindeditor-4.1.7/plugins/code/prettify.css" />
    <style type="text/css">
        body{
            background: url('statics/images/wri_back.png');
            background-size: 1580px 800px;

        }
        #lay_title{
            /*border: 1px black solid;*/
        }

        #lay_body{
            /*border: 1px black solid;*/
            margin-top: 30px;
        }

        #lay_all{
            /*border: 8px pink solid;*/
            margin-top: 40px;
            margin-left: 200px;
            margin-right: 200px;
            background-color: rgba(220, 38, 0, 0.1);
        }
    </style>
</head>
<body>
    <div align="center" id="lay_all">
        <form name="example" id="example" action="addArticleAction.action" method="post">
<%--    <%=request.getParameter("userId")%>--%>

            <div id="lay_title">
                <input id="uploader" name="uploader" hidden type="text" value="<%=request.getParameter("userId")%>" />
                <h2>文章编写</h2><br>
                <table>
                    <tr>
                        <td>标题</td>
                        <td><input id="title" type="text" name="title" /></td>
                        <%--                <%=request.getParameter("userId")%>--%>
                        <td>标签</td>
                        <td>
                            <select id="label" name="label">
                                <option value="程序人生">程序人生</option>
                                <option value="前端">前端</option>
                                <option value="架构">架构</option>
                                <option value="区块链">区块链</option>
                                <option value="编程语言">编程语言</option>
                                <option value="数据库">数据库</option>
                                <option value="游戏开发">游戏开发</option>
                                <option value="移动开发">移动开发</option>
                                <option value="运维">运维</option>
                                <option value="人工智能">人工智能</option>
                                <option value="安全">安全</option>
                                <option value="云计算/大数据">云计算/大数据</option>
                                <option value="研发管理">研发管理</option>
                                <option value="物联网">物联网</option>
                                <option value="计算机基础">计算机基础</option>
                                <option value="音视频开发">音视频开发</option>
                                <option value="其他">其他</option>
                            </select>
                        </td>
                    </tr>
                </table><br> <br>
                <span style="padding: 1px">文章简介</span>

                <textarea style="height: 50px; width: 400px" id="introduction" type="text" name="introduction"></textarea><br />
                <div id="check_intro" style="margin-left: 60px; color: #00FFFF">简介字数不超过100且不能为空</div>
            </div>

            <div id="lay_body">
                <textarea id="content" name="content1" cols="100" rows="8" style="width:800px;height:450px;visibility:hidden;margin:0px 100px 0px 100px;"><%=htmlspecialchars(htmlData)%></textarea>
                <br />
            </div>

<%--            <textarea rows="" cols="" name="schtmlnr" id="schtmlnr" style="display:none;"></textarea>--%>
            <input name="dosubmit" type="submit" value="上传文章" /> (提交快捷键: Ctrl + Enter)
        </form>
    </div>
    <script src="statics/layui/layui.js"></script>
    <script src="statics/jquery.min.js"></script>
    <script charset="utf-8" src="statics/kindeditor-4.1.7/kindeditor-all.js"></script>
    <script charset="utf-8" src="statics/kindeditor-4.1.7/lang/zh-CN.js"></script>
    <script charset="utf-8" src="statics/kindeditor-4.1.7/plugins/code/prettify.js"></script>
    <script>
        KindEditor.ready(function(K) {
            var editor1 = K.create('textarea[name="content1"]', {
                cssPath : 'statics/kindeditor-4.1.7/plugins/code/prettify.css',
                uploadJson : 'insertPictureAction.action',
                fileManagerJson : 'statics/kindeditor-4.1.7/jsp/file_manager_json.jsp',
                allowFileManager : true,
                afterCreate : function() {
                    var self = this;
                    K.ctrl(document, 13, function() {
                        self.sync();
                        document.forms['example'].submit();
                    });
                    K.ctrl(self.edit.doc, 13, function() {
                        self.sync();
                        document.forms['example'].submit();
                    });
                }
            });
            prettyPrint();
        })
        
        function checkIntro() {
            var intro = $("#introduction").val();
            if (intro.length > 100 ){
                document.getElementById("check_intro").innerHTML = "<font color='#F00'>简介格式错误</font>";
                return false;
            }
            if (intro.length === 0 ){
                document.getElementById("check_intro").innerHTML = "<font color='#F00'>简介不能为空</font>";
                return false;
            }
        }

        // function chuanzhi() {
        //     var title = $("#title").val();
        //     var label = $("#label").val();
        //     var uploader = $("#uploader").val();
        //     editor1.sync();
        //     var content1 = $("#content").val();
        //     $.ajax({
        //         url:"addArticleAction.action",
        //         type:"POST",
        //         data:{
        //             "blogTitle":title,
        //             "blogLabel":label,
        //             "blogContent":content1,
        //             "blogUploader":uploader
        //         },
        //         dataType:"json",
        //         contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        //         success:function (data) {
        //             console.log(data)
        //             window.location.href='backstage.jsp?userId='+uploader;
        //         }
        //     });

        // }
    </script>

</body>
</html>
<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&");
        str = str.replaceAll("<", "<");
        str = str.replaceAll(">", ">");
        str = str.replaceAll("\"", "\"");
        return str;
    }
%>
