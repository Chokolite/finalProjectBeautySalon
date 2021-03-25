<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Create Review form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="leaveReview"/></span>
    </div>
    <form action="/client/create-review" method="post">

        <fieldset class="clearfix">
            <input type="hidden" name="appointmentId" value="${param.appointmentId}"/>
            <input type="hidden" name="clientId" value="${param.clientId}"/>
            <p><span class="fontawesome-file-alt"></span><input type="text" name="review" placeholder="<fmt:message key="review"/>" required>
            </p>
            <p><span class="fontawesome-star"></span><input type="number" name="rating" min="1" max="10" placeholder="<fmt:message key="rating"/>" required>
            </p>
            <p></p>
            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>