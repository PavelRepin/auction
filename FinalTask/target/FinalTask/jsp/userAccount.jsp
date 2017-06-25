<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Personal account Rich Only</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userAccount.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">

    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="accountTitle" var="accountTitle"/>
    <fmt:message bundle="${loc}" key="accountName" var="accountName"/>
    <fmt:message bundle="${loc}" key="accountLastName" var="accountLastName"/>
    <fmt:message bundle="${loc}" key="accountLogin" var="accountLogin"/>
    <fmt:message bundle="${loc}" key="accountPassword" var="accountPassword"/>
    <fmt:message bundle="${loc}" key="accountWrongPassword" var="accountWrongPassword"/>
    <fmt:message bundle="${loc}" key="accountNewPassword" var="accountNewPassword"/>
    <fmt:message bundle="${loc}" key="accountRepeatedNewPassword" var="accountRepeatedNewPassword"/>
    <fmt:message bundle="${loc}" key="accountMoney" var="accountMoney"/>
    <fmt:message bundle="${loc}" key="accountAge" var="accountAge"/>
    <fmt:message bundle="${loc}" key="accountDocumentData" var="accountDocumentData"/>
    <fmt:message bundle="${loc}" key="accountCountry" var="accountCountry"/>
    <fmt:message bundle="${loc}" key="accountFullAddress" var="accountFullAddress"/>
    <fmt:message bundle="${loc}" key="accountEmail" var="accountEmail"/>
    <fmt:message bundle="${loc}" key="accountSave" var="accountSave"/>

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

<div class="account">
    <h3>${accountTitle}</h3><br>

    <form action="controller" method="post" accept-charset="utf-8">
        <label for="name">${accountName}</label><br>
        <input name="command" type="hidden" value="editProfile">
        <input name="idBidder" type="hidden" value="${bidder.getId()}">
        <input id="name" name="name" type="text" value="${bidder.getName()}" required class="field"><br>
        <label for="lastName">${accountLastName}</label><br>
        <input id="lastName" name="lastName" type="text" value="${bidder.getLastName()}" class="field"><br>
        <label for="login">${accountLogin}</label><br>
        <input name="login" id="login" class="field" type="text" value="${bidder.getLogin()}"><br>
        <label for="password">${accountPassword}</label>
        <c:if test="${wrongPassword == 1}">
            <span>${accountWrongPassword}</span>
        </c:if>
        <br>
        <input id="password" name="password" class="field" type="password"><br>
        <label for="newpassword">${accountNewPassword}</label><br>
        <input id="newpassword" name="newPassword" class="field" type="password"><br>
        <label for="newpassword1">${accountRepeatedNewPassword}</label><br>
        <input id="newpassword1" name="newPassword1" class="field" type="password"><br>
        <label for="money">${accountMoney}</label><br>
        <input id="money" name="money" class="field" type="number" min="1000" value="${bidder.getMoney()}"><br>
        <label for="age">${accountAge}</label><br>
        <input id="age" name="age" class="field" type="number" min="18" max="120" value="${bidder.getAge()}"><br>
        <label for="docName">${accountDocumentData}</label><br>
        <input id="docName" name="docName" type="text" class="field" value="${bidder.getDocumentNumber()}"><br>
        <label for="country">${accountCountry}</label><br>
        <input id="country" name="country" type="text" class="field" value="${bidder.getCountry()}"><br>
        <label for="fullAddress">${accountFullAddress}</label><br>
        <input id="fullAddress" name="fullAddress" type="text" class="field" value="${bidder.getFullAddress()}"><br>
        <label for="email">${accountEmail}</label><br>
        <input id="email" name="email" type="text" class="field" value="${bidder.getEmail()}"><br>
        <input type="submit" class="submit" value="${accountSave}">
    </form>
</div>

</body>
</html>