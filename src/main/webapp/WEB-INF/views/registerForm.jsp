<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Register</h1>
    <sf:form method="POST" commandName="spitter">
      <sf:errors path="*" element="div" cssClass="errors"/>
      <sf:label path="firstName" cssErrorClass="error">First Name</sf:label>:
      <sf:input path="firstName" cssErrorClass="error"/><sf:errors path="firstName" cssClass="error" /><br/>
      <sf:label path="lastName" cssErrorClass="error">Last Name</sf:label>:
      <sf:input path="lastName" cssErrorClass="error"/><sf:errors path="lastName" cssClass="error" /><br/>
      Email: <sf:input path="email" cssErrorClass="error"/><sf:errors path="email" cssClass="error" /><br/>
      <sf:label path="username" cssErrorClass="error">Username</sf:label>:
      <sf:input path="username" cssErrorClass="error"/><sf:errors path="username" cssClass="error" /><br/>
      <sf:label path="username" cssErrorClass="error">Password</sf:label>:
      <sf:password path="password" cssErrorClass="error"/><sf:errors path="password" cssClass="error" /><br/>
      <input type="submit" value="Register" />
    </sf:form>
  </body>
</html>
