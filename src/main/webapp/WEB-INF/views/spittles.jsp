<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />">
</head>
<body>
<sec:authorize access="hasRole('ROLE_SPITTER')">
    <div class="spittleForm">
        <h1>Spit it out...</h1>
        <h2>Hello <sec:authentication property="principal.username"/>!</h2>
        <form method="POST" name="spittleForm">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" name="latitude">
            <input type="hidden" name="longitude">
            <textarea name="message" cols="80" rows="5"></textarea><br/>
            <input type="submit" value="Add"/>
        </form>
    </div>
</sec:authorize>
<div class="listTitle">
    <h1>Recent Spittles</h1>
    <ul class="spittleList">
        <c:forEach items="${spittleList}" var="spittle">
            <li id="spittle_<c:out value="${spittle.id}"/>">
                <div class="spittleMessage"><c:out value="${spittle.message}"/></div>
                <div>
                    <span class="spittleTime"><c:out value="${spittle.time}"/></span>
                    <span class="spittleLocation">(<c:out value="${spittle.latitude}"/>, <c:out
                            value="${spittle.longitude}"/>)</span>
                </div>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${fn:length(spittleList) gt 20}">
        <hr/>
        <s:url value="/spittles?count=${nextCount}" var="more_url"/>
        <a href="${more_url}">Show more</a>
    </c:if>
</div>
<a href="/logout">Logout</a><br>
<a href="/signout">Signout</a><br>
<c:url value="/signout" var="signout_url"/>
<a href="${signout_url}">Signout</a><br>
<%--<sec:authorize access="isAuthenticated() and principal.username=='admin'">--%>
    <%--<a href="/admin">Administration</a>--%>
<%--</sec:authorize>--%>
<sec:authorize url="/admin">
    <s:url value="/admin" var="admin_url"/>
    <a href="${admin_url}">Admin</a>
</sec:authorize>
</body>
</html>