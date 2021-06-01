<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="fList" value="${sessionScope['facultyList']}"/>
<html>
<head>
    <title>Faculties</title>
    <style>
        <%@ include file="../../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <c:if test="${role == 'ADMIN'}">
        <div>
            <a href="${pageContext.request.contextPath}/controller?command=facultyCreateChoice">
                <button type="button" class="button"><fmt:message key="createNew.CreateNew"/></button>
            </a>
            <form id="updateForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="facultyUpdate"/>
                <input type="hidden" name="facultyIdToUpdate" id="update" value="">
                <a class="btn btn-danger" onclick="updateFaculty();" style=" background-color: #ffb366; border-color: #ffb366">
                    <em class="fa fa-pencil"></em></a>
            </form>
            <form id="deleteForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="facultyDelete"/>
                <input type="hidden" name="facultyIdToDelete" id="delete" value="">
                <a class="btn btn-danger" onclick="deleteFaculty();" style=" background-color: #ff4d4d">
                    <em class="fa fa-trash"></em></a>
            </form>
        </div>
    </c:if>
    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table class="table table-striped table-bordered table-list">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>

                        <th><fmt:message key="faculty.Faculty"/>
                            <a id="nameSort" onclick="facultiesSortDash(this)">
                                <span class="glyphicon glyphicon-arrow-down"></span></a></th>

                        <th><fmt:message key="faculty.BudgetPlaces"/>
                            <a id="budgetSort" href="" onclick="facultiesSortDash(this)">
                                <span class="glyphicon glyphicon-arrow-down"></span></a></th>

                        <th><fmt:message key="faculty.TotalPlaces"/>
                            <a id="totalSort" href="" onclick="facultiesSortDash(this)">
                                <span class="glyphicon glyphicon-arrow-down"></span></a></th>

                        <c:if test="${role == 'ADMIN'}">
                            <th><em class="fa fa-cog"></em></th>
                            <th><em class="fa fa-cog"></em></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fList}" var="faculty" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${faculty.getFacultyList().get(0)}</td>
                            <td class="td-to-align">${faculty.getBudgetQty()}</td>
                            <td class="td-to-align">${faculty.getTotalQty()}</td>

                            <c:if test="${role == 'ADMIN'}">
                                <td align="center">
                                    <input type="radio" class="getId" name="getId" value="${faculty.getId()}"/>
                                </td>
                            </c:if>
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
    <%@include file="../../js/faculty.js" %>
</script>
</body>
</html>