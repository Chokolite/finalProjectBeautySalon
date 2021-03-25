<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="title" value="Add in Catalog form"/>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="login">
    <a href="" onclick="history.back(); return false;" id="back">
        <i class="fas fa-arrow-circle-left"></i>
    </a>
    <br>
    <div class="span_sign">
        <span><fmt:message key="createCatalog"/></span>
    </div>
    <form action="/admin/create-catalog" method="post">

        <fieldset class="clearfix">
                 <p><span class="fontawesome-file-alt"></span><input type="text" name="serviceId" value="${param.id}" placeholder="<fmt:message key="serviceId"/>" required>
            </p>
            <p><span class="fontawesome-file-alt"></span><input type="text" name="masterId" value="${param.userId}" placeholder="<fmt:message key="userId"/>" required>
            </p>
            <p></p>
            <p><input type="submit" name="submit" value="<fmt:message key="save"/>"></p>
        </fieldset>
    </form>
</div>
</body>
</html>