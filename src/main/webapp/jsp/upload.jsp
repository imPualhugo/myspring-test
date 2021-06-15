<%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2021/6/9
  Time: 10:20 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/file/upload" method="post"
      enctype="multipart/form-data">
    file: <input type="file" name="file"/> <br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
