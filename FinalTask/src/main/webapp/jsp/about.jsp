<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>About Rich Only</title>
    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="aboutTitle" var="title"/>
    <fmt:message bundle="${loc}" key="aboutCorp" var="corp"/>
    <fmt:message bundle="${loc}" key="aboutStreet" var="street"/>
    <fmt:message bundle="${loc}" key="aboutAddress" var="address"/>
    <fmt:message bundle="${loc}" key="aboutPhone" var="phone"/>
    <fmt:message bundle="${loc}" key="aboutFax" var="fax"/>
    <fmt:message bundle="${loc}" key="aboutEmail" var="email"/>
    <fmt:message bundle="${loc}" key="aboutVat" var="vat"/>
    <fmt:message bundle="${loc}" key="aboutBank" var="bank"/>
    <fmt:message bundle="${loc}" key="aboutIban" var="iban"/>
    <fmt:message bundle="${loc}" key="aboutBic" var="bic"/>
    <fmt:message bundle="${loc}" key="aboutRepresentative" var="representative"/>
    <fmt:message bundle="${loc}" key="aboutDirector" var="director"/>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/about.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
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

<div class="header">
    <div class="inline">
        <img id="startpic" alt="Auctioneer" src="${pageContext.request.contextPath}/images/auctioneer.png">
    </div>

    <div class="inline">
        <h3>${title}</h3><br>

        <p><br>${corp}<br>
            ${street}<br>
            ${address}<br>
            ${phone}<br>
            ${fax}<br>
            ${email}<br>
            ${vat}<br>
            ${bank}<br>
            ${iban}<br>
            ${bic}<br>
            ${representative}<br>
            ${director}</p>
    </div>
</div>
</body>
</html>
