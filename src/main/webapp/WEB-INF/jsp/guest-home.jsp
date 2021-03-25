<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Guest Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="guest"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
    </div>
    <div id="users" class="tableInfo">
        <hr>
        <div style="display:block; text-align:center; ">
            <span><fmt:message key="catalog"/></span>
        </div>
        <form id="subSearch">
            <c:if test="${param.order != null}">
                <input type="hidden" name="order" value="${param.order}">
            </c:if>
            <input type="text" name="name" value="${param.masterName}" placeholder="<fmt:message key="masterName"/>">
            <select itemtype="text" name="type" size="1">
                <option value="masterName"><fmt:message key="masterName"/></option>
                <option value="serviceName"><fmt:message key="serviceName"/></option>
            </select>
        </form>
        <table style="width: -webkit-fill-available;">
            <c:url value="/guest/guest-home" var="sortName">
                <c:set var="ascDesc" value="DESC"/>
                <c:choose>
                    <c:when test="${param.order == 'u.name,ASC'}">
                        <c:set var="ascDesc" value="DESC"/>
                    </c:when>
                    <c:when test="${param.order == 'u.name,DESC'}">
                        <c:set var="ascDesc" value="ASC"/>
                    </c:when>
                </c:choose>
                <c:param name="order" value="u.name,${ascDesc}"/>
                <c:if test="${catalog.master.name != null}">
                    <c:param name="masterName" value="${catalog.master.name}"/>
                </c:if>
            </c:url>

            <c:url value="/guest/guest-home" var="sortRating">
                <c:set var="ascDesc" value="ASC"/>
                <c:choose>
                    <c:when test="${param.order == 'avg_rating,ASC'}">
                        <c:set var="ascDesc" value="DESC"/>
                    </c:when>
                    <c:when test="${param.order == 'avg_rating,DESC'}">
                        <c:set var="ascDesc" value="ASC"/>
                    </c:when>
                </c:choose>
                <c:param name="order" value="avg_rating,${ascDesc}"/>
                <c:if test="${param.subjectName != null}">
                    <c:param name="masterName" value="${catalog.master.name}"/>
                </c:if>
            </c:url>

            <tr>
                <th><fmt:message key="id"/></th>
                <th><a href="<c:out value="${sortName}"/>"/> <fmt:message key="masterName"/></th>
                <th><a href="<c:out value="${sortRating}"/>" /> <fmt:message key="rating"/> </th>
                <th><fmt:message key="serviceName"/></th>
                <th><fmt:message key="serviceDuration"/></th>
                <th><fmt:message key="servicePrice"/></th>
                <th></th>
            </tr>
            <c:forEach var="catalog" items="${catalogs}">
                <tr>
                    <td>${catalog.id}</td>
                    <td>${catalog.master.name}</td>
                    <td>${catalog.master.rating}</td>
                    <td>${catalog.service.name}</td>
                    <td>${catalog.service.serviceDuration}</td>
                    <td>${catalog.service.servicePrice}</td>
                </tr>
            </c:forEach>
        </table>
        <hr>
    </div>
    </body>
    </html>