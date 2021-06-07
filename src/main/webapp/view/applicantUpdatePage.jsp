<%@ include file="/view/includes/init.jsp" %>
<c:set var="applicant" value="${sessionScope['applicant']}"/>
<html>
<head>
    <title>Signup Details</title>
    <style>
        <%@include file="../css/login.css" %>
    </style>
</head>
<body>

<%@ include file="includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="applicantUpdate"/>
            <input type="hidden" name="id" value="<c:out value="${applicant.getId()}"/>"/>
            <h2><fmt:message key="update.Update"/> <fmt:message key="applicant.Applicant"/></h2>
            <hr>
            <div class="input-group mb-3">
                <input class="form-control" name="email" type="email"
                       placeholder="<fmt:message key="email.Email"/>"
                       value="<c:out value="${applicant.getEmail()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="password" type="password"
                       placeholder="<fmt:message key="password.Password"/>"
                       value="<c:out value="${applicant.getPassword()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="firstName" type="text"
                       placeholder="<fmt:message key="applicant.FirstName"/>"
                       value="<c:out value="${applicant.getFirstName()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="middleName" type="text"
                       placeholder="<fmt:message key="applicant.MiddleName"/>"
                       value="<c:out value="${applicant.getMiddleName()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="lastName" type="text"
                       placeholder="<fmt:message key="applicant.LastName"/>"
                       value="<c:out value="${applicant.getLastName()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="city" type="text"
                       placeholder="<fmt:message key="applicant.City"/>"
                       value="<c:out value="${applicant.getCity()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="region" type="text"
                       placeholder="<fmt:message key="applicant.Region"/>"
                       value="<c:out value="${applicant.getRegion()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="schoolName" type="text"
                       placeholder="<fmt:message key="applicant.SchoolName"/>"
                       value="<c:out value="${applicant.getSchoolName()}"/>" required/></div>
            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="save.Save"/></button>
                <a href="${pageContext.request.contextPath}/controller?command=applicantPage">
                    <button type="button" class="btn btn-customized"><fmt:message key="cancel.Cancel"/></button>
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
