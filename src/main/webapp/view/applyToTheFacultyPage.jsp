<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>

<html>
<head>
    <title>Apply to the faculty</title>
    <style>
        <%@ include file="../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="applyToTheFaculty"/>
            <h2><fmt:message key="application.Apply"/> : </h2>
            <h4><c:out value="${faculty.getFacultyList().get(0)}"/></h4>
            <hr>
            <c:forEach items="${faculty.getSubjectList()}" var="subject">
                <div class="input-group mb-3">
                    <label for="subject" class="applyLabel"><c:out value="${subject.getSubjectList().get(0)}"/></label>
                    <input type="hidden" name="subjectId" value="${subject.getId()}"/>
                    <input type="text" class="applyLabel" name="grade" id="subject" min="0" max="200"
                           placeholder="<fmt:message key="application.YourZNOGrade"/>" required/>
                </div>
            </c:forEach>
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
