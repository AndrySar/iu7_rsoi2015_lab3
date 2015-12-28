<%@ page import="com.database.container.ModelContainer" %>
<%@ page import="com.dependencies.SolveModel" %>
<%@ page import="com.database.logic.Mark" %>
<%@ page import="com.dependencies.CookieWork" %>
<%@ page import="com.dependencies.Main" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 2:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  ModelContainer models = null;
  String curr_page = request.getParameter("page");
  String per_page = request.getParameter("per_page");
  if(curr_page != null && per_page != null)
  {
    models = SolveModel.getModels(Integer.parseInt(curr_page), Integer.parseInt(per_page));
  }else {

    models = SolveModel.getModels();
    if(models != null) {
      curr_page = Integer.toString(models.getPage());
      per_page = Integer.toString(models.getPer_page());
    }
    {
      curr_page = "0";
      per_page = "20";
    }
  }

  String login = null;
  String token = CookieWork.getValueCookie(request, "token");
  if(token != null) {
    login = Main.currentSession.checkToken(token);
  }

%>
<html>
<head>
    <title></title>
</head>
<body>

<% if(models != null){ %>
<table border="1">

  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Contry</th>
    <th>Action</th>
  </tr>
  <%
    for(Mark item : models.getMarks())
    {
  %>
  <tr>
    <td><%=item.getId() %></td>
    <td><%=item.getName() %></td>
    <td><%=item.getContry()%></td>
    <%if(login != null){%>
      <td><input type="button" value="delete" onClick="location.href='/service/model/delete?id=<%= item.getId() %>'"/></td>
    <%}%>
    <td><input type="button" value="more" onClick="location.href='/service/model/modelsInfoConstruction.jsp?id=<%= item.getId() %>'"/></td>
  </tr>
  <%
    }
  %>
</table>
<%}%>

<%if(login != null){%>
  <div>
    <input type="button" value="Add new model" onClick="location.href='/service/model/modelAddConstruction.jsp'"/>
  </div>
<%}%>


  <div>
      <% if(models !=null && Integer.parseInt(curr_page) > 0){ %>
      <a href="/service/model/modelsOutConstruction.jsp?page=<%=(models.getPage() - 1)%>&per_page=<%=models.getPer_page()%>"> << Previous</a>
      <% }else{ %>
      <a href="#"> << Previous</a>
      <%}%>

      <% if(models != null && Integer.parseInt(curr_page) < (models.getPages() -1)){ %>
        <a href="/service/model/modelsOutConstruction.jsp?page=<%=(models.getPage() + 1)%>&per_page=<%=models.getPer_page()%>"> >> Next</a>
        <% }else{ %>
        <a href="#"> >> Next</a>
      <%}%>
  </div>
</body>
</html>
