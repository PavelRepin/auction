<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Rich Only</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/suggestion.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="suggestionTitle" var="suggestionTitle"/>
    <fmt:message bundle="${loc}" key="suggestionLotsName" var="suggestionLotsName"/>
    <fmt:message bundle="${loc}" key="suggestionLotNamePh" var="suggestionLotNamePh"/>
    <fmt:message bundle="${loc}" key="suggestionCategory" var="suggestionCategory"/>
    <fmt:message bundle="${loc}" key="suggestionChoose" var="suggestionChoose"/>
    <fmt:message bundle="${loc}" key="mainButton2" var="mainButton2"/>
    <fmt:message bundle="${loc}" key="mainButton3" var="mainButton3"/>
    <fmt:message bundle="${loc}" key="mainButton4" var="mainButton4"/>
    <fmt:message bundle="${loc}" key="mainButton5" var="mainButton5"/>
    <fmt:message bundle="${loc}" key="mainButton6" var="mainButton6"/>
    <fmt:message bundle="${loc}" key="mainButton7" var="mainButton7"/>
    <fmt:message bundle="${loc}" key="mainButton8" var="mainButton8"/>
    <fmt:message bundle="${loc}" key="mainButton9" var="mainButton9"/>
    <fmt:message bundle="${loc}" key="mainButton10" var="mainButton10"/>
    <fmt:message bundle="${loc}" key="mainButton11" var="mainButton11"/>
    <fmt:message bundle="${loc}" key="suggestionLotAuthor" var="suggestionLotAuthor"/>
    <fmt:message bundle="${loc}" key="suggestionYear" var="suggestionYear"/>
    <fmt:message bundle="${loc}" key="suggestionDescription" var="suggestionDescription"/>
    <fmt:message bundle="${loc}" key="suggestionStartPrice" var="suggestionStartPrice"/>
    <fmt:message bundle="${loc}" key="suggestionImage" var="suggestionImage"/>
    <fmt:message bundle="${loc}" key="suggestionAuthorPh" var="suggestionAuthorPh"/>
    <fmt:message bundle="${loc}" key="suggestionYearPh" var="suggestionYearPh"/>
    <fmt:message bundle="${loc}" key="suggestionDescribePh" var="suggestionDescribePh"/>
    <fmt:message bundle="${loc}" key="suggestionStartPricePh" var="suggestionStartPricePh"/>
    <fmt:message bundle="${loc}" key="suggestionSuggest" var="suggestionSuggest"/>
    <fmt:message bundle="${loc}" key="suggestionHours" var="suggestionHours"/>
    <fmt:message bundle="${loc}" key="suggestionHoursPh" var="suggestionHoursPh"/>

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

<div class="suggestion">
    <h3>${suggestionTitle}</h3><br>

    <form action="controller" method="post" accept-charset="UTF-8" enctype="multipart/form-data">
        <input type="hidden" name="command" value="suggest">
        <label for="name">${suggestionLotsName}</label><br>
        <input id="name" type="text" name="name" class="field" required placeholder="${suggestionLotNamePh}"><br>
        ${suggestionCategory}<br>
        <select name="category" class="field" required>
            <option selected value="Other">${mainButton11}</option>
            <option value="Painting">${mainButton2}</option>
            <option value="Architecture">${mainButton3}</option>
            <option value="Sculpture">${mainButton4}</option>
            <option value="Clothes">${mainButton5}</option>
            <option value="Realty">${mainButton6}</option>
            <option value="Instrument">${mainButton7}</option>
            <option value="Antiques">${mainButton8}</option>
            <option value="Equipment">${mainButton9}</option>
            <option value="Cars">${mainButton10}</option>
        </select><br>
        <label for="author">${suggestionLotAuthor}</label><br>
        <input id="author" type="text" name="author" class="field" required placeholder="${suggestionAuthorPh}"><br>
        <label for="year">${suggestionYear}</label><br>
        <input id="year" type="number" max="2017" class="field" name="year" required placeholder="${suggestionYearPh}"><br>
        <label for="description">${suggestionDescription}</label><br>
        <input id="description" type="text" name="description" class="field" required placeholder="${suggestionDescribePh}"><br>
        <label for="startPrice">${suggestionStartPrice}</label><br>
        <input id="startPrice" type="number" min="100" name="startPrice" class="field" required placeholder="${suggestionStartPricePh}"><br>
        <label for="hours">${suggestionHours}</label><br>
        <input id="hours" type="number" min="1" max="72" name="hours" class="field" required placeholder="${suggestionHoursPh}"><br>
        <label for="picture">${suggestionImage}</label><br>
        <input id="picture" type="file" name="image" accept="image/*"><br>
        <input type="hidden" name="idBidder" value="${idBidder}">
        <input type="submit" value="${suggestionSuggest}" class="submit">

    </form>
</div>

</body>
</html>
