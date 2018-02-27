<%--
  Created by IntelliJ IDEA.
  User: 83917
  Date: 2018/2/7
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${say}
<form action="/test/form" method="post">
    <input name="mainId" type="text">
    <input name="name" type="text">
    <input name="password" type="text">
    <input value="提交" type="submit">
</form>
</body>
</html>
