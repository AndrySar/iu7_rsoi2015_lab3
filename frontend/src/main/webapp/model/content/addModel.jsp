<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new model</title>
</head>
<body>
<form method="post" action="/service/models">
  Name:<input type="text" name="name" /><br/>
  Contry:<input type="text" name="contry" /><br/>
  <input type="submit" value="add" />
</form>
</body>
</html>
