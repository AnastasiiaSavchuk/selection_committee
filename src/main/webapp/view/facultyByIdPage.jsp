<%@ include file="/view/includes/init.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
<c:set var="isExistApplication" value="${sessionScope['applicantExist']}"/>
<c:set var="isExistStatement" value="${sessionScope['statementExisted']}"/>
<html>
<head>
    <title>Faculty by id</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="includes/header.jsp" %>

<div class="container">
    <div class="entityData">
        <h3><c:out value="${faculty.getFacultyList().get(0)}"/></h3>
        <hr>
        <h5><fmt:message key="faculty.TotalPlaces"/> : <c:out value="${faculty.getTotalQty()}"/></h5>
        <h5><fmt:message key="faculty.BudgetPlaces"/> : <c:out value="${faculty.getBudgetQty()}"/></h5>
        <h5><fmt:message key="faculty.AveragePassingGrade"/> : <c:out value="${faculty.getAveragePassingGrade()}"/></h5>
    </div>

    <div class="align-right">
        <div class="inRow">
            <a href="${pageContext.request.contextPath}/controller?command=getFaculties">
                <button class="button" type="button"><fmt:message key="return.Return"/></button>
            </a>
        </div>
        <c:if test="${not empty role}">
            <c:choose>
                <c:when test="${role == 'ADMIN'}">
                    <div class="inRow">
                        <button class="button" type="button" onclick="getApplications(this)">
                            <fmt:message key="faculty.DisplayApplication"/></button>
                    </div>
                </c:when>
                <c:when test="${role == 'USER'}">
                    <c:choose>
                        <c:when test="${isExistApplication}">
                            <div class="inRow">
                                <h5 style="color: #992600"><fmt:message key="application.AlreadyApplied"/></h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="inRow">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="applyToTheFacultyCreateChoice"/>
                                    <input type="hidden" name="facultyId" value="<c:out value="${faculty.getId()}"/>">
                                    <button class="button"><fmt:message key="application.ApplyButton"/></button>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
        </c:if>
    </div>

    <div id="application" style="display:none">
        <div class=" panel-table">
            <div class="panel-heading">
                <div class="panel-body">
                    <div class="align-right">
                        <c:choose>
                            <c:when test="${isExistStatement}">
                                <div class="inRow">
                                    <h5 style="color: #992600"><fmt:message key="application.AlreadyGenerate"/></h5>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="inRow">
                                    <a href="${pageContext.request.contextPath}/controller?command=generateStatement">
                                        <button class="button" type="button">
                                            <fmt:message key="statement.GenerateStatement"/></button>
                                    </a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                        <div class="inRow">
                            <a href="${pageContext.request.contextPath}/controller?command=rollbackStatement">
                                <button class="button" type="button">
                                    <fmt:message key="statement.RollBackStatement"/></button>
                            </a>
                        </div>
                        <div class="inRow">
                            <a href="${pageContext.request.contextPath}/controller?command=sendStatement">
                                <button class="button" type="button"><fmt:message
                                        key="statement.SendStatement"/></button>
                            </a>
                        </div>
                    </div>
                    <h2 style="text-align: center"><fmt:message key="application.Applications"/></h2>
                    <table id="pagination" class="table table-striped table-bordered table-list sortable">
                        <thead>
                        <tr>
                            <th class="hidden-xs">№</th>
                            <th><fmt:message key="applicant.FullName"/></th>
                            <th><fmt:message key="application.SumOfGrades"/></th>
                            <th><fmt:message key="application.AverageGrade"/></th>
                            <th><fmt:message key="application.Status"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${aList}" var="subjects" varStatus="loop">
                            <tr>
                                <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                                <td class="td-to-align"><c:out
                                        value="${subjects.getApplicant().getFirstName()}"/><c:out
                                        value="${subjects.getApplicant().getMiddleName()}"/> <c:out
                                        value="${subjects.getApplicant().getLastName()}"/>
                                </td>
                                <td class="td-to-align"><c:out value="${subjects.getSumOfGrades()}"/></td>
                                <td class="td-to-align"><c:out value="${subjects.getAverageGrade()}"/></td>
                                <td align="center">
                                    <c:if test="${not empty subjects.getApplicationStatus()}">
                                        <c:choose>
                                            <c:when test="${subjects.getApplicationStatus() == 'IN_PROCESSING'}">
                                                <button disabled class="btn btn-info">
                                                    <fmt:message key="application.IN_PROCESSING"/></button>
                                            </c:when>
                                            <c:when test="${subjects.getApplicationStatus() == 'REJECTED'}">
                                                <button disabled class="btn btn-danger">
                                                    <fmt:message key="application.REJECTED"/></button>
                                            </c:when>
                                            <c:when test="${subjects.getApplicationStatus() == 'BUDGET_APPROVED'}">
                                                <button disabled class="btn btn-success">
                                                    <fmt:message key="application.BUDGET_APPROVED"/></button>
                                            </c:when>
                                            <c:when test="${subjects.getApplicationStatus() == 'CONTRACT_APPROVED'}">
                                                <button disabled class="btn btn-warning">
                                                    <fmt:message key="application.CONTRACT_APPROVED"/></button>
                                            </c:when>
                                        </c:choose>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
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
<script>
    <%@include file="../js/applicant.js"%>
</script>
</body>
</html>
