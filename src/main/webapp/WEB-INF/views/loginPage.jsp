<%--
  Created by IntelliJ IDEA.
  User: wenda
  Date: 8/12/2017
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Custom Login Page</title></head>
<body onload='document.f.username.focus();'>
<h3>Custom Login with Username and Password</h3>
<form name='f' action='/login' method='POST'>
    <table>
        <tr><td>User:</td><td>
            <input type='text' name='username' value=''></td></tr>
        <tr><td>Password:</td>
            <td><input type='password' name='password'/></td></tr>
        <tr><td>Remember me</td>
            <td><input id="remember_me" name="remember-me" type="checkbox"/></td></tr>
        <tr><td colspan='2'>
            <input name="submit" type="submit" value="Login"/></td></tr>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </table>
</form>
</body>
</html>
