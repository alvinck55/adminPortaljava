<%@ page import="com.adminportal.servlets.LoginBean" %><%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 11/25/2018
  Time: 5:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<% HttpSession mySession = request.getSession(false);
    if(mySession == null){
        response.sendRedirect("error.jsp");
    }
    else if(((LoginBean)mySession.getAttribute("user")) == null){
        response.sendRedirect("error.jsp");
    }
    else{
        if(((LoginBean)mySession.getAttribute("user")).getAccessLevel() != 2){
            response.sendRedirect("error.jsp");
        }
    }
%>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
<h1>Welcome <%out.println(((LoginBean)mySession.getAttribute("user")).getUsername());%>! ${myVar}</h1>

<div id="myDiv">
    <form id="logout" action="LogoutServlet" method="POST">
        <input type="submit" value="Log Out" name="logout"/>
    </form>
</div>

<form id="ConfirmEdit" action="AdminServlet" method="post">
<table>

    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Access Level</th>
        <th></th>
    </tr>

    <tr>

        <td>${userID}<input type="hidden" name="userID" value="${userID}"/></td>
        <td><input type="text" name="username" value="${username}"/></td>
        <td><input type="text" name="accessLevel" value="${accessLevel}"/></td>
        <td>
            <input type="submit" value="Confirm Edit" name="Submit">
        </td>

    </tr>

</table>
</form>




</body>
</html>