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
        <input type="submit" value="Log Out" name="logout">
    </form>
</div>

<br>

<form id="CreateUser" action="AdminServlet" method="post">
    <input type="submit" value="Create User" name="Submit">
</form>

<table>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Access Level</th>
        <th>Edit</th>
        <th>Delete</th>
        <th></th>
    </tr>
    <c:forEach items="${userList}" var="user" >
        <tr>
            <td><c:out value="${user.userID}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.accessLevel}"/></td>
            <td><form id="Edit" action="AdminServlet" method="post">
                <input type="hidden" name="userID" value="${user.userID}">
                <input type="hidden" name="username" value="${user.username}">
                <input type="hidden" name="accessLevel" value="${user.accessLevel}">
                <input type="submit" value="Edit" name="Submit">
            </form></td>
            <td><form id="Delete" action="AdminServlet" method="post">
                <input type="hidden" name="userID" value="${user.userID}">
                <input type="submit" value="Delete" name="Submit">
            </form></td>
            <td></td>
        </tr>
    </c:forEach>
</table>




</body>
</html>