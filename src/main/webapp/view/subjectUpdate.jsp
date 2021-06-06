<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<html><c:set var="subject" value="${sessionScope['subject']}"/>
<head>
    <title>Subject Update</title>
    <style>
        <%@ include file="../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="subjectUpdate"/>
            <input type="hidden" name="id" value="<c:out value="${subject.getId()}"/>">
            <h2><fmt:message key="update.Update"/> <fmt:message key="subject.Subject"/></h2>
            <hr>
            <div class="input-group mb-3">
                <input class="form-control" name="englishName" type="text"
                       placeholder="<fmt:message key="subject.NameInEnglish"/>"
                       value="<c:out value="${subject.getSubjectList().get(0)}"/>" required/></div>

            <div class="input-group mb-3">
                <input class="form-control" name="ukrainianName" type="text"
                       placeholder="<fmt:message key="subject.NameInUkrainian"/>"
                       value="<c:out value="${subject.getSubjectList().get(1)}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="passingGrade" type="number" min="0"
                       placeholder="<fmt:message key="subject.PassingGrade"/>"
                       value="<c:out value="${subject.getPassingGrade()}"/>" required/></div>
            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="save.Save"/></button>
                <a href="${pageContext.request.contextPath}/controller?command=getSubjects">
                    <button type="button" class="btn btn-customized"><fmt:message key="cancel.Cancel"/></button>
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
