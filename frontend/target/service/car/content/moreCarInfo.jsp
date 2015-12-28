<%@ page import="com.database.logic.Mark" %>
<%@ page import="com.dependencies.SolveModel" %>
<%@ page import="com.database.container.CarContainer" %>
<%@ page import="com.dependencies.SolveCar" %>
<%@ page import="com.database.logic.Car" %>
<%@ page import="com.dependencies.CookieWork" %>
<%@ page import="com.dependencies.Main" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Mark mark = null;
  Car car = null;
  String id = request.getParameter("id");
  if(id != null) {
    car = SolveCar.getCar(Integer.parseInt(id));
    mark = SolveModel.getModel(car.getMark());

  }

  String login = null;
  String token = CookieWork.getValueCookie(request, "token");
  if(token != null) {
    login = Main.currentSession.checkToken(token);
  }

%>
<html>
<head>
    <title>More Info</title>
</head>
<body>

<% if(car != null && mark != null) { %>

<div>
  <br>
  <p>Cars:</p><br>
  <table border="1">
    <tr>
      <th>ID</th>
      <th>Model</th>
      <th>Power</th>
      <th>Year</th>
      <th>Color</th>
      <th>Cost</th>
      <th>Mark</th>
    </tr>

    <tr>
      <td><%=car.getId() %></td>
      <td><%=car.getModel() %></td>
      <td><%=car.getPower()%></td>
      <td><%=car.getYear()%></td>
      <td><%=car.getColor()%></td>
      <td><%=car.getCost()%></td>
      <td><%=mark.getName()%></td>
      <%if(login != null){%>
        <td><input type="button" value="delete" onClick="location.href='/service/car/delete?id=<%= car.getId() %>'"/></td>
        <td><input type="button" value="edit" onClick="location.href='/service/car/carEditConstruction.jsp?id=<%= car.getId() %>'"/></td>
      <%}%>
    </tr>

  </table>
</div>

<%}%>

</body>
</html>
