<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Help Rich Only</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/help.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="helpTitle" var="helpTitle"/>
    <fmt:message bundle="${loc}" key="helpText1" var="helpText1"/>
    <fmt:message bundle="${loc}" key="helpText2" var="helpText2"/>
    <fmt:message bundle="${loc}" key="helpText3" var="helpText3"/>
    <fmt:message bundle="${loc}" key="helpSend" var="helpSend"/>

    <script src="${pageContext.request.contextPath}/javascript/RegistrationPage.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${login eq (null)}">
        <%@include file="unsignedInHeader.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="signedInHeader.jsp" %>
    </c:otherwise>
</c:choose>
<br>

<div class="help">
    <h3>${helpTitle}</h3><br><br>

    <p>${helpText1}</p><br>

<%--
    <p>${helpText2}<br>
        ${helpText3}</p>
    <br>
--%>

    <%--<form action="controller" method="post" accept-charset="utf-8">
        <input name="command" type="hidden" value="help"/>
        <input type="text" name="help" placeholder="Problem..." class="input"/>
        <br>
        <input type="submit" value="${helpSend}" class="submit"/>
    </form>--%>

</div>
</body>
</html>
