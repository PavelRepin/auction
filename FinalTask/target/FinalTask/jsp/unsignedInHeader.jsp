<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale scope="session" value="${sessionScope.local}"/>
<fmt:setBundle basename="local.local" scope="session" var="loc"/>
<fmt:message bundle="${loc}" key="headerAbout" var="headerAbout"/>
<fmt:message bundle="${loc}" key="headerHelp" var="headerHelp"/>
<fmt:message bundle="${loc}" key="headerUserAccount" var="headerUserAccount"/>
<fmt:message bundle="${loc}" key="headerRuButton" var="headerRuButton"/>
<fmt:message bundle="${loc}" key="headerEnButton" var="headerEnButton"/>
<fmt:message bundle="${loc}" key="headerMainPage" var="headerMainPage"/>
<fmt:message bundle="${loc}" key="headerEnterRegister" var="headerEnterRegister"/>
<fmt:message bundle="${loc}" key="headerClose" var="headerClose"/>
<fmt:message bundle="${loc}" key="headerAuthorization" var="headerAuthorization"/>
<fmt:message bundle="${loc}" key="headerAuthorizationText" var="headerAuthorizationText"/>
<fmt:message bundle="${loc}" key="headerLogin" var="headerLogin"/>
<fmt:message bundle="${loc}" key="headerEnter" var="headerEnter"/>
<fmt:message bundle="${loc}" key="headerRegister" var="headerRegister"/>
<fmt:message bundle="${loc}" key="headerRegistration" var="headerRegistration"/>
<fmt:message bundle="${loc}" key="headerRegistrationSpan1" var="headerRegistrationSpan1"/>
<fmt:message bundle="${loc}" key="headerRegistrationSpan2" var="headerRegistrationSpan2"/>
<fmt:message bundle="${loc}" key="headerTextIncor1" var="headerTextIncor1"/>
<fmt:message bundle="${loc}" key="headerPassword" var="headerPassword"/>
<fmt:message bundle="${loc}" key="headerTextIncor2" var="headerTextIncor2"/>
<fmt:message bundle="${loc}" key="headerPasswordRepeat" var="headerPasswordRepeat"/>
<fmt:message bundle="${loc}" key="headerTextIncor3" var="headerTextIncor3"/>
<fmt:message bundle="${loc}" key="headerEmail" var="headerEmail"/>
<fmt:message bundle="${loc}" key="headerTextIncor4" var="headerTextIncor4"/>
<fmt:message bundle="${loc}" key="headerAge" var="headerAge"/>
<fmt:message bundle="${loc}" key="headerTextIncor5" var="headerTextIncor5"/>
<fmt:message bundle="${loc}" key="headerTextIncor6" var="headerTextIncor6"/>
<fmt:message bundle="${loc}" key="headerTextIncor7" var="headerTextIncor7"/>
<fmt:message bundle="${loc}" key="headerAgePh" var="headerAgePh"/>
<fmt:message bundle="${loc}" key="headerRegisterButton" var="headerRegisterButton"/>
<fmt:message bundle="${loc}" key="accountWrongPassword" var="accountWrongPassword"/>
<fmt:message bundle="${loc}" key="headerWrongLogin" var="headerWrongLogin"/>

