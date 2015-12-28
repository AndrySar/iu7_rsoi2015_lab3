<%@ page import="com.dependencies.SolveCar" %>
<%@ page import="com.database.logic.Car" %>
<%@ page import="com.database.logic.Mark" %>
<%@ page import="com.dependencies.SolveModel" %>
<%@ page import="com.database.container.ModelContainer" %>
<%@ page import="com.dependencies.Main" %>
<%@ page import="com.dependencies.CookieWork" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Car car = null;
  Mark mark = null;
  ModelContainer models = null;
  String id = request.getParameter("id");
  if(id != null) {
    car = SolveCar.getCar(Integer.parseInt(id));
    mark = SolveModel.getModel(car.getMark());
    models = SolveModel.getModels();

  }

  String login = null;
  String token = CookieWork.getValueCookie(request, "token");
  if(token != null) {
    login = Main.currentSession.checkToken(token);
  }
%>

<%if(login != null){%>

<html>
<head>
    <title>Edit Car</title>
</head>
<body>

  <form method="get" action="/service/cars">
    Id:<input type="text" readonly name="id" value=<%=car.getId()%> /><br/>
    Model:<input type="text" name="model" value=<%=car.getModel()%> /><br/>
    Power(ls):<input type="number" name="power" value=<%=car.getPower()%> /><br/>
    Year:<input type="text" name="year" value=<%=car.getYear()%> /><br/>
    Color:<input type="text" name="color" value=<%=car.getColor()%>/><br/>
    Cost:<input type="number" name="cost" value=<%=car.getCost()%> /><br/>
    Mark:<input type="number" readonly name="mkname" value=<%=mark.getName()%> />
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

<%}else{%><h2>Unauthorization</h2><%}%>
