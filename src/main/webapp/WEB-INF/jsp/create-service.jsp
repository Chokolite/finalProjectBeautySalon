<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Service form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="createService"/></span>
    </div>
    <form action="/admin/create-service" method="post">

        <fieldset class="clearfix">
            <c:if test="${not empty errors['emailError']}">
                <fmt:message key="${errors['loginError']}"/>
            </c:if>
            <c:if test="${not empty errors['passwordError']}">
                <fmt:message key="${errors['passwordError']}"/>
            </c:if>
            <p><span class="fontawesome-file-alt"></span><input type="text" name="serviceName" placeholder="<fmt:message key="serviceName"/>" required>
            </p>
            <p><span class="fontawesome-envelope"></span><input type="text" name="serviceDuration" placeholder="<fmt:message key="serviceDuration"/>" required>
            </p>
            <p><span class="fontawesome-key"></span><input type="number" maxlength="3" name="servicePrice" placeholder="<fmt:message key="servicePrice"/>" required pattern="[0-9a-zA-Z]{1,3}">
            </p>
            <p></p>
            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>