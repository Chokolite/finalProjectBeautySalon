<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Master Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="master"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
        <span><fmt:message key="email"/>: ${user.email}</span><br>
        <span><fmt:message key="acctype"/>: ${user.role}</span><br>
    </div>
    <div id="users" class="tableInfo">
        <table>
            <hr>

        </table>
        <div style="display:block; text-align:center; ">
            <span><fmt:message key="services"/></span>
        </div>
        <table style="width: -webkit-fill-available;">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="serviceName"/></th>
                <th><fmt:message key="serviceDuration"/></th>
                <th><fmt:message key="servicePrice"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="service" items="${services}">
                <tr>
                    <td>${service.id}</td>
                    <td>${service.name}</td>
                    <td>${service.serviceDuration}</td>
                    <td>${service.servicePrice}</td>
                    <td></td>
                    <td>
                        <a type="submit" name="check"
                           onclick="window.location='/master/create-catalog?serviceId=${service.id}&masterId=${user.id}';
                                   return false"
                           id="edit">
                            <i class="fa fa-check" aria-hidden="true"></i>
                        </a>
                    </td>
                </tr>

            </c:forEach>
        </table>

        <hr>
        <div style="display:block; text-align:center; ">
            <span><fmt:message key="catalog"/></span>
        </div>
        <table style="align-self: auto">
            <tr>
                <th><fmt:message key="id"/></th>
                <th><fmt:message key="masterName"/></th>
                <th><fmt:message key="serviceName"/></th>
                <th><fmt:message key="serviceDuration"/></th>
                <th><fmt:message key="servicePrice"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="catalog" items="${catalogs}">
                <tr>
                    <td>${catalog.id}</td>
                    <td>${catalog.master.name}</td>
                    <td>${catalog.service.name}</td>
                    <td>${catalog.service.serviceDuration}</td>
                    <td>${catalog.service.servicePrice}</td>
                    <td></td>
                    <td>
                        <a type="submit" name="delete"
                           onclick="window.location='/master/delete-catalog?id=${catalog.id}&role=${user.role}'"
                           id="edit">
                            <i class="fa fa-trash" aria-hidden="true"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <hr>
        <div style="display:block; text-align:center; ">
            <span><fmt:message key="shelude"/></span>
        </div>
        <div action="/master/edit-appointment" method="post">
            <table style="width: -webkit-fill-available;">
                <c:forEach var="slot" items="${shelude}">
                    <tr>
                        <td><fmt:parseDate value="${slot.key}" pattern="yyyy-MM-dd'T'HH:mm"
                                           var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedDateTime}"/></td>
                        <c:forEach var="slotValue" items="${slot.value}">
                            <c:choose>
                                <c:when test="${slotValue.value eq true}">
                                    <c:forEach var="appointment" items="${appointments}">
                                        <c:if test="${slotValue.key eq appointment.localDateTime}">
                                            <td>Busy:
                                                <a type="submit" name="settings"
                                                   onclick="window.location='/master/master-edit-appointment?appointmentId=${appointment.id}&appointmentStatus=${appointment.status}&role=${user.role}'"
                                                   id="edit">
                                                    <button name="dateTime"><fmt:parseDate value="${ slotValue.key }"
                                                                                           pattern="yyyy-MM-dd'T'HH:mm"
                                                                                           var="parsedDateTime"
                                                                                           type="both"/>
                                                        <fmt:formatDate pattern="HH:mm"
                                                                        value="${ parsedDateTime }"/></button>
                                                </a>
                                            </td>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${slotValue.value eq false}">
                                    <td>Free:
                                        <button disabled name="dateTime" value="${slotValue.key}" type="submit">
                                            <fmt:parseDate value="${ slotValue.key }"
                                                           pattern="yyyy-MM-dd'T'HH:mm"
                                                           var="parsedDateTime" type="both"/>
                                            <fmt:formatDate pattern="HH:mm"
                                                            value="${ parsedDateTime }"/></button>
                                    </td>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
            <hr>
        </div>
    </div>
    </body>
    </html>