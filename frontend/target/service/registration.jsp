<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 21.12.15
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Register form</title>
</head>
<body>
<div id="register" class="animate form">
  <form  method="post" action="/service/registration" autocomplete="on">
    <h1> Sign up </h1>
    <p>
      <label for="usernamesignup" class="uname" data-icon="u">Your username</label>
      <input id="usernamesignup" name="usernamesignup" required="required" type="text" placeholder="mysuperusername690" />
    </p>
    <p>
      <label for="emailsignup" class="youmail" data-icon="e" > Your email</label>
      <input id="emailsignup" name="emailsignup" required="required" type="email" placeholder="mysupermail@mail.com"/>
    </p>
    <p>
      <label for="passwordsignup" class="youpasswd" data-icon="p">Your password </label>
      <input id="passwordsignup" name="passwordsignup" required="required" type="password" placeholder="eg. X8df!90EO"/>
    </p>
    <p class="signin button">
      <input type="submit" value="Sign up"/>
    </p>
    <p class="change_link">
      Already a member ?
      <a href="#tologin" class="to_register"> Go and log in </a>
    </p>
  </form>
</div>
</body>
</html>
