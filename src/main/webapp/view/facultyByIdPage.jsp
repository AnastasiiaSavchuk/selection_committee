<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<html>
<head>
    <title>Faculty by id</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <div>
        <h2><c:out value="${faculty.getFacultyList().get(0)}"/></h2>
        <div class="float-right"><a href="${pageContext.request.contextPath}/controller?command=getFaculties">
            <button type="button" class="btn btn-customized"><fmt:message key="return.Return"/></button>
        </a>
        </div>
        <hr>
        <h4><fmt:message key="faculty.TotalPlaces"/> : <c:out value="${faculty.getTotalQty()}"/></h4>
        <h4><fmt:message key="faculty.BudgetPlaces"/> : <c:out value="${faculty.getBudgetQty()}"/></h4>
    </div>

    <div class="panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table class="table table-striped table-bordered table-list sortable">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="subject.Subject"/></th>
                        <th><fmt:message key="subject.PassingGrade"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sList}" var="subject" varStatus="loop">
                        <tr>
                            <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                            <td class="td-to-align"><c:out value="${subject.getSubjectList().get(0)}"/></td>
                            <td class="td-to-align"><c:out value="${subject.getPassingGrade()}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
