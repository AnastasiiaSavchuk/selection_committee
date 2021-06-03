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
            <a class="inline" href="${pageContext.request.contextPath}/controller?command=facultyCreateChoice">
                <button type="button" class="btn btn-danger"
                        style=" background-color: #339966; border-color: #339966; color:#262626">
                    <em class="fa fa-plus"></em></button>
            </a>
            <form class="inline" id="updateForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="facultyUpdate"/>
                <input type="hidden" name="facultyIdToUpdate" id="facultyUpdate" value="">
                <a class="btn btn-danger" onclick="facultyUpdate();"
                   style=" background-color: #ffb366; border-color: #ffb366">
                    <em class="fa fa-pencil"></em></a>
            </form>
            <form class="inline" id="deleteForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="facultyDelete"/>
                <input type="hidden" name="facultyIdToDelete" id="facultyDelete" value="">
                <a class="btn btn-danger" onclick="facultyDelete();"
                   style=" background-color: #ff4d4d">
                    <em class="fa fa-trash"></em></a>
            </form>
        </c:if>
        <form class="inline" id="getByIdForm" method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="getFacultyById"/>
            <input type="hidden" name="facultyId" id="facultyGetById" value="">
            <a class="btn btn-danger" onclick="getFacultyById();"
               style=" background-color: #75a3a3; border-color: #75a3a3">
                <em class="fa fa-eye"></em></a>
        </form>
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
                            <th><em class="fa fa-check-square-o"></em></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fList}" var="faculty" varStatus="loop">
                        <tr>
                            <td class="td-to-align"><c:out value="${loop.index + 1}"/></td>
                            <td class="td-to-align"><c:out value="${faculty.getFacultyList().get(0)}"/></td>
                            <td class="td-to-align"><c:out value="${faculty.getTotalQty()}"/></td>
                            <td class="td-to-align"><c:out value="${faculty.getBudgetQty()}"/></td>
                            <c:if test="${role == 'ADMIN'}">
                                <td align="center">
                                    <input type="radio" class="getId" name="getId" value="<c:out value="${faculty.getId()}"/>"/>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    <%@include file="../js/faculty.js" %>
</script>
</body>
</html>