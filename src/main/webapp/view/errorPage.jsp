<%@ include file="/view/includes/init.jsp" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>

<html>
<head>
    <c:set var="title" value="Error" scope="page"/>
    <style>
        <%@ include file="/css/table.css" %>
    </style>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<div class="container">
    <div class="error_h3">
        <h3>Something went wrong!</h3>
    </div>
    <div>
        <%-- this way we get the error information (error 404)--%>
        <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
        <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

        <%-- this way we get the exception --%>
        <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

        <c:if test="${not empty code}"><h4>Error code: ${code}</h4></c:if>

        <c:if test="${not empty message}"><h5>Message: ${message}</h5></c:if>

        <%-- if get this page using forward --%>
        <c:if test="${not empty errorMessage and empty exception and empty code}"><h3>Error
            message: ${errorMessage}</h3></c:if>

        <%-- this way we print exception stack trace --%>
        <c:if test="${not empty exception}">
            <hr/>
            <h4>Stack trace:</h4>
            <c:forEach var="stackTraceElement" items="${exception.stackTrace}">${stackTraceElement}</c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>