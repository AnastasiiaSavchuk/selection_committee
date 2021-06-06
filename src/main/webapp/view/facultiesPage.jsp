<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="fList" value="${sessionScope['facultyList']}"/>
<html>
<head>
    <title>Faculties</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <div class="align-right">
        <c:if test="${role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/controller?command=facultyCreateChoice">
                <button class="button" type="submit"><fmt:message key="faculty.CreateNewFaculty"/></button>
            </a>
        </c:if>
    </div>
    <div class="panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table id="pagination" class="table table-striped table-bordered table-list sortable">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="faculty.Faculty"/></th>
                        <th><fmt:message key="faculty.TotalPlaces"/></th>
                        <th><fmt:message key="faculty.BudgetPlaces"/></th>
                        <c:if test="${role == 'ADMIN'}">
                            <th colspan="2"><em class="fa fa-cog fa-lg"></em></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fList}" var="faculty" varStatus="loop">
                    <tr>
                        <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                        <td class="td-to-align">
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="getFacultyById"/>
                                <input type="hidden" name="facultyId"
                                       value="<c:out value="${faculty.getId()}"/>">
                                <button class="buttonChose"><c:out value="${faculty.getFacultyList().get(0)}"/></button>
                            </form>
                        </td>
                        <td class="td-to-align"><c:out value="${faculty.getTotalQty()}"/></td>
                        <td class="td-to-align"><c:out value="${faculty.getBudgetQty()}"/></td>
                        <c:if test="${role == 'ADMIN'}">
                        <td align="center">
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="facultyUpdate"/>
                                <input type="hidden" name="facultyIdToUpdate"
                                       value="<c:out value="${faculty.getId()}"/>">
                                <button class="tdButton"><fmt:message key="update.Update"/></button>
                            </form>
                        </td>
                        <td align="center">
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="facultyDelete"/>
                                <input type="hidden" name="facultyIdToDelete"
                                       value="<c:out value="${faculty.getId()}"/>">
                                <button class="tdButton"><fmt:message key="delete.Delete"/></button>
                            </form>
                        </td>
                        </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>