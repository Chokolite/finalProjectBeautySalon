<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Edit Appointment form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<a href="" onclick="history.back(); return false;" id="back">
    <i class="fas fa-arrow-circle-left">
        <fmt:message key="editAppointment"/>
    </i>
</a>
<div id="login">
    <form action="/master/master-edit-appointment" method="post">
        <fieldset class="clearfix">
            <p><span class="fontawesome-user"></span><input disabled type="text" value="${appointment.client.name}"
                                                            placeholder="<fmt:message key="customerName"/>: "></label>
            </p>
            <p><span class="fontawesome-calendar"></span><input disabled type="datetime-local"
                                                                placeholder="<fmt:message key="date"/>"
                                                                value="${appointment.localDateTime}"/></p>
            <p>
                <c:if test="${param.appointmentStatus eq 'COMPLETE'}">
                <span class="fontawesome-file-alt"></span><select disabled name="status" size="1">
                    <option value="COMPLETE" selected>Complete</option>
                </c:if>
                <c:if test="${param.appointmentStatus != 'COMPLETE'}">
                <span class="fontawesome-file-alt"></span><select name="status" size="1">
                </c:if>
                <option value="OPEN">Open</option>
                <option value="CANCELED">Canceled</option>
            </select></p>
            <p></p>

            <input type="hidden" name="appointmentId" value="${appointment.id}"/>
            <input type="hidden" name="dateTime" value="${appointment.localDateTime}" required/>
            <input type="hidden" name="clientName" value="${appointment.client.name}" required/>
            <input type="hidden" name="catalogId" value="${appointment.catalog.id}"/>
            <input type="hidden" name="clientId" value="${appointment.client.id}"/>
            <input type="hidden" name="dateTime" value="${appointment.localDateTime}" required/>
            <p></p>
            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>