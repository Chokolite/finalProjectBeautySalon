<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create User form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="createUser"/></span>
    </div>
    <form action="/admin/create-user" method="post">

        <fieldset class="clearfix">
            <c:if test="${not empty errors['emailError']}">
                <fmt:message key="${errors['loginError']}"/>
            </c:if>
            <c:if test="${not empty errors['passwordError']}">
                <fmt:message key="${errors['passwordError']}"/>
            </c:if>
            <p><span class="fontawesome-file-alt"></span><input type="text" name="name" placeholder="<fmt:message key="username"/>" required>
            </p>
            <p><span class="fontawesome-envelope"></span><input type="email" name="email" placeholder="<fmt:message key="email"/>" required>
            </p>
            <p><span class="fontawesome-key"></span><input type="password" maxlength="20" name="password" placeholder="<fmt:message key="password"/>" required pattern="[0-9a-zA-Z]{6,20}">
            </p>
            <p><span class="fontawesome-key"></span><input type="password" maxlength="20" name="rePassword" placeholder="<fmt:message key="repass"/>" required pattern="[0-9a-zA-Z]{6,20}">
            </p>
            <p><span class="fontawesome-user"></span><select name="role" size="1">
                <option value="ADMIN" selected>Admin</option>
                <option value="MASTER">Master</option>
                <option value="CLIENT">Client</option>
            </select></p>
            <p></p>
            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>