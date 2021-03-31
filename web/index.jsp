<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/5/28
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
      <title>登录页面</title>
      <link rel="stylesheet" href="statics/layui/css/layui.css" />
      <style type="text/css">
          body{
              background: url('statics/images/backk.jpg');
              background-size: 1580px 800px;

          }
          #layout_all{
              border: 6px lightgray solid;
              margin-top: 180px;
              margin-left: 100px;
              margin-right: 150px;
              background-color: rgba(220, 38, 0, 0.1);
          }
          #layout_title{
              /*border: 1px black solid;*/
              font-size: 30px;
              color: white;
              padding-left: 480px;
              margin-top: 20px;
          }
          #layout_body{
              /*border: 1px red solid ;*/
              height: 230px;
              margin-top: 20px;
              padding-top: 10px;
              padding-left: 400px;
          }
      </style>
  </head>
  <body>
    <div class="layui-anim layui-anim-up layui-anim-rotate">
      <div id="layout_all">
          <div id="layout_title">
              qwy's bolg system
          </div>
          <div id="layout_body">

              <form class="layui-form" action="loginAction.action" method="post">
                  <div class="layui-form-item">
                      <label class="layui-form-label"><span style="color: #00F7DE">账号</span></label>
                      <div class="layui-input-inline">
                          <input type="text" id="userId" name="user.userId" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input" onclick="checkId()">
                      </div>
                      <div class="layui-form-mid layui-word-aux" id="check_id">请输入账号</div>
                  </div>
                  <div class="layui-form-item">
                      <label class="layui-form-label"><span style="color: #00F7DE">密码</span></label>
                      <div class="layui-input-inline">
                          <input type="password" id="userPwd" name="user.userPwd" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" onclick="checkPwd()">
                      </div>
                      <div class="layui-form-mid layui-word-aux" id="check_pwd">密码长度为6~20</div>
                  </div>
                  <div class="layui-form-item">
                      <label class="layui-form-label"><span style="color: #00F7DE">验证码</span></label>
                      <div class="layui-input-inline">
                          <input type="text" name="securityCode" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input" onclick="checkPwd()">
                          <div style="margin-left: 50px">
                              <img src="SecurityCodeImageAction.action" id="Verify" style="cursor: hand;" alt="看不清，换一张">
                          </div>
                          </div>
                  </div>
                  <input type="submit"  value="登录" class="layui-btn" style="margin-left: 130px;"  onblur="login()" />
                  <a href="register.jsp" class="layui-btn" style="margin-left: 10px;">注册</a>
              </form>
          </div>
      </div>
  </div>

    <script src="statics/layui/layui.js" charset="utf-8"></script>
    <script src="statics/jquery.min.js"></script>
    <script>
        $("#Verify").click(function () {
            $(this).attr("src", "SecurityCodeImageAction.action?timestamp=" + new Date().getTime());
        });
        function reloadImage(t) {
            t.src="findImage?flag="+Math.random();
        }

        function checkId() {
            var checkId = /^\d{8}$/;
            var id = document.getElementById("userId").value;
            if (checkId.test(id) === true){
                document.getElementById("check_id").innerHTML = "<font color='#0F0'>√√√√√</font>";
                return true;
            }else {
                document.getElementById("check_id").innerHTML = "<font color='#F00'>学号输入不规范！</font>";
                return false;
            }
        }
        function checkPwd() {
            var checkPwd = /^.{6,20}$/;
            var userPwd = document.getElementById("userPwd").value;
            if (checkPwd.test(userPwd) == true){
                document.getElementById("check_pwd").innerHTML = "<font color='#0F0'>√√√√√</font>";
                return true;
            }else {
                document.getElementById("check_pwd").innerHTML = "<font color='#F00'>密码格式错误</font>";
                return false;
            }
        }
        function login() {
            if (checkId() && checkPwd()) {
                return true;
            }else {
                alert("用户名或密码不能为空");
                return false;
            }
        }
    </script>
  </body>
</html>
