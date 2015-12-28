<%@ page import="com.database.container.ModelContainer" %>
<%@ page import="com.dependencies.SolveModel" %>
<%@ page import="com.database.logic.Mark" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ModelContainer models = SolveModel.getModels();
%>
<html>
<head>
    <title>Add new model</title>
</head>
<body>
<form method="post" action="/service/cars">
  Model:<input type="text" name="model" /><br/>
  Power(ls):<input type="number" name="power" /><br/>
  Year:<input type="text" name="year" /><br/>
  Color:<input type="text" name="color" /><br/>
  Cost:<input type="number" name="cost" /><br/>
  <%--<%--%>
    <%--for(Mark item : models.getMarks())--%>
    <%--{--%>
  <%--%><input type="number" name="markId" value="<%=item.getId()%>">--%>
  <%--<% } %>--%>

  <select name="markId" onchange=" ">
    <% for (Mark item : models.getMarks()) {%>
    <option value=<%=item.getId()%> ><%=item.getName()%></option>
    <% } %>
  </select>

  <%--<c:choose>--%>
    <%--<c:when test="${models ne null}">--%>
      <%--<input type="text" name="markId" value="${models.getId()}"/>--%>
    <%--</c:when>--%>
    <%--<c:otherwise>--%>
      <%--<input type="text" name="markId" value=""/>--%>
    <%--</c:otherwise>--%>
  <%--</c:choose>--%>

  <input type="submit" value="add" />
</form>
</body>
</html>
