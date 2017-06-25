<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale scope="session" value="${sessionScope.local}"/>
<fmt:setBundle basename="local.local" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="headerAbout" var="headerAbout"/>
<fmt:message bundle="${loc}" key="headerHelp" var="headerHelp"/>
<fmt:message bundle="${loc}" key="headerUserAccount" var="headerUserAccount"/>
<fmt:message bundle="${loc}" key="headerSignOut" var="headerSignOut"/>
<fmt:message bundle="${loc}" key="headerHello" var="headerHello"/>
<fmt:message bundle="${loc}" key="headerRuButton" var="headerRuButton"/>
<fmt:message bundle="${loc}" key="headerEnButton" var="headerEnButton"/>
<fmt:message bundle="${loc}" key="headerMainPage" var="headerMainPage"/>
<fmt:message bundle="${loc}" key="mainAdminPage" var="mainAdminPage"/>

<div class="container">
    <div class="preheader">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="goToCartPage"/>
            <input type="submit" value="" id="cart" class="cart" hidden="hidden">
            <label for="cart">
                <img height="23px" width="auto" alt="ShoppingBasket"
                     src="${pageContext.request.contextPath}/images/shopping_cart.png">
            </label>
        </form>
    </div>

    <div class="preheaderTextItem">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="about"/>
            <label for="about" class="ref">${headerAbout}</label>
            <input type="submit" id="about" hidden="hidden">
        </form>
    </div>

    <div class="preheaderTextItem">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="goToHelpPage"/>
            <label for="help" class="ref">${headerHelp}</label>
            <input type="submit" id="help" hidden="hidden">
        </form>
    </div>

    <div class="preheaderTextItem">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="goToUserAccountPage"/>
            <label for="userAcc" class="ref">${headerUserAccount}</label>
            <input type="submit" id="userAcc" hidden="hidden">
        </form>
    </div>

    <div class="preheaderTextItem" id="blackLine">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="signOut"/>
            <input type="hidden" name="local" value="${local}">
            <label for="signOut" class="ref">${headerSignOut}</label>
            <input type="submit" id="signOut" hidden="hidden">
        </form>
    </div>

    <div class="preheaderTextItem">
        ${headerHello},&nbsp;
        <div class="preheaderTextItem">
            <form action="controller" method="post" accept-charset="UTF-8">
                <input type="hidden" name="command" value="goToUserAccountPage"/>
                <label for="loginName" class="ref">${login}!</label>
                <input type="submit" id="loginName" hidden="hidden">
            </form>
        </div>
    </div>

    <div class="preheaderTextItem">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="changeLocal"/>
            <input type="hidden" name="local" value="ru">
            <label for="ruButton" class="ref">${headerRuButton}</label>
            <input type="submit" id="ruButton" hidden="hidden">
        </form>
    </div>

    <div class="preheaderTextItem">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="changeLocal"/>
            <input type="hidden" name="local" value="en">
            <label for="enButton" class="ref">${headerEnButton}</label>
            <input type="submit" id="enButton" hidden="hidden">
        </form>
    </div>

    <c:if test="${bidder.isAdmin()}">
        <div class="preheaderTextItem">
            <form action="controller" method="post" accept-charset="UTF-8">
                <input type="hidden" name="command" value="goToAdminPage"/>
                <input type="hidden" name="paginationSuggestions" value="1">
                <input type="hidden" name="paginationBidders" value="1">
                <label for="adminPage" class="ref">${mainAdminPage}</label>
                <input type="submit" id="adminPage" hidden="hidden">
            </form>
        </div>
    </c:if>

    <div id="tomain">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="goToMainPage"/>
            <label for="mainPage" class="ref">${headerMainPage}</label>
            <input type="submit" id="mainPage" hidden="hidden">
        </form>
    </div>

</div>
<hr>