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
  CarContainer cars = null;
  String id = request.getParameter("id");
  if(id != null) {
    mark = SolveModel.getModel(Integer.parseInt(id));
    cars = SolveCar.getCarsByModel(Integer.parseInt(id));
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

<% if(mark != null) { %>

<div><br>
  <p>Model:</p><br>

<table border="1">
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Contry</th>
    <th>Action</th>
    <th>Action</th>
  </tr>
  <tr>
    <td><%=mark.getId() %></td>
    <td><%=mark.getName() %></td>
    <td><%=mark.getContry()%></td>
    <%if(login != null){%>
      <td><input type="button" value="delete" onClick="location.href='/service/model/delete?id=<%= mark.getId() %>'"/></td>
      <td><input type="button" value="edit" onClick="location.href='/service/model/delete?id=<%= mark.getId() %>'"/></td>
    <%}%>
  </tr>
</table>
</div>

<% if(cars != null) { %>
<div>
  <br>
  <p>Cars:</p><br>
  <table border="1">
    <tr>
      <th>ID</th>
      <th>Model</th>
      <th>Power</th>
      <th>Year</th>
      <th>Action</th>
    </tr>
    <%
      for(Car item : cars.getCars())
      {
    %>
    <tr>
      <td><%=item.getId() %></td>
      <td><%=item.getModel() %></td>
      <td><%=item.getPower()%></td>
      <td><%=item.getYear()%></td>
      <%if(login != null){%>
        <td><input type="button" value="delete" onClick="location.href='/service/car/delete?id=<%= item.getId() %>'"/></td>
      <%}%>
      <td><input type="button" value="more" onClick="location.href='/service/car/carsInfoConstruction.jsp?id=<%= item.getId() %>'"/></td>
    </tr>
    <%
      }
    %>
  </table>
</div>
<%}%>


<%}%>

</body>
</html>
