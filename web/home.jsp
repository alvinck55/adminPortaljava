<%@ page import="com.adminportal.servlets.LoginBean" %><%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 11/24/2018
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<% HttpSession mySession = request.getSession(false);
    if(mySession == null){
    response.sendRedirect("error.jsp");
    }
    else if(mySession.getAttribute("user") == null){
        response.sendRedirect("error.jsp");
    }
    else{
        if(((LoginBean)mySession.getAttribute("user")).getAccessLevel() != 1){
            response.sendRedirect("error.jsp");
        }
    }
%>
<head>
    <title>Welcome </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<h1>Welcome <%out.println(((LoginBean)mySession.getAttribute("user")).getUsername());%>!</h1>

<div id="myDiv">
    <form id="logout" action="LogoutServlet" method="POST">
        <input type="submit" value="Log Out" name="logout">
    </form>
</div>

<br>
<a href="globalLink.jsp">Global Link</a>
<br>
<a href="studentLink.jsp">Student Link</a>



</body>
</html>
