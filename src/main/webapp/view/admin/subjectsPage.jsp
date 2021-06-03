<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<html>
<head>
    <title>Subjects</title>
    <style>
        <%@ include file="../../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>

<div class="container">
    <div class="align-right">
        <c:if test="${role == 'ADMIN'}">
            <a class="inline" href="${pageContext.request.contextPath}/controller?command=facultyCreateChoice">
                <button type="button" class="btn btn-danger"
                        style=" background-color: #339966; border-color: #339966; color:#1e2f2f">
                    <em class="fa fa-plus"></em></button>
            </a>
            <form class="inline" id="updateForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="subjectUpdate"/>
                <input type="hidden" name="subjectIdToUpdate" id="update" value="">
                <a class="btn btn-danger" onclick="updateSubject();"
                   style=" background-color: #ffb366; border-color: #ffb366">
                    <em class="fa fa-pencil"></em></a>
            </form>
            <form class="inline" id="deleteForm" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="subjectDelete"/>
                <input type="hidden" name="subjectIdToDelete" id="delete" value="">
                <a class="btn btn-danger" onclick="deleteSubject();"
                   style=" background-color: #ff4d4d">
                    <em class="fa fa-trash"></em></a>
            </form>
        </c:if>
        <form class="inline" id="getSubjectById" method="get" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="getSubjectById"/>
            <input type="hidden" name="facultyId" id="getById" value="">
            <a class="btn btn-danger" onclick="getSubjectById();"
               style=" background-color: #75a3a3; border-color: #75a3a3">
                <em class="fa fa-eye"></em></a>
        </form>
    </div>

    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table class="table table-striped table-bordered table-list sortable">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="subject.Subject"/>></th>
                        <th><fmt:message key="subject.PassingGrade"/></th>
                        <th><em class="fa fa-check-square-o"></em></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sList}" var="faculty" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td><c:out value="${faculty.getSubjectList().get(0)}"/></td>
                            <td class="td-to-align"><c:out value="${faculty.getPassingGrade()}"/></td>
                            <td align="center">
                                <input type="radio" class="getId" name="getId" value="<c:out value="${faculty.getId()}"/>"/>
                            </td>
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