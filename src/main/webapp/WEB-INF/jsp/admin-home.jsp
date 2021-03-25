<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Admin Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="admin"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
        <span><fmt:message key="email"/>: ${user.email}</span><br>
        <span><fmt:message key="acctype"/>: ${user.role}</span><br>
    </div>
    <div id="users" class="tableInfo">
        <table>
            <hr>
            <a href="/admin/create-user" id="add"><i class="fas fa-plus-circle"></i></a>
            <div style="display:block; text-align:center; ">
                <span><fmt:message key="users"/></span>
            </div>
            <table style="width: -webkit-fill-available;">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="username"/></th>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="acctype"/></th>
                    <th><fmt:message key="block"/></th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.role}</td>
                        <td>
                            <form method="post" action="/admin/user-update-enabled" id="lockForm">
                                <fmt:message key="enabled" var="enabled"/>
                                <fmt:message key="disabled" var="disabled"/>
                                <input name="userId" type="hidden" value="${user.id}">
                                <input name="enabled" type="hidden" value="${!user.enabled}">
                                <input type="submit" value="${user.enabled ? disabled : enabled}">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <hr>

            <table>
                <c:url value="/admin/admin-home" var="sortAmount">
                    <c:set var="ascDesc" value="ASC"/>
                <c:choose>
                <c:when test="${param.order == 't.id,ASC'}">
                    <c:set var="ascDesc" value="DESC"/>
                </c:when>
                <c:when test="${param.order == 't.id,DESC'}">
                    <c:set var="ascDesc" value="ASC"/>
                </c:when>
                </c:choose>
                    <c:param name="order" value="t.id,${ascDesc}"/>
                <c:if test="${param.id != null}">
                    <c:param name="id" value="${param.id}"/>
                </c:if>
                </c:url>

                <c:url value="/admin/admin-home" var="sortDate">
                    <c:set var="ascDesc" value="ASC"/>
                <c:choose>
                <c:when test="${param.order == 't.createDate,ASC'}">
                    <c:set var="ascDesc" value="DESC"/>
                </c:when>
                <c:when test="${param.order == 't.createDate,DESC'}">
                    <c:set var="ascDesc" value="ASC"/>
                </c:when>
                </c:choose>
                    <c:param name="order" value="t.createDate,${ascDesc}"/>
                <c:if test="${param.status != null}">
                    <c:param name="createDate" value="${param.createDate}"/>
                </c:if>
                </c:url>

                <c:url value="/admin/admin-home" var="sortStatus">
                    <c:set var="ascDesc" value="ASC"/>
                <c:choose>
                <c:when test="${param.order == 't.status,ASC'}">
                    <c:set var="ascDesc" value="DESC"/>
                </c:when>
                <c:when test="${param.order == 't.status,DESC'}">
                    <c:set var="ascDesc" value="ASC"/>
                </c:when>
                </c:choose>
                    <c:param name="order" value="t.status,${ascDesc}"/>
                <c:if test="${param.status != null}">
                    <c:param name="status" value="${param.status}"/>
                </c:if>
                </c:url>
            </table>
            <a href="/admin/create-service" id="add"><i class="fas fa-plus-circle"></i></a>
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
                            <a type="submit" name="check"create-catalog?serviceId=${service.id}&masterId=${user.id}';
                               onclick="window.location='/admin/
                                       return false"
                               id="edit">
                                <i class="fa fa-check" aria-hidden="true"></i>
                            </a>
                        </td>
                        <td>
                            <a type="submit" name="settings"
                               onclick="window.location='/admin/edit-service?id=${service.id}'" id="edit">
                                <i class="fa fa-cog" aria-hidden="true"></i>
                            </a>
                        </td>
                        <td>
                            <a type="submit" name="delete"
                               onclick="window.location='/admin/delete-service?id=${service.id}'"
                               id="edit">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </a>
                        </td>
                    </tr>

                </c:forEach>
            </table>

    <hr>
            <div style="display:block; text-align:center; ">
                <span><fmt:message key="catalog"/></span>
            </div>
            <table style="width: -webkit-fill-available;">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="masterName"/></th>
                    <th><fmt:message key="serviceName"/></th>
                    <th><fmt:message key="serviceDuration"/></th>
                    <th><fmt:message key="servicePrice"/></th>
                    <th></th>
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
                            <a type="submit" name="check"
                               onclick="window.location='/admin/create-appointment?catalogId=${catalog.id}&clientId=${user.id}&serviceName=${catalog.service.name}';
                                       return false"
                               id="edit">
                                <i class="fa fa-check" aria-hidden="true"></i>
                            </a>
                        </td>
                        <td>
                            <a type="submit" name="delete"
                               onclick="window.location='/admin/delete-catalog?id=${catalog.id}&role=${user.role}'"
                               id="edit">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

                    <hr>
                    <div style="display:block; text-align:center; ">
                        <span><fmt:message key="appointments"/></span>
                    </div>
                    <table style="width: -webkit-fill-available;">
                        <tr>
                            <th><fmt:message key="customerName"/></th>
                            <th><fmt:message key="serviceName"/></th>
                            <th><fmt:message key="masterName"/></th>
                            <th><fmt:message key="date"/></th>
                            <th><fmt:message key="status"/> </th>
                            <th></th>
                        </tr>
                        <c:forEach var="appointment" items="${appointments}">
                            <tr>
                                <td>${appointment.client.name}</td>
                                <td>${appointment.catalog.service.name}</td>
                                <td>${appointment.catalog.master.name}</td>
                                <td><fmt:parseDate value="${ appointment.localDateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }" /></td>
                                <td>${appointment.status}</td>
                                <td>
                                    <a type="submit" name="settings"
                                       onclick="window.location='/admin/edit-appointment?appointmentId=${appointment.id}&role=${user.role}'" id="edit">
                                        <i class="fa fa-cog" aria-hidden="true"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
    </div>
    </body>
</html>