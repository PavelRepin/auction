<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width">
  <title>Rich only</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/error.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
  <fmt:setLocale scope="session" value="${sessionScope.local}"/>
  <fmt:setBundle basename="local.local" scope="session" var="loc"/>
  <fmt:message bundle="${loc}" key="errorTitle" var="errorTitle"/>
  <fmt:message bundle="${loc}" key="errorText1" var="errorText1"/>
  <fmt:message bundle="${loc}" key="errorText2" var="errorText2"/>
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

<div class="inline">
  <img id="startpic" alt="Error" src="${pageContext.request.contextPath}/images/error.png">
</div>

<div class="inline" id="error">
  <h2>${errorTitle}</h2><br>
  <p>${errorText1}<br>
    ${errorText2}</p>
</div>

</body>
</html>