<%@ include file="/view/includes/init.jsp" %>
<c:set var="aList" value="${sessionScope['applicantList']}"/>
<html>
<head>
    <title>Admin Page</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/includes/header.jsp" %>

<div class="container">
    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <h2 style="text-align: center"><fmt:message key="applicant.Applicants"/></h2>
                <table id="pagination" class="table table-striped table-bordered table-list">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="applicant.FullName"/></th>
                        <th><fmt:message key="applicant.City"/></th>
                        <th><fmt:message key="applicant.Status"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${aList}" var="applicant" varStatus="loop">
                        <tr>
                            <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                            <td class="td-to-align">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="getApplicantById"/>
                                    <input type="hidden" name="applicantId"
                                           value="<c:out value="${applicant.getId()}"/>">
                                    <button class="buttonChose"><c:out value="${applicant.getLastName()}"/> <c:out
                                            value="${applicant.getFirstName()}"/> <c:out
                                            value="${applicant.getMiddleName()}"/>
                                    </button>
                                </form>
                            <td class="td-to-align"><c:out value="${applicant.getCity()}"/></td>
                            <c:if test="${not empty applicant.isBlocked()}">
                                <c:choose>
                                    <c:when test="${applicant.isBlocked() == true}">
                                        <td align="center">
                                            <button disabled class="btn btn-danger">
                                                <fmt:message key="applicant.Blocked"/></button>
                                        </td>
                                    </c:when>
                                    <c:when test="${applicant.isBlocked() == false}">
                                        <td align="center">
                                            <button disabled class="btn btn-success">
                                                <fmt:message key="applicant.Active"/></button>
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
