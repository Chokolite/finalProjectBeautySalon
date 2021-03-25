<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Sign-Up/Login Form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags"%>

<la:lang />

<div id="login">
    <a href="/login" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <div class="span_sign">
        <span><fmt:message key="signup"/></span>
    </div>
    <form action="sign-up" method="post">
        <fieldset class="clearfix">
            <c:if test="${not empty errors['emailError']}">
                <fmt:message key="${errors['loginError']}"/>
            </c:if>
            <c:if test="${not empty errors['passwordError']}">
                <fmt:message key="${errors['passwordError']}"/>
            </c:if>
            <p><span class="fontawesome-user"></span><input type="text" name="name" placeholder="<fmt:message key="username"/>" required></p>
            <p><span class="fontawesome-envelope"></span><input type="email" name="email" placeholder="<fmt:message key="email"/>" required></p>
            <p><span class="fontawesome-lock"></span><input type="password" name="password" maxlength="20" placeholder="<fmt:message key="password" />"
                                                            required pattern="[0-9a-zA-Z]{6,20}"></p>
            <p><span class="fontawesome-lock"></span><input type="password" name="rePassword" maxlength="20" placeholder="<fmt:message key="repass" />"
                                                            required pattern="[0-9a-zA-Z]{6,20}"></p>
            <p><input type="submit" name="submit" value="<fmt:message key="signup"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>