<div class="container">
    <div class="preheader">
        <a href="javascript:void(0)"
           onclick="document.getElementById('wrapper').style.display='block';document.getElementById('fade').style.display='block'">
            <img height="23px" width="auto" alt="ShoppingBasket"
                 src="${pageContext.request.contextPath}/images/shopping_cart.png">
        </a>
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
        <a href="javascript:void(0)"
           onclick="document.getElementById('wrapper').style.display='block';
           document.getElementById('fade').style.display='block'">${headerUserAccount}</a>
    </div>

    <div id="register" class="preheaderTextItem">
        <a href="javascript:void(0)"
           onclick="document.getElementById('wrapper').style.display='block';
           document.getElementById('fade').style.display='block'">${headerEnterRegister}</a>
    </div>

    <c:if test="${wrongLogin == 1}">
        <div class="preheaderTextItem" id="wrongLogin">${headerWrongLogin}</div>
    </c:if>

    <c:if test="${wrongPassword == 2}">
        <div class="preheaderTextItem" id="wrongPassword">${accountWrongPassword}</div>
    </c:if>

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

    <div id="tomain">
        <form action="controller" method="post" accept-charset="UTF-8">
            <input type="hidden" name="command" value="goToMainPage"/>
            <label for="main" class="ref">${headerMainPage}</label>
            <input type="submit" id="main" hidden="hidden">
        </form>
    </div>

    <div id="wrapper" class="wrapper">
        <a class="close-btn" href="javascript:void(0)"
           onclick="document.getElementById('wrapper').style.display='none';
           document.getElementById('fade').style.display='none'">${headerClose}</a>

        <form name="login-form" id="enter-form" class="enter-form" action="controller" accept-charset="utf-8"
              method="post">

            <div class="headerEnter">
                <h1>${headerAuthorization}</h1>
                <span>${headerAuthorizationText} </span>
            </div>

            <div class="content">
                <input name="command" type="hidden" value="signIn"/>
                <input name="login" type="text" class="input username" placeholder="${headerLogin}"/>
                <input name="password" type="password" class="input password" placeholder="*****"/>
            </div>

            <div class="footer">
                <input type="submit" value="${headerEnter}" class="wrapperButton"/>
                <a class="register" href="javascript:void(0)"
                   onclick="document.getElementById('regform').style.display='block';
                   document.getElementById('fade').style.display='block';
                   document.getElementById('wrapper').style.display='none'">${headerRegister}</a>
            </div>

        </form>

    </div>

    <div id="fade" class="black-overlay"
         onclick="document.getElementById('wrapper').style.display='none';
         document.getElementById('fade').style.display='none';document.getElementById('regform').style.display='none'"></div>

    <div id="regform" class="wrapper">
        <a id="regbtn" class="close-btn" href="javascript:void(0)"
           onclick="document.getElementById('regform').style.display='none';
           document.getElementById('fade').style.display='none'">${headerClose}</a>

        <form name="registration-form" id="registration-form" class="enter-form" action="controller"
              accept-charset="utf-8" method="post">

            <div class="content">
                <div class="h1">${headerRegistration}</div>
                <div class="span">${headerRegistrationSpan1}</div>
                <div class="span">${headerRegistrationSpan2}</div>
                <div id="incorname" class="incorrect">${headerTextIncor1}</div>
                <input name="command" type="hidden" value="signUp"/>
                <input name="login" id="username1" type="text" class="input username" placeholder="Login"/>

                <div class="span">${headerPassword}</div>
                <div id="incorpass" class="incorrect">${headerTextIncor2}</div>
                <input name="password" id="password" type="password" class="input password" placeholder="******"/>

                <div class="span">${headerPasswordRepeat}</div>
                <div id="incorpass1" class="incorrect">${headerTextIncor3}</div>
                <input name="password1" id="password1" type="password" class="input password" placeholder="******"/>

                <div class="span">${headerEmail}</div>
                <div id="incoremail" class="incorrect">${headerTextIncor4}</div>
                <input name="email" id="email" type="email" class="input email" placeholder="Email"/>

                <div class="span">${headerAge}</div>
                <div id="incoragey" class="incorrect">${headerTextIncor5}</div>
                <div id="incorageo" class="incorrect">${headerTextIncor6}</div>
                <div id="incorageempty" class="incorrect">${headerTextIncor7}</div>
                <input name="age" id="age" type="number" min="18" max="120" class="input age"
                       placeholder="${headerAgePh}"/>
            </div>

            <div class="footer">
                <input type="submit" value="${headerRegisterButton}" class="wrapperButton" onclick="return validate()"/>
            </div>

        </form>

    </div>
</div>
<hr>