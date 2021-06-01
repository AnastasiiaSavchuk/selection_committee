<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<html>
<head>
    <title>Faculty Create</title>
    <style>
        <%@ include file="../../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" action="controller" method="post">
            <input type="hidden" name="command" value="facultyCreate"/>
            <h2><fmt:message key="createNew.CreateNew"/> <fmt:message key="faculty.Faculty"/></h2>
            <hr>
            <div class="input-group mb-3">
                <input id="englishName" class="form-control" name="englishName" type="text"
                       placeholder="<fmt:message key="faculty.NameInEnglish"/>" required/></div>

            <div class="input-group mb-3">
                <input id="ukrainianName" class="form-control" name="ukrainianName" type="text"
                       placeholder="<fmt:message key="faculty.NameInUkrainian"/>" required/></div>

            <div class="input-group mb-3">
                <input id="budgetQty" class="form-control" name="budgetQty" type="number" min="0"
                       placeholder="<fmt:message key="faculty.BudgetPlaces"/>" required/></div>

            <div class="input-group mb-3">
                <input id="totalQty" class="form-control" name="totalQty" type="number" min="0"
                       placeholder="<fmt:message key="faculty.TotalPlaces"/>" required/></div>

            <h4><fmt:message key="subject.Subjects"/></h4>
            <hr>

            <div class="input-group mb-3">
                <c:forEach items="${sList}" var="subject">
                    <label for="subject">${subject.getSubjectList().get(0)}
                        <input type="checkbox" class="checkbox" name="subject" id="subject"
                               value="${subject.getId()}"/></label>
                </c:forEach>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="create.Create"/></button>
                <a href="${pageContext.request.contextPath}/view/jsp/facultiesPage.jsp">
                    <button type="button" class="btn btn-customized"><fmt:message key="cancel.Cancel"/></button>
                </a>
            </div>
        </form>
    </div>
</div>

<script>
    <%@include file="../../js/faculty.js"%>
</script>
</body>
</html>
