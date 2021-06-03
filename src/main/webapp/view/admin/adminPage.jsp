<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="aList" value="${sessionScope['applicantList']}"/>

<html>
<head>
    <title>Admin Page</title>
    <style>
        <%@ include file="../../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <div class="align-right">
        <form class="inline" id="deleteForm" method="post" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="applicantDelete"/>
            <input type="hidden" name="applicantIdToDelete" id="applicantDelete" value="">
            <a class="btn btn-danger" onclick="applicantDelete();"
               style=" background-color: #ff4d4d">
                <em class="fa fa-trash"></em></a>
        </form>
        <form class="inline" id="getByIdForm" method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="getApplicantById"/>
            <input type="hidden" name="applicantId" id="getApplicantById" value="">
            <a class="btn btn-danger" onclick="applicantGetById();"
               style=" background-color: #75a3a3; border-color: #75a3a3">
                <em class="fa fa-eye"></em></a>
        </form>
    </div>

    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table id="pagination" class="table table-striped table-bordered table-list">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="applicant.FirstName"/></th>
                        <th><fmt:message key="applicant.MiddleName"/></th>
                        <th><fmt:message key="applicant.LastName"/></th>
                        <th><fmt:message key="applicant.City"/></th>
                        <th><fmt:message key="applicant.Status"/></th>
                        <th><em class="fa fa-check-square-o"></em></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${aList}" var="applicant" varStatus="loop">
                        <tr>
                            <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                            <td class="td-to-align"><c:out value="${applicant.getFirstName()}"/></td>
                            <td class="td-to-align"><c:out value="${applicant.getMiddleName()}"/></td>
                            <td class="td-to-align"><c:out value="${applicant.getLastName()}"/></td>
                            <td class="td-to-align"><c:out value="${applicant.getCity()}"/></td>
                            <c:if test="${not empty applicant.isBlocked()}">
                                <c:choose>
                                    <c:when test="${applicant.isBlocked() == true}">
                                        <td align="center">
                                            <button disabled type="button" class="btn btn-danger">
                                                <fmt:message key="applicant.Blocked"/></button>
                                        </td>
                                    </c:when>
                                    <c:when test="${applicant.isBlocked() == false}">
                                        <td align="center">
                                            <button disabled type="button" class="btn btn-success">
                                                <fmt:message key="applicant.Active"/></button>
                                        </td>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <td align="center">
                                <input type="radio" class="getId" name="getId" value="${applicant.getId()}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    <%@include file="../../js/applicant.js"%>
</script>
</body>
</html>
