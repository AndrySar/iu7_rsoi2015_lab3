<%@ taglib uri='/WEB-INF/tlds/template.tld' prefix='template' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<template:insert template='/articleTemplate.jsp'>
  <template:put name='title' content='Templates' direct='true'/>
  <template:put name='header' content='/header.html' />
  <template:put name='sidebar' content='/sidebar.jsp' />
  <template:put name='content' content='/car/content/moreCarInfo.jsp'/>
  <template:put name='footer' content='/footer.html' />
</template:insert>
