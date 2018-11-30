<%@ page import="com.adminportal.servlets.LoginBean" %>
<%@ page import="com.adminportal.servlets.Login" %><%--
  Created by IntelliJ IDEA.
  User: Alvin K
  Date: 11/26/2018
  Time: 9:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% HttpSession mySession = request.getSession(false);
    if(mySession == null){
        response.sendRedirect("error.jsp");
    }
    else if(((LoginBean)mySession.getAttribute("user")) == null){
        response.sendRedirect("error.jsp");
    }
%>
<head>
    <title>Title</title>
</head>
<body>

<h1>Global Link</h1>

</body>
</html>
