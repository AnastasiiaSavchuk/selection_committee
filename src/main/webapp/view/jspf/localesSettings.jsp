<%@include file="/view/jspf/directive.jspf" %>
<%--@elvariable id="elanguage" type="java"--%>
<c:set var="language"
       value="${not empty elanguage ? elanguage : not empty elanguage ? elanguage : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="locales"/>