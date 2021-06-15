<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/5/27
  Time: 2:06 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
</head>
<body>
<div>
    <a href="home.jsp">
        <img class="logo" src="https://tse4-mm.cn.bing.net/th/id/OIP.KKd7eZf1mTYtJhJKaA0mQwHaE4?w=270&h=180&c=7&o=5&pid=1.7">
    </a>
</div>
<div id="message" style="color: red"></div>
<div>
    用户名 : <input id="userName"> <br>
    密码 : <input id="password" type="password"> <br>
<%--    验证码 : <input id="imageCode" > <img id="code" src="/day18_bqg_back/user/getImageCode"> <a href="javascript:void(0)" onclick="changeImage()">看不清,换一张</a> <br>--%>
    <button onclick="login()">登录</button> <a href="register.jsp">注册</a>
</div>

</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    function changeImage() {
        $("#code").prop("src","/day18_bqg_back/user/getImageCode?"+new Date());
    }

    function login() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var imageCode = $("#imageCode").val();
        //在js中先判断用户名密码的是否合规
        if (userName == undefined || password == undefined || "" == userName || "" == password) {
            $("#com.banyuan.message").text("对不起,用户名密码不能为空")
            return;
        }
        // if (imageCode == undefined || imageCode == "") {
        //     alert("对不起,验证码不能为空");
        //     return;
        // }
        $.ajax({
            url:"/day18_bqg_back/user/login",
            type: "post",
            data:{'userName':userName,'password':password},
            dataType: "json",
            success: function (data) {
                if (data.code == -1) {
                    $("#com.banyuan.message").text(data.message);
                }else{
                    location.href = 'home.jsp';
                }
            }
        });
    }
</script>
</html>
