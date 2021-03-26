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
            <div class="table-flex">
                <div class="flex-item header"><span><fmt:message key="users"/><a href="/admin/create-user" id="add"><i
                        class="fas fa-plus-circle"></i></a></span></div>
            <div class="flex-container wrap">
                <div class="table-flex">
                    <div class="flex-container table-tr">
                        <div class="flex-item"><fmt:message key="id"/></div>
                        <div class="flex-item"><fmt:message key="username"/></div>
                        <div class="flex-item"><fmt:message key="email"/></div>
                        <div class="flex-item"><fmt:message key="acctype"/></div>
                        <div class="flex-item"><fmt:message key="block"/></div>
                    </div>
                    <c:forEach var="user" items="${users}">
                        <div class="flex-container wrap">
                            <div class="flex-item">${user.id}</div>
                            <div class="flex-item">${user.name}</div>
                            <div class="flex-item">${user.email}</div>
                            <div class="flex-item">${user.role}</div>
                            <div class="flex-item">
                                <form method="post" action="/admin/user-update-enabled" id="lockForm">
                                    <fmt:message key="enabled" var="enabled"/>
                                    <fmt:message key="disabled" var="disabled"/>
                                    <input name="userId" type="hidden" value="${user.id}">
                                    <input name="enabled" type="hidden" value="${!user.enabled}">
                                    <input type="submit" value="${user.enabled ? disabled : enabled}">
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

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
            <div class="table-flex">
                <div class="flex-item header"><span><fmt:message key="services"/><a href="/admin/create-service"
                                                                                    id="add"><i
                        class="fas fa-plus-circle"></i></a></span></div>
            </div>


            <div class="flex-container wrap">
                <div class="table-flex">
                    <div class="flex-container table-tr">
                        <div class="flex-item"><fmt:message key="serviceName"/></div>
                        <div class="flex-item"><fmt:message key="serviceDuration"/></div>
                        <div class="flex-item"><fmt:message key="servicePrice"/></div>
                        <div class="flex-item"></div>
                    </div>
                    <c:forEach var="service" items="${services}">
                        <div class="flex-container wrap">
                            <div class="flex-item">${service.name}</div>
                            <div class="flex-item">${service.serviceDuration}</div>
                            <div class="flex-item">${service.servicePrice}</div>
                            <div class="flex-item wrap">
                                <a type="submit" name="check"
                                   onclick="window.location='/master/create-catalog?serviceId=${service.id}&masterId=${user.id}';
                                           return false"
                                   id="edit">
                                    <i class="fa fa-check" aria-hidden="true"></i>
                                </a>
                                <a type="submit" name="settings"
                                   onclick="window.location='/admin/edit-service?id=${service.id}'"
                                   id="edit">
                                    <i class="fa fa-cog" aria-hidden="true"></i>
                                </a>
                                <a type="submit" name="delete"
                                   onclick="window.location='/admin/delete-service?id=${service.id}'"
                                   id="edit">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a></div>
                        </div>

                    </c:forEach>
                </div>
            </div>

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
                    <input type="text" name="name" value="${param.masterName}"
                           placeholder="<fmt:message key="search"/>">
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
                    <input type="number" name="size" value="${param.size}" min="1"
                           placeholder="<fmt:message key="size"/>">
                </form>
                <table style="width: -webkit-fill-available;">
                    <c:url value="/master/master-home" var="sortName">
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

                    <c:url value="/master/master-home" var="sortRating">
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
                        <div class="flex-item"><a href="<c:out value="${sortName}"/>"/><fmt:message
                                key="masterName"/></a></div>
                        <div class="flex-item"><a href="<c:out value="${sortRating}"/>"/><fmt:message key="rating"/></a></div>
                        <div class="flex-item"><fmt:message key="serviceName"/></div>
                        <div class="flex-item"><fmt:message key="serviceDuration"/></div>
                        <div class="flex-item"><fmt:message key="servicePrice"/></div>
                    </div>
                    <c:forEach var="catalog" items="${catalogs}">
                        <div class="flex-container wrap">
                            <div class="flex-item">${catalog.master.name}</div>
                            <div class="flex-item">${catalog.master.rating}</div>
                            <div class="flex-item">${catalog.service.name}</div>
                            <div class="flex-item">${catalog.service.serviceDuration}</div>
                            <div class="flex-item">${catalog.service.servicePrice}</div>
                            <div class="flex-item">
                                <a type="submit" name="check"
                                   onclick="window.location='/admin/create-appointment?catalogId=${catalog.id}&clientId=${user.id}&serviceName=${catalog.service.name}';
                                           return false"
                                   id="edit">
                                    <i class="fa fa-check" aria-hidden="true"></i>
                                </a>
                                <a type="submit" name="delete"
                                   onclick="window.location='/admin/delete-catalog?id=${catalog.id}&role=${user.role}'"
                                   id="edit">
                                    <i class="fa fa-trash" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="table-flex">
                <div class="flex-item header">
                    <span><fmt:message key="appointments"/></span>
                </div>
            </div>
            <div class="flex-container wrap">
                <div class="table-flex">
                    <div class="flex-container table-tr">
                        <div class="flex-item"><fmt:message key="serviceName"/></div>
                        <div class="flex-item"><fmt:message key="masterName"/></div>
                        <div class="flex-item"><fmt:message key="date"/></div>
                        <div class="flex-item"><fmt:message key="status"/></div>
                    </div>
                    <c:forEach var="appointment" items="${appointments}">
                        <div class="flex-container wrap">
                            <div class="flex-item">${appointment.catalog.service.name}</div>
                            <div class="flex-item">${appointment.catalog.master.name}</div>
                            <div class="flex-item"><fmt:parseDate value="${ appointment.localDateTime }"
                                                                  pattern="yyyy-MM-dd'T'HH:mm"
                                                                  var="parsedDateTime" type="both"/>
                                <fmt:formatDate pattern="dd.MM.yyyy HH:mm"
                                                value="${ parsedDateTime }"/></div>
                            <div class="flex-item">${appointment.status}</div>
                            <div class="flex-item"><a type="submit" name="settings"
                                                      onclick="window.location='/admin/edit-appointment?appointmentId=${appointment.id}&role=${user.role}'"
                                                      id="edit">
                                <i class="fa fa-cog" aria-hidden="true"></i>
                            </a></div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>