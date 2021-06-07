<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%--==================== JSTL core tag library. ====================--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--==================== JSTL i18n tag library. ====================--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="elanguage" type="java"--%>
<c:set var="language"
       value="${not empty elanguage ? elanguage : not empty elanguage ? elanguage : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locales"/>