<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Appointment form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="table-flex">
    <div class="table-flex wrap">
        <div class="flex-item">
            <a href="" onclick="history.back(); return false;" id="back">
                <i class="fas fa-arrow-circle-left"></i>
            </a>
            <p>
            <div class="flex-item"><span><fmt:message key="chooseFreeTimeSlot"/></span></div>
            </p>
        </div>
        <form id="createAppointment" action="/${param.role.toLowerCase()}/create-appointment" method="post">

            <input type="hidden" name="serviceName"
                   value="${param.serviceName}" required>
            <input type="hidden" name="catalogId" value="${param.catalogId}"/>
            <input type="hidden" name="clientId" value="${param.clientId}"/>
            <div class="flex-container wrap" action="/client/create-appointment" method="post">
                <c:forEach var="slot" items="${shelude}">
                    <div class="table-flex">
                        <div class="flex-item table-tr">
                            <fmt:parseDate value="${slot.key}" pattern="yyyy-MM-dd'T'HH:mm"
                                           var="parsedDateTime"
                                           type="both"/>
                            <fmt:formatDate pattern="dd.MM.yyyy" value="${parsedDateTime}"/>
                        </div>
                        <div class="flex-container wrap">
                            <c:forEach var="slotValue" items="${slot.value}">
                                <c:choose>
                                    <c:when test="${slotValue.value eq true}">
                                        <c:forEach var="appointment" items="${appointments}">
                                            <c:if test="${slotValue.key eq appointment.localDateTime}">
                                                <div class="flex-item busy">
                                                    <span class="tooltiptext">busy</span>
                                                    <fmt:parseDate
                                                            value="${ slotValue.key }"
                                                            pattern="yyyy-MM-dd'T'HH:mm"
                                                            var="parsedDateTime"
                                                            type="both"/>
                                                    <fmt:formatDate pattern="HH:mm"
                                                                    value="${ parsedDateTime }"/>
                                                </div>

                                            </c:if>
                                        </c:forEach>
                                    </c:when>
                                    <c:when test="${slotValue.value eq false}">

                                        <div class="flex-item free">

                                                <span class="tooltiptext">
                                                    <button class="btn-link" name="dateTime" value="${slotValue.key}" type="submit">
                                                        free
                                                    </button>
                                                </span>
                                                <fmt:parseDate value="${ slotValue.key }"
                                                               pattern="yyyy-MM-dd'T'HH:mm"
                                                               var="parsedDateTime" type="both"/>
                                                <fmt:formatDate pattern="HH:mm"
                                                                value="${ parsedDateTime }"/>
                                        </div>

                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </form>
    </div>
</div>
</body>
</html>