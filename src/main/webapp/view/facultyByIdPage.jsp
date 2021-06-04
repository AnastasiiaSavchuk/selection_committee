<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="faculty" value="${sessionScope['faculty']}"/>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<c:set var="aList" value="${sessionScope['applicationList']}"/>
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
    <div class="entityData">
        <h3><c:out value="${faculty.getFacultyList().get(0)}"/></h3>
        <hr>
        <h5><fmt:message key="faculty.TotalPlaces"/> : <c:out value="${faculty.getTotalQty()}"/></h5>
        <h5><fmt:message key="faculty.BudgetPlaces"/> : <c:out value="${faculty.getBudgetQty()}"/></h5>
    </div>

    <div class="align-right">
        <div class="formInline">
            <a href="${pageContext.request.contextPath}/controller?command=getFaculties">
                <button class="tdButton" type="button" style="margin: 15px 0;">
                    <fmt:message key="return.Return"/></button>
            </a>
        </div>
        <c:if test="${not empty role}">
            <c:choose>
                <c:when test="${role == 'ADMIN'}">
                    <button class="tdButton formInline" type="button" onclick="getApplications(this)"
                            style="width: 280px!important;">
                        <fmt:message key="faculty.DisplayApplication"/></button>
                </c:when>
                <c:when test="${role == 'USER'}">
                    <form class="formInline" method="post" action="controller">
                        <input type="hidden" name="command" value="applyToTheFaculty"/>
                        <input type="hidden" name="applyToTheFaculty"
                               value="<c:out value="${faculty.getId()}"/>">
                        <button style="width: 300px !important;"><fmt:message key="application.Apply"/></button>
                    </form>
                </c:when>
            </c:choose>
        </c:if>
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

    <div id="application" style="display:none">
        <div class=" panel-table">
            <div class="panel-heading">
                <div class="panel-body">
                    <h2 style="text-align: center"><fmt:message key="application.Applications"/></h2>
                    <table class="table table-striped table-bordered table-list sortable">
                        <thead>
                        <tr>
                            <th class="hidden-xs">№</th>
                            <th><fmt:message key="applicant.FullName"/></th>
                            <th><fmt:message key="application.SumOfGrades"/></th>
                            <th><fmt:message key="application.AverageGrade"/></th>
                            <th><fmt:message key="application.Status"/></th>
                            <th colspan="2"><em class="fa fa-cog fa-lg"></em></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${aList}" var="application" varStatus="loop">
                            <tr>
                                <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                                <td class="td-to-align">
                                    <form class="formInline" method="post" action="controller">
                                        <input type="hidden" name="command" value="applicationById"/>
                                        <input type="hidden" name="applicationId"
                                               value="<c:out value="${faculty.getId()}"/>">
                                        <button style="text-align: left !important; border: none !important; width: 100% !important;">
                                            <c:out value="${application.getApplicant().getFirstName()}"/> <c:out
                                                value="${application.getApplicant().getMiddleName()}"/> <c:out
                                                value="${application.getApplicant().getLastName()}"/>
                                        </button>
                                    </form>
                                </td>
                                <td class="td-to-align"><c:out value="${application.getSumOfGrades()}"/></td>
                                <td class="td-to-align"><c:out value="${application.getAverageGrade()}"/></td>
                                <td align="center">
                                    <c:if test="${not empty application.getApplicationStatus()}">
                                        <c:choose>
                                            <c:when test="${application.getApplicationStatus() == 'IN_PROCESSING'}">
                                                <button disabled class="btn btn-warning">
                                                    <fmt:message key="application.IN_PROCESSING"/></button>
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
                                                <button disabled class="btn btn-success">
                                                    <fmt:message key="application.CONTRACT_APPROVED"/></button>
                                            </c:when>
                                        </c:choose>
                                    </c:if>
                                </td>
                                <td align="center">
                                    <form method="post" action="controller">
                                        <input type="hidden" name="command" value="applicationDelete"/>
                                        <input type="hidden" name="applicationIdToDelete"
                                               value="<c:out value="${application.getId()}"/>">
                                        <button class="tdButton"><fmt:message key="delete.Delete"/></button>
                                    </form>
                                </td>
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
    <%@include file="../js/applicant.js"%>
</script>
</body>
</html>
