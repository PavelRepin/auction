<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>Rich only lots</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminPage.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/pagination.css">
    <fmt:setLocale scope="session" value="${sessionScope.local}"/>
    <fmt:setBundle basename="local.local" scope="session" var="loc"/>
    <fmt:message bundle="${loc}" key="lotsPaginationPrev" var="lotsPaginationPrev"/>
    <fmt:message bundle="${loc}" key="lotsPaginationNext" var="lotsPaginationNext"/>
    <fmt:message bundle="${loc}" key="adminBidders" var="adminBidders"/>
    <fmt:message bundle="${loc}" key="adminId" var="adminId"/>
    <fmt:message bundle="${loc}" key="adminUnlock" var="adminUnlock"/>
    <fmt:message bundle="${loc}" key="adminLock" var="adminLock"/>
    <fmt:message bundle="${loc}" key="adminRemoveRights" var="adminRemoveRights"/>
    <fmt:message bundle="${loc}" key="adminSetRights" var="adminSetRights"/>
    <fmt:message bundle="${loc}" key="adminSuggestions" var="adminSuggestions"/>
    <fmt:message bundle="${loc}" key="adminStartPrice" var="adminStartPrice"/>
    <fmt:message bundle="${loc}" key="adminDescription" var="adminDescription"/>
    <fmt:message bundle="${loc}" key="adminAccept" var="adminAccept"/>
    <fmt:message bundle="${loc}" key="adminReject" var="adminReject"/>
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

<div class="adminPage">
    <div class="inline">
        <h3>${adminBidders}</h3><br>
        <c:forEach items="${bidders}" var="oneBidder" varStatus="counter">
            <div class="oneBidder">
                <p>${counter.count}) ${oneBidder.getLogin()}${adminId} ${oneBidder.getId()}.</p>
                <c:choose>
                    <c:when test="${oneBidder.isBlocked()}">
                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="lockUnlockBidder">
                            <input type="hidden" name="idBidder" value="${oneBidder.getId()}">
                            <input type="hidden" name="isBlocked" value="${oneBidder.isBlocked()}">
                            <input type="submit" value="${adminUnlock}" class="submit">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="lockUnlockBidder">
                            <input type="hidden" name="idBidder" value="${oneBidder.getId()}">
                            <input type="hidden" name="isBlocked" value="${oneBidder.isBlocked()}">
                            <input type="submit" value="${adminLock}" class="submit">
                        </form>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${oneBidder.isAdmin()}">
                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="setRemoveAdminRights">
                            <input type="hidden" name="idBidder" value="${oneBidder.getId()}">
                            <input type="hidden" name="isAdmin" value="${oneBidder.isAdmin()}">
                            <input type="submit" value="${adminRemoveRights}" class="submit">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="controller" method="post" accept-charset="UTF-8">
                            <input type="hidden" name="command" value="setRemoveAdminRights">
                            <input type="hidden" name="idBidder" value="${oneBidder.getId()}">
                            <input type="hidden" name="isAdmin" value="${oneBidder.isAdmin()}">
                            <input type="submit" value="${adminSetRights}" class="submit">
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
        <div class="pagination">
            <%@include file="/jsp/paginationForAdminPageBidders.jsp" %>
        </div>
    </div>

    <div class="inline" id="secondInline">
        <h3>${adminSuggestions}</h3><br>
        <c:forEach items="${suggestions}" var="suggestion" varStatus="counter">
            <div class="oneSuggestion">
                <p>${counter.count}) ${suggestion.getName()}${adminId} ${suggestion.getId()}${adminStartPrice} ${suggestion.getStartPrice()},<br>
                ${adminDescription} ${suggestion.getDescription()}.</p>
                <form action="controller" method="post" accept-charset="utf-8">
                    <input type="hidden" name="command" value="acceptRejectLot">
                    <input type="hidden" name="idLot" value="${suggestion.getId()}">
                    <input type="hidden" name="idBidder" value="${suggestion.getOwnerBidder()}">
                    <input type="hidden" name="acceptReject" value="accept">
                    <input type="submit" value="${adminAccept}" class="submit">
                </form>
                <form action="controller" method="post" accept-charset="utf-8">
                    <input type="hidden" name="command" value="acceptRejectLot">
                    <input type="hidden" name="idLot" value="${suggestion.getId()}">
                    <input type="hidden" name="idBidder" value="${suggestion.getOwnerBidder()}">
                    <input type="hidden" name="acceptReject" value="reject">
                    <input type="submit" value="${adminReject}" class="submit">
                </form>
            </div>
        </c:forEach>
        <div class="pagination">
            <%@include file="/jsp/paginationForAdminPageSuggestions.jsp" %>
        </div>
    </div>
</div>
</body>
</html>