<%@ include file="/view/includes/init.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>
<html>
<head>
    <title>Faculty update</title>
    <style>
        <%@ include file="../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="facultyUpdate"/>
            <input type="hidden" name="id" value="<c:out value="${faculty.getId()}"/>"/>
            <h2><fmt:message key="update.Update"/> <fmt:message key="faculty.Faculty"/></h2>
            <hr>
            <div class="input-group mb-3">
                <input class="form-control" name="englishName" type="text"
                       placeholder="<fmt:message key="faculty.NameInEnglish"/>"
                       value="<c:out value="${faculty.getFacultyList().get(0)}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="ukrainianName" type="text"
                       placeholder="<fmt:message key="faculty.NameInUkrainian"/>"
                       value="<c:out value="${faculty.getFacultyList().get(1)}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="budgetQty" type="number" min="0"
                       placeholder="<fmt:message key="faculty.BudgetPlaces"/>"
                       value="<c:out value="${faculty.getBudgetQty()}"/>" required/></div>
            <div class="input-group mb-3">
                <input class="form-control" name="totalQty" type="number" min="0"
                       placeholder="<fmt:message key="faculty.TotalPlaces"/>"
                       value="<c:out value="${faculty.getTotalQty()}"/>" required/></div>
            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="save.Save"/></button>
                <a href="${pageContext.request.contextPath}/controller?command=getFaculties">
                    <button type="button" class="btn btn-customized"><fmt:message key="cancel.Cancel"/></button>
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
