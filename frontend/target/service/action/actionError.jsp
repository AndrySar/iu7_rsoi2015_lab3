<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.12.15
  Time: 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>

<template:insert template='/articleTemplate.jsp'>
  <template:put name='title' content='Templates' direct='true'/>
  <template:put name='header' content='/header.html' />
  <template:put name='sidebar' content='/sidebar.jsp' />
  <template:put name='content' content='/action/error.jsp'/>
  <template:put name='footer' content='/footer.html' />
</template:insert>

