<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Appointment form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="chooseFreeTimeSlot"/></span>
    </div>
    <form action="/${param.role.toLowerCase()}/create-appointment" method="post">

        <table class="tableInfo">
            <td>
                <input type="hidden" name="serviceName"
                       value="${param.serviceName}" required>
                <input type="hidden" name="catalogId" value="${param.catalogId}"/>
                <input type="hidden" name="clientId" value="${param.clientId}"/>
                <div class="tableInfo" id="user_info">
                    <table class="tableInfo">
                        <c:forEach var="slot" items="${shelude}">
                            <thead>
                            <th>
                                <fmt:parseDate value="${ slot.key }" pattern="yyyy-MM-dd'T'HH:mm"
                                               var="parsedDateTime"
                                               type="both"/>
                                <fmt:formatDate pattern="dd.MM.yyyy" value="${ parsedDateTime }"/>
                            </th>
                            </thead>
                            <tbody>
                            <tr>
                                <c:forEach var="slotValue" items="${slot.value}">
                                    <c:choose>
                                        <c:when test="${slotValue.value eq true}">
                                            <td> Busy slot:
                                                <button disabled><fmt:parseDate value="${ slotValue.key }"
                                                                                pattern="yyyy-MM-dd'T'HH:mm"
                                                                                var="parsedDateTime" type="both"/>
                                                    <fmt:formatDate pattern="HH:mm"
                                                                    value="${ parsedDateTime }"/></button>
                                            </td>
                                        </c:when>
                                        <c:when test="${slotValue.value eq false}">
                                            <td> Free slot:
                                                <button name="dateTime" value="${slotValue.key}" type="submit">
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
                            </tbody>
                        </c:forEach>
                    </table>
        </table>
    </form>
</div>
</body>
</html>