<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Rich Only</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cart.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination.css">

    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="cartTitle" var="cartTitle"/>
    <fmt:message bundle="${loc}" key="cartSuggested" var="cartSuggested"/>
    <fmt:message bundle="${loc}" key="cartStartPrice" var="cartStartPrice"/>
    <fmt:message bundle="${loc}" key="cartCancelButton" var="cartCancelButton"/>
    <fmt:message bundle="${loc}" key="cartOwn" var="cartOwn"/>
    <fmt:message bundle="${loc}" key="cartStartPrice1" var="cartStartPrice1"/>
    <fmt:message bundle="${loc}" key="cartFinalPrice" var="cartFinalPrice"/>
    <fmt:message bundle="${loc}" key="cartDescribe" var="cartDescribe"/>
    <fmt:message bundle="${loc}" key="cartPhDesc" var="cartPhDesc"/>
    <fmt:message bundle="${loc}" key="cartNewStartPrice" var="cartNewStartPrice"/>
    <fmt:message bundle="${loc}" key="cartPhStartPrice" var="cartPhStartPrice"/>
    <fmt:message bundle="${loc}" key="cartSuggest" var="cartSuggest"/>
    <fmt:message bundle="${loc}" key="cartBargaining" var="cartBargaining"/>
    <fmt:message bundle="${loc}" key="cartCurrentPrice" var="cartCurrentPrice"/>
    <fmt:message bundle="${loc}" key="cartInfoMsg" var="cartInfoMsg"/>
    <fmt:message bundle="${loc}" key="cartInfoMsg2" var="cartInfoMsg2"/>
    <fmt:message bundle="${loc}" key="cartBid" var="cartBid"/>
    <fmt:message bundle="${loc}" key="cartSelling" var="cartSelling"/>
    <fmt:message bundle="${loc}" key="cartLatestBid" var="cartLatestBid"/>
    <fmt:message bundle="${loc}" key="suggestionHours" var="suggestionHours"/>
    <fmt:message bundle="${loc}" key="cartBlocked" var="cartBlocked"/>
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

<%--owner       seller
    id          id      ===>    item is not suggested and is not selling, it is just a bidder's property
    --------------------------------------------------------------------
    owner       seller
    id          0    ===>    item is suggeted but not selling
    --------------------------------------------------------------------
    owner       seller
    0        id      ===>    item is selling but not suggested already

    owner       seller
    0           0
    --%>
<br>

<div class="block1">
    <h2 id="title">${cartTitle}</h2><br>

    <div class="inline">
        <div class="suggested">

            <h3>${cartSuggested}</h3><br>
            <c:forEach items="${lots}" var="lot">
                <c:if test="${lot.getOwnerBidder() eq (idBidder) && lot.getSellerBidder() eq (0)}">
                    <div>
                        <p>${lot.getName()}${cartStartPrice} ${lot.getStartPrice()}.</p>

                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="cancelSuggestion">
                            <input type="hidden" name="idLot" value="${lot.getId()}">
                            <input type="hidden" name="idBidder" value="${idBidder}">
                            <input type="submit" value="${cartCancelButton}" class="submit">
                        </form>
                    </div>
                    <br>
                </c:if>
            </c:forEach><br>
        </div>
    </div>

    <div class="inline" id="secondInline">
        <div class="selling">
            <h3>${cartSelling}</h3><br>

            <c:forEach items="${lots}" var="lot">
                <c:if test="${lot.getOwnerBidder() eq (0) && lot.getSellerBidder() eq (idBidder)}">
                    <div>
                        <p>${lot.getName()}${cartStartPrice} ${lot.getStartPrice()}
                                ${cartCurrentPrice} ${lot.getCurrentPrice()}. ${cartLatestBid} ${lot.getLastBidder()}.</p>
                    </div>
                    <br>
                </c:if>
            </c:forEach><br>
        </div>
    </div>
</div>

<div class="block2">
    <div class="inline">
        <div class="auction">
            <h3>${cartBargaining}</h3><br>

            <c:forEach items="${lots}" var="lot">
                <c:if test="${!(lot.getOwnerBidder() eq (idBidder)) and !(lot.getSellerBidder() eq (idBidder))}">
                    <div>
                        <p>${lot.getName()}${cartStartPrice1} ${lot.getStartPrice()}${cartCurrentPrice} ${lot.getCurrentPrice()}.</p>
                        <br>
                        <c:choose>
                            <c:when test="${lot.getLastBidder() eq (idBidder)}">
                                <p>${cartInfoMsg}</p>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${bidder.isBlocked()}">
                                        <p>${cartBlocked}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>${cartInfoMsg2}</p>

                                        <form action="controller" method="post" accept-charset="UTF-8">
                                            <input type="hidden" name="command" value="bidFromCartPage">
                                            <input type="hidden" name="idLot" value="${lot.getId()}">
                                            <input type="hidden" name="idBidder" value="${idBidder}">
                                            <input type="submit" value="${cartBid}" class="submit">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <br>
                </c:if>
            </c:forEach><br>
        </div>
    </div>

    <div class="inline" id="fourthInline">
        <div class="own">
            <h3>${cartOwn}</h3><br>

            <c:forEach items="${lots}" var="lot">
                <c:if test="${lot.getOwnerBidder() eq (idBidder) && !(lot.getSellerBidder() eq (0))}">
                    <div>
                        <p>${lot.getName()}${cartStartPrice1} ${lot.getStartPrice()} ${cartFinalPrice} ${lot.getCurrentPrice()}.</p>

                        <br>

                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="suggestFromCart">
                            <input type="hidden" name="idLot" value="${lot.getId()}">
                            <label for="description">${cartDescribe}</label><br>
                            <input type="text" id="description" name="description" class="field" required
                                   placeholder="${cartPhDesc}"><br>
                            <label for="startPrice">${cartNewStartPrice}</label><br>
                            <input type="number" id="startPrice" min="100" name="startPrice" class="field" required
                                   placeholder="${cartPhStartPrice}"><br>
                            <label for="hours">${suggestionHours}</label><br>
                            <input id="hours" type="number" min="1" name="hours" class="field" required
                                   placeholder="${suggestionHoursPh}"><br>
                            <input type="submit" value="${cartSuggest}" class="submit">
                        </form>
                    </div>
                    <br>
                </c:if>
            </c:forEach><br>
        </div>
    </div>
</div>
</body>
</html>
