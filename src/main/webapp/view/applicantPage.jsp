<%@ include file="/view/includes/init.jsp" %>
<c:set var="applicant" value="${sessionScope['applicant']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
<c:set var="gList" value="${sessionScope['gradeList']}"/>
<c:set var="certImage" value="${sessionScope['certImage']}"/>
<html>
<head>
    <title>Applicant Page</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/includes/header.jsp" %>

<div class="container">
    <div class="entityData">
        <h2><c:out value="${applicant.getLastName()}"/> <c:out value="${applicant.getFirstName()}"/> <c:out
                value="${applicant.getMiddleName()}"/>
        </h2>
        <hr>
        <h5><fmt:message key="contacts.Email"/> : <c:out value="${applicant.getEmail()}"/></h5>
        <h5><fmt:message key="applicant.City"/> : <c:out value="${applicant.getCity()}"/></h5>
        <h5><fmt:message key="applicant.Region"/> : <c:out value="${applicant.getRegion()}"/></h5>
        <h5><fmt:message key="applicant.SchoolName"/> : <c:out value="${applicant.getSchoolName()}"/></h5>

        <c:if test="${role == 'ADMIN'}">
            <c:choose>
                <c:when test="${applicant.isBlocked() == true}">
                    <form id="updateForm" method="post" action="controller">
                        <input type="hidden" name="command" value="applicantUpdateByAdmin"/>
                        <input type="hidden" name="blocked" value="0">
                        <h5><fmt:message key="applicant.Status"/> :
                            <button disabled class="btn btn-danger"><fmt:message key="applicant.Blocked"/></button>
                            <button class="btn btn-success"><fmt:message key="applicant.UnlockedApplicant"/></button>
                        </h5>
                    </form>
                </c:when>
                <c:otherwise>
                    <form id="updateForm" method="post" action="controller">
                        <input type="hidden" name="command" value="applicantUpdateByAdmin"/>
                        <input type="hidden" name="blocked" value="1">
                        <h5><fmt:message key="applicant.Status"/> :
                            <button disabled class="btn btn-success"><fmt:message key="applicant.Active"/></button>
                            <button class="btn btn-danger"><fmt:message key="applicant.BlockedApplicant"/></button>
                        </h5>
                    </form>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${role == 'USER'}">
            <c:choose>
                <c:when test="${applicant.isBlocked() == true}">
                    <h5><fmt:message key="applicant.Status"/> :
                        <button disabled class="btn btn-danger"><fmt:message key="applicant.Blocked"/></button>
                    </h5>
                </c:when>
                <c:otherwise>
                    <h5><fmt:message key="applicant.Status"/> :
                        <button disabled class="btn btn-success"><fmt:message key="applicant.Active"/></button>
                    </h5>
                </c:otherwise>
            </c:choose>
        </c:if>
        <hr>
        <h2><fmt:message key="applicant.ZNOGrade"/></h2>
        <c:forEach items="${gList}" var="grade">
            <h5>${grade.getSubject().getSubjectList().get(0)} : ${grade.getGrade()}</h5>
        </c:forEach>
    </div>

    <div class="align-right">
        <c:if test="${role == 'USER'}">
            <c:choose>
                <c:when test="${applicant.isBlocked() == true}">
                    <div class="inRow">
                        <h5 style="color: brown"><fmt:message key="applicant.ToUnlocked"/></h5>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="inRow">
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="applicantUpdate"/>
                            <input type="hidden" name="applicantIdToUpdate"
                                   value="<c:out value="${applicant.getId()}"/>">
                            <button class="button"><fmt:message key="update.Update"/></button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
        <div class="inRow">
            <img src="data:image/jpg;base64,${certImage}" width="600" height="400" alt=""/>
        </div>
    </div>

    <div class="panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <h2 style="text-align: center"><fmt:message key="application.Applications"/></h2>
                <table id="pagination" class="table table-striped table-bordered table-list sortable">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="faculty.Faculty"/></th>
                        <th><fmt:message key="application.SumOfGrades"/></th>
                        <th><fmt:message key="application.AverageGrade"/></th>
                        <th><fmt:message key="application.Status"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${aList}" var="application" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${application.getFaculty().getFacultyList().get(0)}</td>
                            <td>${application.getSumOfGrades()}</td>
                            <td>${application.getAverageGrade()}</td>
                            <c:if test="${not empty application.getApplicationStatus()}">
                                <c:choose>
                                    <c:when test="${application.getApplicationStatus() == 'IN_PROCESSING'}">
                                        <td>
                                            <button disabled class="btn btn-info">
                                                <fmt:message key="application.IN_PROCESSING"/></button>
                                        </td>
                                    </c:when>
                                    <c:when test="${application.getApplicationStatus() == 'BLOCKED'}">
                                        <td>
                                            <button disabled class="btn btn-danger">
                                                <fmt:message key="application.Blocked"/></button>
                                        </td>
                                    </c:when>
                                    <c:when test="${application.getApplicationStatus() == 'REJECTED'}">
                                        <td>
                                            <button disabled class="btn btn-danger">
                                                <fmt:message key="application.REJECTED"/></button>
                                        </td>
                                    </c:when>
                                    <c:when test="${application.getApplicationStatus() == 'BUDGET_APPROVED'}">
                                        <td>
                                            <button disabled class="btn btn-success">
                                                <fmt:message key="application.BUDGET_APPROVED"/></button>
                                        </td>
                                    </c:when>
                                    <c:when test="${application.getApplicationStatus() == 'CONTRACT_APPROVED'}">
                                        <td>
                                            <button disabled class="btn btn-warning">
                                                <fmt:message key="application.CONTRACT_APPROVED"/></button>
                                        </td>
                                    </c:when>
                                </c:choose>
                            </c:if>
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
