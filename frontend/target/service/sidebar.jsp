<%@ page import="com.dependencies.Main" %>
<%@ page import="com.dependencies.CookieWork" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<font size='3'>Service</font><p>
  <table width='145'>
  <%
    String login = null;
    String token = CookieWork.getValueCookie(request, "token");

    if(token != null) {
      login = Main.currentSession.checkToken(token);
    }
  %>

  <tr><td>Пользователь :<%if (login == null) {%>

    <div>(Не авторизирован)</div> <a href=/service/registration.jsp>Регистрация</a><br>
                                  <a href=/service/authorization.jsp>Авторизация</a>
    <% }else{ %>(<%= login %>
    <a href=/service/exit>Выход</a>)<%} %>



  </td></tr>

    <tr><td><a href='/service/index.jsp'>
	 			Main </a></td></tr>

    <tr><td><a href='/service/model/modelsOutConstruction.jsp'>
	 			Models </a></td></tr>

    <tr><td><a href='/service/car/carsOutConstruction.jsp'>
	 			Cars </a></td></tr>

    <%--<tr><td><a href='underConstruction.jsp'>--%>
	 			<%--Nested Templates</a></td></tr>--%>

    <%--<tr><td><a href='underConstruction.jsp'>--%>
	 			<%--Implementing Templates </a></td></tr>--%>

  </table></p>

