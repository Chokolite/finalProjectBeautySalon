<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="title" value="Sign-Up/Login Form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags"%>

<la:lang />

<div id="login">
    <form action="/login" method="post">
        <p>
            <c:if test="${not empty errors}">
                <fmt:message key="${errors['loginError']}"/>
                <br>
            </c:if>
        </p>

        <fieldset class="clearfix">
            <p><span class="fontawesome-user"></span><input type="email" name="email" placeholder="<fmt:message key="email"/>" required></p>
            <p><span class="fontawesome-lock"></span><input type="password" name="password" placeholder="<fmt:message key="password"/>"
                                                            required></p>
            <p><input type="submit" name="submit" value="<fmt:message key="signin"/>"></p>
        </fieldset>
    </form>
    <p><fmt:message key="noacc"/>? &nbsp;&nbsp;<a href="/sign-up"><fmt:message key="signup"/></a><span class="fontawesome-arrow-right"></span></p>
    <p><fmt:message key="or"/></p>
    <p><a href="/guest/guest-home"><fmt:message key="enterLikeGuest"/> </a></p>
</div>
</body>
</html>