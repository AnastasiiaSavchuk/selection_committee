<%@ include file="/view/includes/init.jsp" %>
<c:set var="applicant" value="${sessionScope['applicant']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
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
        <h2><c:out value="${applicant.getLastName()}"/> <c:out value="${applicant.getFirstName()}"/><c:out
                value="${applicant.getMiddleName()}"/>
        </h2>
        <hr>
        <h5><fmt:message key="contacts.Email"/> : <c:out value="${applicant.getEmail()}"/></h5>
        <h5><fmt:message key="applicant.City"/> : <c:out value="${applicant.getCity()}"/></h5>
        <h5><fmt:message key="applicant.Region"/> : <c:out value="${applicant.getRegion()}"/></h5>
        <h5><fmt:message key="applicant.SchoolName"/> : <c:out value="${applicant.getSchoolName()}"/></h5>
        <h5><fmt:message key="applicant.Status"/> :
            <c:if test="${not empty applicant.isBlocked()}">
                <c:choose>
                    <c:when test="${applicant.isBlocked() == true}">
                        <button disabled class="btn btn-danger"><fmt:message key="applicant.Blocked"/></button>
                    </c:when>
                    <c:when test="${applicant.isBlocked() == false}">
                        <button disabled class="btn btn-success"><fmt:message key="applicant.Active"/></button>
                    </c:when>
                </c:choose>
            </c:if></h5>
        <div id="certificate" style="display:none">
            <h5><fmt:message key="applicant.Certificate"/></h5>
            <img src="data:image/jpg;base64,${certImage}" width="500"/>
        </div>
    </div>

    <div class="align-right">
        <c:if test="${role == 'ADMIN'}">
            <div class="inRow">
                <a href="${pageContext.request.contextPath}/controller?command=adminPage">
                    <button class="button" type="button"><fmt:message key="return.Return"/></button>
                </a>
            </div>
        </c:if>
        <div class="inRow">
            <button class="button" type="button" onclick="getCertificate(this)">
                <fmt:message key="applicant.DisplayCertificate"/></button>
        </div>
        <div class="inRow">
            <button class="button" type="button" onclick="getGrades(this)"><fmt:message
                    key="grade.DisplayGrade"/></button>
        </div>
        <c:if test="${not empty role}">
            <c:choose>
                <c:when test="${role == 'ADMIN'}">
                    <div class="inRow">
                        <h5><fmt:message key="applicant.ChangStatus"/></h5>
                        <div>
                            <label class="form-check-label" for="unBlocked">
                                <fmt:message key="applicant.UnlockedApplicant"/>
                                <input id="unBlocked" class="getStatus" type="radio" name="unBlocked"
                                       value="0"/></label>
                            <label class="form-check-label" for="isBlocked">
                                <fmt:message key="applicant.BlockedApplicant"/>
                                <input id="isBlocked" class="getStatus" type="radio" name="isBlocked"
                                       value="1"/></label>
                        </div>
                        <form id="updateForm" method="post" action="controller">
                            <input type="hidden" name="command" value="applicantUpdateByAdmin"/>
                            <input type="hidden" name="blocked" id="applicantUpdate" value="">
                            <a onclick="applicantUpdate();">
                                <button class="button"><fmt:message key="save.Save"/></button>
                            </a>
                        </form>
                    </div>
                </c:when>
                <c:when test="${role == 'USER'}">
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
                </c:when>
            </c:choose>
        </c:if>
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
                            <td class="td-to-align">${loop.index + 1}</td>
                            <td class="td-to-align">${application.getFaculty().getFacultyList().get(0)}</td>
                            <td class="td-to-align">${application.getSumOfGrades()}</td>
                            <td class="td-to-align">${application.getAverageGrade()}</td>
                            <td align="center">
                                <c:if test="${not empty application.getApplicationStatus()}">
                                    <c:choose>
                                        <c:when test="${application.getApplicationStatus() == 'IN_PROCESSING'}">
                                            <button disabled class="btn btn-info">
                                                <fmt:message key="application.IN_PROCESSING"/></button>
                                        </c:when>
                                        <c:when test="${application.getApplicationStatus() == 'BLOCKED'}">
                                            <button disabled class="btn btn-danger">
                                                <fmt:message key="application.Blocked"/></button>
                                        </c:when>
                                        <c:when test="${application.getApplicationStatus() == 'REJECTED'}">
                                            <button disabled class="btn btn-danger">
                                                <fmt:message key="application.REJECTED"/></button>
                                        </c:when>
                                        <c:when test="${application.getApplicationStatus() == 'BUDGET_APPROVED'}">
                                            <button disabled class="btn btn-success">
                                                <fmt:message key="application.BUDGET_APPROVED"/></button>
                                        </c:when>
                                        <c:when test="${application.getApplicationStatus() == 'CONTRACT_APPROVED'}">
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

    <div id="grade" style="display:none">
        <div class="panel-table">
            <div class="panel-heading">
                <div class="panel-body">
                    <h2 style="text-align: center"><fmt:message key="subject.Subjects"/></h2>
                    <table class="table table-striped table-bordered table-list sortable">
                        <thead>
                        <tr>
                            <th class="hidden-xs">№</th>
                            <th><fmt:message key="faculty.Faculty"/></th>
                            <th colspan="4"><fmt:message key="subject.Subject"/> : <fmt:message
                                    key="subject.Grade"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${aList}" var="application" varStatus="loop">
                            <tr>
                                <td class="td-to-align">${loop.index + 1}</td>
                                <td class="td-to-align">${application.getFaculty().getFacultyList().get(0)}</td>
                                <c:forEach items="${application.getGradeList()}" var="grade">
                                    <td class="td-to-align">${grade.getSubject().getSubjectList().get(0)}
                                        : ${grade.getGrade()}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    <%@include file="../js/applicant.js" %>
</script>
</body>
</html>
