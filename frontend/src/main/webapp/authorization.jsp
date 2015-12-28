<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization Form</title>
</head>
<body>
<div id="login" class="animate form">
  <form  method="post" action="/service/authorization" autocomplete="on">
    <h1>Log in</h1>
    <p>
      <label for="username" class="uname" data-icon="u" > Your email or username </label>
      <input id="username" name="login" required="required" type="text" placeholder="myusername or mymail@mail.com"/>
    </p>
    <p>
      <label for="password" class="youpasswd" data-icon="p"> Your password </label>
      <input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO" />
    </p>
    <p class="keeplogin">
      <input type="checkbox" name="loginkeeping" id="loginkeeping" value="loginkeeping" />
      <label for="loginkeeping">Keep me logged in</label>
    </p>
    <p class="login button">
      <input type="submit" value="Login" />
    </p>
    <p class="change_link">
      Not a member yet ?
      <a href="#toregister" class="to_register">Join us</a>
    </p>
    <p class="change_link">
      ${status}
    </p>
  </form>
</div>
</body>
</html>
