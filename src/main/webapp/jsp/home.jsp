<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 2021/5/27
  Time: 1:41 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
<%--    <base href="/day15/">--%>
    <style>
        .folder{
            display: inline-block;
            margin: 20px;
        }
    </style>
</head>
<body>
    <div>
        <a href="/day18_bqg_back/jsp/register.jsp">注册</a>
        <c:if test="${login==null}">
            <a href="login.jsp">登录</a>
        </c:if>
        <c:if test="${login!=null}">
            <a href="/day18_bqg_back/user/updatePassword.jsp">修改密码</a>
            <a href="/day18_bqg_back/user/logout">退出</a>
        </c:if>
        <c:if test="${login!=null}">
            <a href="type.jsp">类型管理</a>
            <a href="book.jsp">我的作品</a>
        </c:if>
    </div>

    <div id="content">

    </div>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>

</script>
</html>
