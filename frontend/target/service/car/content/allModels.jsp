<%@ page import="com.database.container.ModelContainer" %>
<%@ page import="com.dependencies.SolveModel" %>
<%@ page import="com.database.logic.Mark" %>
<%@ page import="com.database.container.CarContainer" %>
<%@ page import="com.dependencies.SolveCar" %>
<%@ page import="com.database.logic.Car" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CarContainer cars = null;
  String curr_page = request.getParameter("page");
  String per_page = request.getParameter("per_page");
  if(curr_page != null && per_page != null)
  {
    cars = SolveCar.getCars(Integer.parseInt(curr_page), Integer.parseInt(per_page));
  }else {
    cars = SolveCar.getCars();
    curr_page = Integer.toString(cars.getPage());
    per_page = Integer.toString(cars.getPer_page());
  }

%>
<html>
<head>
    <title></title>
</head>
<body>

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
    <td><input type="button" value="delete" onClick="location.href='/service/model/delete?id=<%= item.getId() %>'"/></td>
    <td><input type="button" value="more" onClick="location.href='/service/model/delete?id=<%= item.getId() %>'"/></td>
  </tr>
  <%
    }
  %>
</table>

<div>
  <input type="button" value="Add new car" onClick="location.href='/service/car/carAddConstruction.jsp'"/>
</div>


  <div>
      <% if(Integer.parseInt(curr_page) > 0){ %>
      <a href="/service/car/carsOutConstruction.jsp?page=<%=(cars.getPage() - 1)%>&per_page=<%=cars.getPer_page()%>"> << Previous</a>
      <% }else{ %>
      <a href="#"> << Previous</a>
      <%}%>

      <% if(Integer.parseInt(curr_page) < (models.getPages() -1)){ %>
        <a href="/service/car/carsOutConstruction.jsp?page=<%=(cars.getPage() + 1)%>&per_page=<%=cars.getPer_page()%>"> >> Next</a>
        <% }else{ %>
        <a href="#"> >> Next</a>
      <%}%>
  </div>
</body>
</html>
