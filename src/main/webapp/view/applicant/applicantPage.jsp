<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="applicant" value="${sessionScope['applicant']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
<html>
<head>
    <title>Applicant Page</title>
    <style>
        <%@ include file="../../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <div class="applicantData">
        <h2><c:out value="${applicant.getFirstName()}"/> <c:out value="${applicant.getMiddleName()}"/>
            <c:out value="${applicant.getLastName()}"/></h2>
        <hr>
        <h5><fmt:message key="contacts.Email"/> : <c:out value="${applicant.getEmail()}"/></h5>
        <h5><fmt:message key="applicant.City"/> : <c:out value="${applicant.getCity()}"/></h5>
        <h5><fmt:message key="applicant.Region"/> : <c:out value="${applicant.getRegion()}"/></h5>
        <h5><fmt:message key="applicant.SchoolName"/> : <c:out value="${applicant.getSchoolName()}"/></h5>
        <h5><fmt:message key="applicant.Status"/> :
            <c:if test="${not empty applicant.isBlocked()}">
                <c:choose>
                    <c:when test="${applicant.isBlocked() == true}">
                        <button type="button" class="btn btn-danger"><fmt:message key="applicant.Blocked"/></button>
                    </c:when>
                    <c:when test="${applicant.isBlocked() == false}">
                        <button type="button" class="btn btn-success"><fmt:message key="applicant.Active"/></button>
                    </c:when>
                </c:choose>
            </c:if></h5>
    </div>

    <div>
        <div>
            <button type="button" class="btn btn-customized inRow" id="getCertificate"
                    onclick="getCertificate(this)">
                <fmt:message key="applicant.DisplayCertificate"/></button>
            <c:if test="${role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/controller?command=adminPage">
                <button type="button" class="btn btn-customized inRow"><fmt:message key="return.Return"/></button>
            </a></div>

        <div class="inRow">
            <h5><fmt:message key="applicant.IsBlocked"/></h5>
            <div class="form-check">
                <label class="form-check-label" for="unBlocked">
                    <fmt:message key="applicant.UnBlockedApplicant"/>
                    <input id="unBlocked" class="getStatus" type="radio" name="unBlocked" value="0"/></label>

                <label class="form-check-label" for="isBlocked">
                    <fmt:message key="applicant.BlockedApplicant"/>
                    <input id="isBlocked" class="getStatus" type="radio" name="isBlocked" value="1"/></label>
            </div>
            <br>
            <form id="updateForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="applicantUpdate"/>
                <input type="hidden" name="isBlocked" id="applicantUpdate" value="">
                <a class="btn btn-customized" onclick="applicantUpdate();"><fmt:message key="save.Save"/></a>
            </form>
        </div>
        </c:if>
    </div>
    <div id="certificate" style="display:none">
        <h5><fmt:message key="applicant.Certificate"/></h5>
        <img src="data:image/jpg;base64,${applicant.getCertificate()}" width="500"/>
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
                    <c:forEach items="${aList}" var="application" varStatus="loop">
                    <tr>
                        <td class="td-to-align">${loop.index + 1}</td>
                        <td class="td-to-align">${application.getFaculty().getFacultyList().get(0)}</td>
                        <td class="td-to-align">${application.getSumOfGrades()}</td>
                        <td class="td-to-align">${application.getAverageGrade()}</td>
                        <td class="td-to-align">${application.getApplicationStatus()}</td>
                        </c:forEach>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <c:forEach items="${aList}" var="application" varStatus="loop">
        <div class="panel-table">
            <h3 style="color: #75a3a3">
                <fmt:message key="application.SubjectsFor"/> ${application.getFaculty().getFacultyList().get(0)}:</h3>
            <div class="panel-heading">
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list sortable">
                        <thead>
                        <tr>
                            <th class="hidden-xs">№</th>
                            <th><fmt:message key="subject.Subject"/></th>
                            <th><fmt:message key="subject.Grade"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${application.getGradesList()}" var="subject" varStatus="loop">
                            <tr>
                                <td class="td-to-align">${loop.index + 1}</td>
                                <td class="td-to-align">${subject.getSubject().getSubjectList().get(0)}</td>
                                <td class="td-to-align">${subject.getGrade()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    <%@include file="../../js/applicant.js" %>
</script>
</body>
</html>
