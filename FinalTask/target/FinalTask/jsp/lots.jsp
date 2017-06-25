<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/mytaglib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Rich only lots</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/lots.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination.css">
    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="mainSearch" var="mainSearch"/>
    <fmt:message bundle="${loc}" key="mainPutButton" var="mainPutButton"/>
    <fmt:message bundle="${loc}" key="mainButton1" var="mainButton1"/>
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
    <fmt:message bundle="${loc}" key="cartStartPrice" var="cartStartPrice"/>
    <fmt:message bundle="${loc}" key="cartCurrentPrice" var="cartCurrentPrice"/>
    <fmt:message bundle="${loc}" key="lotsBid" var="lotsBid"/>
    <fmt:message bundle="${loc}" key="lotsPaginationPrev" var="lotsPaginationPrev"/>
    <fmt:message bundle="${loc}" key="lotsPaginationNext" var="lotsPaginationNext"/>
    <fmt:message bundle="${loc}" key="lotsDescription" var="lotsDescription"/>
    <fmt:message bundle="${loc}" key="lotsCategory" var="lotsCategory"/>
    <fmt:message bundle="${loc}" key="lotsAuthor" var="lotsAuthor"/>
    <fmt:message bundle="${loc}" key="lotsYear" var="lotsYear"/>
    <fmt:message bundle="${loc}" key="lotsImage" var="lotsImage"/>
    <fmt:message bundle="${loc}" key="lotsAlt" var="lotsAlt"/>
    <fmt:message bundle="${loc}" key="cartInfoMsg" var="cartInfoMsg"/>
    <fmt:message bundle="${loc}" key="cartInfoMsg2" var="cartInfoMsg2"/>
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

<div class="header">

    <div class="inline">
        <mytag:startimage/>
    </div>

    <div class="inline">
        <form action="controller" method="post" class="search" accept-charset="utf-8">
            <input name="command" type="hidden" value="search"/>
            <input type="search" name="search" placeholder="${mainSearch}" class="input"/>
            <input type="submit" value="" class="submit"/>
        </form>
    </div>

    <c:if test="${login != (null)}">
        <div class="inline">
            <button id="putforbids" name="command" value="goToPutPage">${mainPutButton}
            </button>
        </div>
    </c:if>
</div>

<div class="goods">
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="All lots"/>
        <input type="submit" value="${mainButton1}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Painting"/>
        <input type="submit" value="${mainButton2}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Architecture"/>
        <input type="submit" value="${mainButton3}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Sculpture"/>
        <input type="submit" value="${mainButton4}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Clothes"/>
        <input type="submit" value="${mainButton5}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Realty"/>
        <input type="submit" value="${mainButton6}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Instrument"/>
        <input type="submit" value="${mainButton7}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Antiques"/>
        <input type="submit" value="${mainButton8}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Equipment"/>
        <input type="submit" value="${mainButton9}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Cars"/>
        <input type="submit" value="${mainButton10}" class="button"/><br>
    </form>
    <form action="controller" method="post" accept-charset="utf-8">
        <input type="hidden" name="command" value="goToLotsPage"/><br>
        <input type="hidden" name="pagination" value="1">
        <input type="hidden" name="category" value="Other"/>
        <input type="submit" value="${mainButton11}" class="button"/><br>
    </form>
</div>

<div class="goods" id="pages">
    <c:forEach items="${lots}" var="lot" varStatus="counter">
        <div class="oneLot">
            <p>${counter.count}) ${lot.getName()}${cartStartPrice} ${lot.getStartPrice()}${cartCurrentPrice} ${lot.getCurrentPrice()}.<br>
            ${lotsDescription} ${lot.getDescription()}.<br>
            ${lotsCategory} ${lot.getCategory()}${lotsAuthor} ${lot.getAuthor()}${lotsYear} ${lot.getYear()}.</p><br>
            ${lotsImage} <img class="image" alt="${lotsAlt}" src="${pageContext.request.contextPath}/pictures/${lot.getImage()}">
            <c:if test="${lot.getOwnerBidder() eq (0) && !(lot.getSellerBidder() eq (idBidder)) && !(lot.getSellerBidder() eq (0))}">
                <c:choose>
                    <c:when test="${lot.getLastBidder() eq (idBidder)}">
                        <p>${cartInfoMsg}</p>
                    </c:when>
                    <c:otherwise>
                        <p>${cartInfoMsg2}</p>

                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="bidFromLotsPage">
                            <input type="hidden" name="idLot" value="${lot.getId()}">
                            <input type="hidden" name="idBidder" value="${idBidder}">
                            <input type="submit" value="${lotsBid}" class="submit">
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <br>
    </c:forEach>
    <div class="pagination">
        <%@include file="/jsp/paginationForLotsPage.jsp" %>
    </div>
</div>
</body>
</html>