<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Error"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="errorBlock">

    <c:set var="rand"><%= (int) (Math.random() * 4) + 1 %></c:set>
    <span>${errorPage} - </span>
    <c:choose>
        <c:when test="${errorPage == 401}">
            <a href="/login"><fmt:message key="signin"/></a>
        </c:when>
        <c:when test="${errorPage == 403}">
            <c:if test="${user.role eq 'CLIENT'}">
                <a href="/client/client-home"><fmt:message key="homepage"/></a>
            </c:if>
            <c:if test="${user.role eq 'ADMIN'}">
                <a href="/admin/admin-home"><fmt:message key="homepage"/></a>
            </c:if>
            <c:if test="${user.role eq 'MASTER'}">
                <a href="/master/master-home"><fmt:message key="homepage"/> </a>
            </c:if>
        </c:when>
        <c:when test="${errorPage == 404}">
            <a href="" onclick="history.back(); return false;"><fmt:message key="goback"/></a>
        </c:when>
        <c:otherwise>
            <fmt:message key="sorry"/>
            <a href="" onclick="history.back(); return false;"><fmt:message key="goback"/></a>
        </c:otherwise>
    </c:choose>
    <br>
    <c:choose>
        <c:when test="${rand == 1}">
            <img src="/img/error1.png">
        </c:when>
        <c:when test="${rand == 2}">
            <img src="/img/error2.png">
        </c:when>
        <c:when test="${rand == 3}">
            <img src="/img/error3.png">
        </c:when>
        <c:when test="${rand == 4}">
            <img src="/img/error4.png">
        </c:when>
    </c:choose>
</div>
</body>
</html>