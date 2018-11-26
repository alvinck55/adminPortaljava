<%--
  Created by IntelliJ IDEA.
  User: Alvin
  Date: 11/22/2018
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% session.invalidate(); %>
<html>
<script src="invalid.js"></script>
<head>
    <link rel="stylesheet" href="styles.css">
    <title>Login Page</title>
</head>
<body>
<div>
    <h1>Login Page</h1>
        <form name="loginForm" action="LoginServlet" method="POST">
        <table border = "0">
            <tbody>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" value="" size="30"/></td>
                 </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" value="" size="30"/></td>
                </tr>
            </tbody>
        </table>
        <input type="reset" value="Clear" name="Clear"/>
        <input type="submit" value="Submit" name="Submit"/>
            <br>
            ${responsefromServer}
    </form>
    <span id="serverResponse"></span>





</div>



</body>
</html>
