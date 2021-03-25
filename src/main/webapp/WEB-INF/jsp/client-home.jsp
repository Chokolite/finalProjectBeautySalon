<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Client Panel"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ taglib prefix="la" tagdir="/WEB-INF/tags" %>

<la:lang/>

<div id="wrapper">
    <div id="user_info">
        <div id="title_user">
            <div id="icon"><i class="fas fa-user" style="padding-top: 8px;"></i></div>
            <div id="welcome_user"><fmt:message key="client"/> ${user.name}</div>
            <a href="/logout"><i class="fas fa-sign-out-alt"></i></a>
        </div>
        <hr>
        <span><fmt:message key="email"/>: ${user.email}</span><br>
        <span><fmt:message key="acctype"/>: ${user.role}</span><br>
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
        <table style="width: -webkit-fill-available;">
            <c:url value="/client/client-home" var="sortName">
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

            <c:url value="/client/client-home" var="sortRating">
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
            <tr>
                <th><fmt:message key="id"/></th>
                <th><a href="<c:out value="${sortName}"/>"/> <fmt:message key="masterName"/></th>
                <th><a href="<c:out value="${sortRating}"/>" /> <fmt:message key="rating"/> </th>
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
                    <td>${catalog.master.rating}</td>
                    <td>${catalog.service.name}</td>
                    <td>${catalog.service.serviceDuration}</td>
                    <td>${catalog.service.servicePrice}</td>
                    <td>
                        <a type="submit" name="check"
                           onclick="window.location='/client/create-appointment?catalogId=${catalog.id}&clientId=${user.id}&serviceName=${catalog.service.name}&role=${user.role}';
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
            <span><fmt:message key="appointments"/></span>
        </div>
        <table style="width: -webkit-fill-available;">
            <tr>
                <th><fmt:message key="customerName"/></th>
                <th><fmt:message key="serviceName"/></th>
                <th><fmt:message key="masterName"/></th>
                <th><fmt:message key="date"/></th>
                <th><fmt:message key="status"/></th>
                <th></th>
            </tr>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td>${appointment.client.name}</td>
                    <td>${appointment.catalog.service.name}</td>
                    <td>${appointment.catalog.master.name}</td>
                    <td><fmt:parseDate value="${ appointment.localDateTime }" pattern="yyyy-MM-dd'T'HH:mm"
                                       var="parsedDateTime" type="both"/>
                        <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
                    <td>${appointment.status}</td>
                    <td>
                        <c:if test="${appointment.status eq 'COMPLETE'}">
                            <c:if test="${appointment.reviewId > 0}">
                            <a type="submit" name="check"
                               onclick="window.location='/client/create-review?appointmentId=${appointment.id}&clientId=${user.id}';
                                       return false"
                               id="edit">
                                <i class="fa fa-comment" aria-hidden="true"></i>
                            </a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <hr>

        <div id="pagination">
            <c:if test="${size != null}">
                <c:forEach var="i" begin="1" end="${count}">
                    <c:url value="/client/client-home" var="paginationUrl">
                        <c:if test="${size*i-size != 0}">
                            <c:param name="offset" value="${size*i-size}"/>
                        </c:if>
                        <c:if test="${size != null}">
                            <c:param name="size" value="${size}"/>
                        </c:if>
                        <c:if test="${param.type != null}">
                            <c:param name="type" value="${catalog.master.name}"/>
                        </c:if>
                        <c:if test="${param.serviceName != null}">
                            <c:param name="type" value="${catalog.service.name}"/>
                        </c:if>
                    </c:url>
                    <a href='<c:out value="${paginationUrl}" />'>${i}</a>
                </c:forEach>
            </c:if>
        </div>

    </div>
    </body>
    </html>