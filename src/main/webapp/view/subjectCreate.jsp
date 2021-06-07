<%@ include file="/view/includes/init.jsp" %>
<html>
<head>
    <title>Subject Create</title>
    <style>
        <%@ include file="../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="subjectCreate"/>
            <h2><fmt:message key="subject.CreateNewSubject"/></h2>
            <hr>
            <div class="input-group mb-3">
                <input class="form-control" name="englishName" type="text"
                       placeholder="<fmt:message key="subject.NameInEnglish"/>" required/></div>

            <div class="input-group mb-3">
                <input class="form-control" name="ukrainianName" type="text"
                       placeholder="<fmt:message key="subject.NameInUkrainian"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="passingGrade" type="number" min="0" max="200"
                       placeholder="<fmt:message key="subject.PassingGrade"/>" required/></div>
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
