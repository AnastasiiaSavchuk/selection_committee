<%@ include file="/view/includes/init.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
<c:set var="applicantExist" value="${sessionScope['applicantExist']}"/>
<c:set var="isExist" value="${sessionScope['isExist']}"/>
<c:set var="isSent" value="${sessionScope['isSent']}"/>
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
    <div class="entityData_1">
        <h3><c:out value="${faculty.getFacultyList().get(0)}"/></h3>
        <hr>
        <h5><fmt:message key="faculty.TotalPlaces"/> : <c:out value="${faculty.getTotalQty()}"/></h5>
        <h5><fmt:message key="faculty.BudgetPlaces"/> : <c:out value="${faculty.getBudgetQty()}"/></h5>
        <h5><fmt:message key="faculty.AveragePassingGrade"/> : <c:out value="${faculty.getAveragePassingGrade()}"/></h5>
        <hr>
        <h2><fmt:message key="subject.Subjects"/></h2>
        <c:forEach items="${sList}" var="subject">
            <h5>${subject.getSubjectList().get(0)} : ${subject.getPassingGrade()}</h5>
        </c:forEach>

    </div>

    <div class="align-right">
        <c:if test="${not empty role}">
            <c:choose>
                <c:when test="${role == 'ADMIN'}">
                    <c:choose>
                        <c:when test="${isExist}">
                            <div class="inRow">
                                <h5 style="color: brown"><fmt:message key="application.AlreadyGenerate"/></h5>
                            </div>
                            <c:choose>
                                <c:when test="${isSent}">
                                    <div class="inRow">
                                        <h5 style="color: #992600"><fmt:message key="application.AlreadySent"/></h5>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="inRow">
                                        <a href="${pageContext.request.contextPath}/controller?command=rollbackStatement">
                                            <button class="button" type="button">
                                                <fmt:message key="statement.RollBackStatement"/></button>
                                        </a>
                                    </div>
                                    <div class="inRow">
                                        <a href="${pageContext.request.contextPath}/controller?command=sendStatement">
                                            <button class="button" type="button">
                                                <fmt:message key="statement.SendStatement"/></button>
                                        </a>
                                    </div>
                                </c:otherwise>
                            </c:choose>
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
                </c:when>

                <c:when test="${role == 'USER'}">
                    <c:choose>
                        <c:when test="${applicant.isBlocked() == true}">
                            <div class="inRow">
                                <h5 style="color: brown"><fmt:message key="applicant.ToUnlocked"/></h5>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${applicantExist}">
                                    <div class="inRow">
                                        <h5 style="color: #992600"><fmt:message key="application.AlreadyApplied"/></h5>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="inRow">
                                        <form method="post" action="controller">
                                            <input type="hidden" name="command" value="applyToTheFacultyCreateChoice"/>
                                            <input type="hidden" name="facultyId"
                                                   value="<c:out value="${faculty.getId()}"/>">
                                            <button class="button"><fmt:message key="application.ApplyButton"/></button>
                                        </form>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:when>
            </c:choose>
        </c:if>
    </div>

    <c:if test="${role == 'ADMIN'}">
        <div class=" panel-table">
            <div class="panel-heading">
                <div class="panel-body">
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
                        <c:forEach items="${aList}" var="application" varStatus="loop">
                            <tr>
                                <td><c:out value="${loop.index + 1}"/></td>
                                <td><c:out value="${application.getApplicant().getFirstName()}"/> <c:out
                                        value="${application.getApplicant().getMiddleName()}"/> <c:out
                                        value="${application.getApplicant().getLastName()}"/></td>
                                <td><c:out value="${application.getSumOfGrades()}"/></td>
                                <td><c:out value="${application.getAverageGrade()}"/></td>
                                <c:if test="${application.getApplicationStatus() == 'IN_PROCESSING'}">
                                    <td>
                                        <button disabled class="btn btn-info">
                                            <fmt:message key="application.IN_PROCESSING"/></button>
                                    </td>
                                </c:if>
                                <c:if test="${application.getApplicationStatus() == 'BLOCKED'}">
                                    <td>
                                        <button disabled class="btn btn-danger">
                                            <fmt:message key="application.Blocked"/></button>
                                    </td>
                                </c:if>
                                <c:if test="${application.getApplicationStatus() == 'REJECTED'}">
                                    <td>
                                        <button disabled class="btn btn-danger">
                                            <fmt:message key="application.REJECTED"/></button>
                                    </td>
                                </c:if>
                                <c:if test="${application.getApplicationStatus() == 'BUDGET_APPROVED'}">
                                    <td>
                                        <button disabled class="btn btn-success">
                                            <fmt:message key="application.BUDGET_APPROVED"/></button>
                                    </td>
                                </c:if>
                                <c:if test="${application.getApplicationStatus() == 'CONTRACT_APPROVED'}">
                                    <td>
                                        <button disabled class="btn btn-warning">
                                            <fmt:message key="application.CONTRACT_APPROVED"/></button>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
