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
        <div class="table-flex">
            <div class="flex-item header">
                <span><fmt:message key="catalog"/></span>
            </div>
            <form id="subSearch">
                <c:if test="${param.order != null}">
                    <input type="hidden" name="order" value="${param.order}">
                </c:if>
                <c:if test="${param.size != null}">
                    <input type="hidden" name="size" value="${param.size}">
                </c:if>
                <input type="text" name="name" value="${param.masterName}" placeholder="<fmt:message key="search"/>">
                <select itemtype="text" name="type" size="1">
                    <option value="masterName"><fmt:message key="masterName"/></option>
                    <option value="serviceName"><fmt:message key="serviceName"/></option>
                </select>
            </form>
            <form id="pageSize">
                <c:if test="${param.name != null}">
                    <input type="hidden" name="name" value="${param.name}">
                </c:if>
                <c:if test="${param.order != null}">
                    <input type="hidden" name="order" value="${param.order}">
                </c:if>
                <c:if test="${param.type != null}">
                    <input type="hidden" name="type" value="${param.type}">
                </c:if>
                <input type="number" name="size" value="${param.size}" min="1" placeholder="<fmt:message key="size"/>">
            </form>
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
                <c:if test="${param.masterName != null}">
                    <c:param name="masterName" value="${catalog.master.name}"/>
                </c:if>
                </c:url>
        </div>
        <div class="flex-container wrap">
            <div class="table-flex">
                <div class="flex-container table-tr">
                    <div class="flex-item"><a href="<c:out value="${sortName}"/>"/><fmt:message key="masterName"/></a>
                    </div>
                    <div class="flex-item"><a href="<c:out value="${sortRating}"/>"/><fmt:message
                            key="serviceName"/></a>
                    </div>
                    <div class="flex-item"><fmt:message key="serviceDuration"/></div>
                    <div class="flex-item"><fmt:message key="servicePrice"/></div>
                </div>
                <c:forEach var="catalog" items="${catalogs}">
                    <div class="flex-container wrap">
                        <div class="flex-item">${catalog.master.name}</div>
                        <div class="flex-item">${catalog.service.name}</div>
                        <div class="flex-item">${catalog.service.serviceDuration}</div>
                        <div class="flex-item">${catalog.service.servicePrice}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    </body>
    </html>