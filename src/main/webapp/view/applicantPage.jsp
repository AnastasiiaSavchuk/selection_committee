<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="applicant" value="${sessionScope['applicant']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
<html>
<head>
    <title>Applicant Page</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <div class="entityData">
        <h3><c:out value="${applicant.getFirstName()}"/> <c:out value="${applicant.getMiddleName()}"/>
            <c:out value="${applicant.getLastName()}"/></h3>
        <hr>
        <h6><fmt:message key="contacts.Email"/> : <c:out value="${applicant.getEmail()}"/></h6>
        <h6><fmt:message key="applicant.City"/> : <c:out value="${applicant.getCity()}"/></h6>
        <h6><fmt:message key="applicant.Region"/> : <c:out value="${applicant.getRegion()}"/></h6>
        <h6><fmt:message key="applicant.SchoolName"/> : <c:out value="${applicant.getSchoolName()}"/></h6>
        <h6><fmt:message key="applicant.Status"/> :
            <c:if test="${not empty applicant.isBlocked()}">
                <c:choose>
                    <c:when test="${applicant.isBlocked() == true}">
                        <button disabled class="btn btn-danger"><fmt:message key="applicant.Blocked"/></button>
                    </c:when>
                    <c:when test="${applicant.isBlocked() == false}">
                        <button disabled class="btn btn-success"><fmt:message key="applicant.Active"/></button>
                    </c:when>
                </c:choose>
            </c:if></h6>
        <div id="certificate" style="display:none">
            <h6><fmt:message key="applicant.Certificate"/></h6>
            <img src="data:image/jpg;base64,${applicant.getCertificate()}" width="500"/>
        </div>
    </div>

    <div class="align-right">
        <c:if test="${role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/controller?command=adminPage">
                <button class="button" type="button"><fmt:message key="return.Return"/></button>
            </a>
        </c:if>

        <button class="button" type="button" onclick="getCertificate(this)">
            <fmt:message key="applicant.DisplayCertificate"/></button>

        <button class="button" type="button" onclick="getGrades(this)"><fmt:message key="grade.DisplayGrade"/></button>

        <c:if test="${role == 'ADMIN'}">
            <h5><fmt:message key="applicant.ChangStatus"/></h5>
            <div>
                <label class="form-check-label" for="unBlocked">
                    <fmt:message key="applicant.UnBlockedApplicant"/>
                    <input id="unBlocked" class="getStatus" type="radio" name="unBlocked" value="0"/></label>
                <label class="form-check-label" for="isBlocked">
                    <fmt:message key="applicant.BlockedApplicant"/>
                    <input id="isBlocked" class="getStatus" type="radio" name="isBlocked" value="1"/></label>
            </div>
            <form id="updateForm" method="post" action="controller">
                <input type="hidden" name="command" value="applicantUpdate"/>
                <input type="hidden" name="blocked" id="applicantUpdate" value="">
                <a onclick="applicantUpdate();">
                    <button class="button"><fmt:message key="save.Save"/></button>
                </a>
            </form>
        </c:if>
    </div>

    <div class="panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <h2 style="text-align: center"><fmt:message key="application.Applications"/></h2>
                <table class="table table-striped table-bordered table-list sortable">
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
                    <c:forEach items="${aList}" var="subjects" varStatus="loop">
                        <tr>
                            <td class="td-to-align">${loop.index + 1}</td>
                            <td class="td-to-align">${subjects.getFaculty().getFacultyList().get(0)}</td>
                            <td class="td-to-align">${subjects.getSumOfGrades()}</td>
                            <td class="td-to-align">${subjects.getAverageGrade()}</td>
                            <td align="center">
                                <c:if test="${not empty subjects.getApplicationStatus()}">
                                    <c:choose>
                                        <c:when test="${subjects.getApplicationStatus() == 'IN_PROCESSING'}">
                                            <button disabled class="btn btn-warning">
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
                                            <button disabled class="btn btn-success">
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
                        <c:forEach items="${aList}" var="subjects" varStatus="loop">
                            <tr>
                                <td class="td-to-align">${loop.index + 1}</td>
                                <td class="td-to-align">${subjects.getFaculty().getFacultyList().get(0)}</td>
                                <c:forEach items="${subjects.getGradeList()}" var="grade">
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
