<%@ include file="/view/includes/init.jsp" %>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
<html>
<head>
    <title>Subjects</title>
    <style>
        <%@ include file="../css/table.css" %>
    </style>
</head>
<body>
<%@ include file="includes/header.jsp" %>

<div class="container">
    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <h2 style="text-align: center"><fmt:message key="subject.Subjects"/></h2>
                <div class="align-right">
                    <c:if test="${role == 'ADMIN'}">
                        <div class="inRow">
                            <a href="${pageContext.request.contextPath}/controller?command=subjectCreateChoice">
                                <button class="button" type="submit"><fmt:message
                                        key="subject.CreateNewSubject"/></button>
                            </a>
                        </div>
                    </c:if>
                </div>
                <table id="pagination" class="table table-striped table-bordered table-list sortable">
                    <thead>
                    <tr>
                        <th class="hidden-xs">№</th>
                        <th><fmt:message key="subject.Subject"/></th>
                        <th><fmt:message key="subject.PassingGrade"/></th>
                        <th colspan="2"><em class="fa fa-cog fa-lg"></em></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sList}" var="subject" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td><c:out value="${subject.getSubjectList().get(0)}"/></td>
                            <td class="td-to-align"><c:out value="${subject.getPassingGrade()}"/></td>
                            <td align="center" class="tdWidth">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="subjectUpdate"/>
                                    <input type="hidden" name="subjectIdToUpdate"
                                           value="<c:out value="${subject.getId()}"/>">
                                    <button class="tdButton"><fmt:message key="update.Update"/></button>
                                </form>
                            </td>
                            <td align="center" class="tdWidth">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="subjectDelete"/>
                                    <input type="hidden" name="subjectIdToDelete"
                                           value="<c:out value="${subject.getId()}"/>">
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
</body>
</html>