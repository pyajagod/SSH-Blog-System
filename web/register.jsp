<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/14
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册页面</title>
    <link rel="stylesheet" href="statics/layui/css/layui.css" media="all"/>
    <style>
        body{
            background: url('statics/images/bback.jpeg');
            background-size: 1580px 800px;

        }
        .rg_layout{
            width: 800px;
            height: 500px;
            border: 8px solid #EEEEEE;
            background-color: white;

            margin: auto;
            margin-top: 90px;
            background-color: rgba(220, 38, 0, 0.1);
        }

        .rg_title{
            /*border: 1px solid red;*/
            padding-left: 249px;

        }

        .rg_title > p:first-child{
            color: white;
            font-size: 40px;
        }


        .rg_center{
            /*border: 1px solid red;*/
            float: left;
            width: 500px;
            padding-left: 200px;
            padding-top: 40px;

        }

        .rg_right{
            /*border: 1px solid red;*/
            float: right;
            padding-right: 20px;
        }

        .rg_right > p:first-child{
            /*color: #A6A6A6;*/
            font-size: 15px;
        }

        .rg_right p a {
            color: lightyellow;
        }
    </style>
</head>
<body>
    <div class="layui-anim layui-anim-up layui-anim-rotate">
    <div class="rg_layout">
        <div class="rg_title">

            <p >欢迎新用户注册</p>
        </div>
        <div class="rg_center">
            <form class="layui-form layui-form-pane" action="registerAction.action" method="post">

                <div class="layui-form-item">
                    <label class="layui-form-label">账号</label>
                    <div class="layui-input-inline">
                        <input type="text" id="userId" name="user.userId" lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input" onblur="checkUseId()">
                    </div>
                    <div class="layui-form-mid layui-word-aux" id="check_id">账号必须是八位数字</div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="user.userName" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                    </div>

                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="user.userPwd" id="pwd" placeholder="请输入密码" autocomplete="off" class="layui-input" onblur="checkPwd()">
                    </div>
                    <div class="layui-form-mid layui-word-aux" id="check_pwd_one">密码必须6到20位</div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">核对密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" id="pwd_again" placeholder="请核对密码" autocomplete="off" class="layui-input" onblur="checkPwdAgain()">
                    </div>
                    <div class="layui-form-mid layui-word-aux" id="check_pwd">两次输入密码必须一致</div>
                </div>


                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-block">
                        <input type="radio" name="user.userSex" value="男" title="男" checked="" />
                        <input type="radio" name="user.userSex" value="女" title="女" />
                    </div>
                </div>

                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">权限</label>
                    <div class="layui-input-block">
                        <input type="radio" name="user.userProsition" value="管理员" title="管理员" disabled />
                        <input type="radio" name="user.userProsition" value="普通用户" title="普通用户" checked />
                    </div>
                </div>



                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-submit="" lay-filter="demo1" onblur="checkAll()">立即注册</button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="rg_right">
            <p>已有账号？<a href="index.jsp">立即登录</a></p>
        </div>
    </div>
</div>
    <script src="statics/layui/layui.js"></script>
    <script src="statics/jquery.min.js"></script>
    <script>
        layui.use(['form', 'layedit', 'laydate'], function(){
            var form = layui.form
                ,layer = layui.layer
                ,layedit = layui.layedit
                ,laydate = layui.laydate;

            //日期
            laydate.render({
                elem: '#date'
            });
            laydate.render({
                elem: '#date1'
            });

            //创建一个编辑器
            var editIndex = layedit.build('LAY_demo_editor');

            //自定义验证规则
            form.verify({
                title: function(value){
                    if(value.length < 5){
                        return '标题至少得5个字符啊';
                    }
                }
                ,pass: [
                    /^[\S]{6,12}$/
                    ,'密码必须6到12位，且不能出现空格'
                ]
                ,content: function(value){
                    layedit.sync(editIndex);
                }
            });

        });
    </script>
    <script type="text/javascript">
        function checkUseId() {
            var checkId = /^\d{8}$/;
            var id = document.getElementById("userId").value;
            if(checkId.test(id) === true){
                document.getElementById("check_id").innerHTML = "<font color='#0F0'>√√√√√</font>";
                return true;
            }else {
                document.getElementById("check_id").innerHTML = "<font color='#F00'>学号输入不规范！</font>";
                return false;
            }

        }

        function checkPwd() {
            var checkPwd = /^.{6,20}$/;
            var pwd = document.getElementById("pwd").value;
            if (checkPwd.test(pwd) === true){
                document.getElementById("check_pwd_one").innerHTML = "<font color='#0F0'>√√√√√</font>";
                return true;
            }else {
                document.getElementById("check_pwd_one").innerHTML = "<font color='#F00'>密码输入不规范！</font>";
                return false;
            }
        }

        function checkPwdAgain() {
            var pwd = document.getElementById("pwd").value;
            var pwdAgain = document.getElementById("pwd_again").value;
            if (pwd === pwdAgain && pwd !== null){
                document.getElementById("check_pwd").innerHTML = "<font color='#0F0'>√√√√√</font>";
                return true;
            } else {
                document.getElementById("check_pwd").innerHTML = "<font color='#F00'>两次密码不一致!</font>";
                return false;
            }
        }

        function checkAll() {
            if (checkUseId() && checkPwd() && checkPwdAgain()){
                alert("欢迎注册。。。");
            }
        }
    </script>

</body>
</html>
