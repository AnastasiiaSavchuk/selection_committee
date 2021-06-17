<%@ include file="/view/includes/init.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>
<c:set var="gList" value="${sessionScope['gradeList']}"/>
<html>
<head>
    <title>Apply to the faculty</title>
    <style>
        <%@ include file="../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="applyToTheFaculty"/>
            <h2><fmt:message key="application.Apply"/> : </h2>
            <h4><c:out value="${faculty.getFacultyList().get(0)}"/></h4>
            <hr>
            <div class="input-group mb-3">
                <c:forEach items="${gList}" var="grade">
                    <label class="applyLabel"><c:out value="${grade.getSubject().getSubjectList().get(0)}"/></label>
                    <label class="applyLabel"><c:out value="${grade.getGrade()}"/></label>
                </c:forEach>
            </div>

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
