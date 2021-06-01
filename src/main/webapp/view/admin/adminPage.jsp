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
    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="firstName.FirstName"/></th>
                        <th><fmt:message key="middleName.MiddleName"/></th>
                        <th><fmt:message key="lastName.LastName"/></th>
                        <th><fmt:message key="city.City"/></th>
                        <th><fmt:message key="region.Region"/></th>
                        <th><fmt:message key="schoolName.SchoolName"/></th>
                        <th><fmt:message key="status.Status"/></th>
                        <th><em class="fa fa-cog"></em></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${aList}" var="applicant" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td class="td-to-align">${applicant.getFirstName()}</td>
                            <td class="td-to-align">${applicant.getMiddleName()}</td>
                            <td class="td-to-align">${applicant.getLastName()}</td>
                            <td class="td-to-align">${applicant.getCity()}</td>
                            <td class="td-to-align">${applicant.getRegion()}</td>
                            <td class="td-to-align">${applicant.getSchoolName()}</td>
                            <c:if test="${not empty applicant.isBlocked()}">
                                <c:choose>
                                    <c:when test="${applicant.isBlocked() == true}">
                                        <td style=" background-color: #ff4d4d; border-color: #ff4d4d">
                                            <fmt:message key="status.Blocked"/></td>
                                    </c:when>
                                    <c:when test="${applicant.isBlocked() == false}">
                                        <td style=" background-color: #75a3a3; border-color: #75a3a3">
                                            <fmt:message key="status.Active"/></td>
                                    </c:when>
                                </c:choose>
                            </c:if>
                            <td align="center">
                                <a class="btn btn-danger" style=" background-color: #ffb366; border-color: #ffb366">
                                    <em class="fa fa-pencil"></em></a>
                                <a class="btn btn-danger" style=" background-color: #ff4d4d">
                                    <em class="fa fa-trash"></em></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-end">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
<script>
    <%@include file="../../js/admin.js"%>
</script>
</body>
</html>
