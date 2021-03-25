<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Edit Appointment form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<a href="" onclick="history.back(); return false;" id="back">
    <i class="fas fa-arrow-circle-left">
        <fmt:message key="editAppointment"/>
    </i>
</a>
    <div id="user_info" class="tableInfo"  style="margin: 0 auto; width: 100%;"  >
        <form action="/${param.role.toLowerCase()}/edit-appointment" method="post">
            <table class="table">
                <tr>
                    <th><fmt:message key="id"/></th>
                    <th><fmt:message key="customerName"/></th>
                    <th><fmt:message key="masterName"/></th>
                    <th><fmt:message key="date"/></th>
                    <th><fmt:message key="status"/> </th>
                </tr>
                <tr>
                    <td><input type="number" name="appointmentId" value="${appointment.id}" required/></td>
                    <td><input type="text" name="clientName" value="${appointment.client.name}" required/></td>
                    <td><input type="text" name="masterName" value="${appointment.catalog.master.name}" required/></td>
                    <td><input type="datetime-local" name="dateTime" value="${appointment.localDateTime}" required/></td>
                    <td>
                        <p><select name="status" size="1">
                            <option value="OPEN" selected>Open</option>
                            <option value="CANCELED">Canceled</option>
                            <option value="PAID">Paid</option>
                            <option value="COMPLETE">Complete</option>
                        </select></p>
                        <p></p>
                    </td>
                <tr style="background-color: transparent">
                <td colspan="7">
                    <input type="hidden" name="role" value="${user.role}">
                    <input type="hidden" name="catalogId" value="${appointment.catalog.id}"/>
                    <input type="hidden" name="clientId" value="${appointment.client.id}"/>
                    <input id="accept"  type="submit" name="save" value="<fmt:message key="save"/>">
            </tr>
            </table>
        </form>
    </div>
</body>
</html>