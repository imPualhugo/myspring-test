<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/6/1
  Time: 2:06 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的作品</title>
</head>
<body>
    <div>
        用户名 : <input id="userName"> <br>
        昵称 : <input id="nickName"> <br>
        密码 : <input id="password" type="password"> <br>
        确认密码 : <input id="repassword" type="password"> <br>
        <button onclick="register()">注册</button>
    </div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    function register() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var repassword = $("#repassword").val();
        var nickName = $("#nickName").val();
        if (userName == undefined || userName == "" || password == undefined || password == "") {
            alert("对不起,用户名密码不能为空");
            return;
        }
        if (password != repassword) {
            alert("对不起,两次密码不一致");
            return;
        }

        $.ajax({
            url:"/day18_bqg_back/user/register",
            data:{"userName":userName,"password":password, "repeatPassword": repassword,"nickName":nickName},
            dataType: "json",
            success: function (data) {
                if (data.code == -1) {
                    alert(data.message);
                }else{
                    location.href="login.jsp";
                }
            }
        });


    }
</script>
</html>
