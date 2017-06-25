<%--TODO: local to each user,

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/mytaglib.tld" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Rich Only</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mainPageStyle.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="mainSearch" var="mainSearch"/>
    <fmt:message bundle="${loc}" key="mainPutButton" var="mainPutButton"/>
    <fmt:message bundle="${loc}" key="mainWelcome" var="mainWelcome"/>
    <fmt:message bundle="${loc}" key="mainHelloText1" var="mainHelloText1"/>
    <fmt:message bundle="${loc}" key="mainHelloText2" var="mainHelloText2"/>
    <fmt:message bundle="${loc}" key="mainHelloText3" var="mainHelloText3"/>
    <fmt:message bundle="${loc}" key="mainHelloText4" var="mainHelloText4"/>
    <fmt:message bundle="${loc}" key="mainAltForStock" var="mainAltForStock"/>
    <fmt:message bundle="${loc}" key="mainStock" var="mainStock"/>
    <fmt:message bundle="${loc}" key="mainPhrase" var="mainPhrase"/>
    <fmt:message bundle="${loc}" key="mainAltBuyNow" var="mainAltBuyNow"/>
    <fmt:message bundle="${loc}" key="mainStock2" var="mainStock2"/>
    <fmt:message bundle="${loc}" key="mainPhrase2" var="mainPhrase2"/>
    <fmt:message bundle="${loc}" key="mainAltForStock2" var="mainAltForStock2"/>
    <fmt:message bundle="${loc}" key="mainStock3" var="mainStock3"/>
    <fmt:message bundle="${loc}" key="mainPhrase3" var="mainPhrase3"/>
    <fmt:message bundle="${loc}" key="mainAltForStock3" var="mainAltForStock3"/>
    <fmt:message bundle="${loc}" key="mainStock4" var="mainStock4"/>
    <fmt:message bundle="${loc}" key="mainPhrase4" var="mainPhrase4"/>
    <fmt:message bundle="${loc}" key="mainAltForStock4" var="mainAltForStock4"/>
    <fmt:message bundle="${loc}" key="mainStock5" var="mainStock5"/>
    <fmt:message bundle="${loc}" key="mainPhrase5" var="mainPhrase5"/>
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
            <form action="controller" method="post">
                <button id="putforbids" name="command" value="goToPutPage">${mainPutButton}</button>
            </form>
        </div>
    </c:if>
</div>

<div class="welcome">
    <p>${mainWelcome}</p>

    <p id="helloText">${mainHelloText1}<br>
        ${mainHelloText2}<br>
        ${mainHelloText3}<br>
        ${mainHelloText4}</p>
</div>

<div id="slideshow-wrap">
    <input type="radio" id="button-1" name="controls" checked="checked"/>
    <label for="button-1"></label>
    <input type="radio" id="button-2" name="controls"/>
    <label for="button-2"></label>
    <input type="radio" id="button-3" name="controls"/>
    <label for="button-3"></label>
    <input type="radio" id="button-4" name="controls"/>
    <label for="button-4"></label>
    <input type="radio" id="button-5" name="controls"/>
    <label for="button-5"></label>
    <label for="button-1" class="arrows" id="arrow-1">></label>
    <label for="button-2" class="arrows" id="arrow-2">></label>
    <label for="button-3" class="arrows" id="arrow-3">></label>
    <label for="button-4" class="arrows" id="arrow-4">></label>
    <label for="button-5" class="arrows" id="arrow-5">></label>

    <div id="slideshow-inner">
        <ul>
            <li id="slide1">
                <img src="${pageContext.request.contextPath}/images/money_under_hammer.jpg" alt="${mainAltForStock}"/>

                <div class="description">
                    <input type="checkbox" id="show-description-1"/>
                    <label for="show-description-1" class="show-description-label">I</label>

                    <div class="description-text">
                        <h2>${mainStock}</h2>

                        <p>${mainPhrase}</p>
                    </div>
                </div>
            </li>
            <li id="slide2">
                <img src="${pageContext.request.contextPath}/images/buynow.jpg" alt="${mainAltBuyNow}"/>

                <div class="description">
                    <input type="checkbox" id="show-description-2"/>
                    <label for="show-description-2" class="show-description-label">I</label>

                    <div class="description-text">
                        <h2>${mainStock2}</h2>

                        <p>${mainPhrase2}</p>
                    </div>
                </div>
            </li>
            <li id="slide3">
                <img src="${pageContext.request.contextPath}/images/for-sale.jpg" alt="${mainAltForStock2}"/>

                <div class="description">
                    <input type="checkbox" id="show-description-3"/>
                    <label for="show-description-3" class="show-description-label">I</label>

                    <div class="description-text">
                        <h2>${mainStock3}</h2>

                        <p>${mainPhrase3}</p>
                    </div>
                </div>
            </li>
            <li id="slide4">
                <img src="${pageContext.request.contextPath}/images/cousineisland.jpg" alt="${mainAltForStock3}"/>

                <div class="description">
                    <input type="checkbox" id="show-description-4"/>
                    <label for="show-description-4" class="show-description-label">I</label>

                    <div class="description-text">
                        <h2>${mainStock4}</h2>

                        <p>${mainPhrase4}</p>
                    </div>
                </div>
            </li>
            <li id="slide5">
                <img src="${pageContext.request.contextPath}/images/supercar.jpg"
                     alt="${mainAltForStock4}"/>

                <div class="description">
                    <input type="checkbox" id="show-description-5"/>
                    <label for="show-description-5" class="show-description-label">I</label>

                    <div class="description-text">
                        <h2>${mainStock5}</h2>

                        <p>${mainPhrase5}</p>
                    </div>
                </div>
            </li>
        </ul>
    </div>
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


</body>
</html>