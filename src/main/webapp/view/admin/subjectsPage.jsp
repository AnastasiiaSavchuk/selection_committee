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
            <a href="${pageContext.request.contextPath}/controller?command=subjectCreateChoice">
                <button class="tdButton" type="submit" style="width: 270px !important;">
                    <fmt:message key="subject.CreateNewSubject"/></button>
            </a>
        </c:if>
    </div>
    <div class=" panel-table">
        <div class="panel-heading">
            <div class="panel-body">
                <table class="table table-striped table-bordered table-list sortable">
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
                            <td align="center">
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="subjectUpdate"/>
                                    <input type="hidden" name="subjectIdToUpdate"
                                           value="<c:out value="${subject.getId()}"/>">
                                    <button class="tdButton"><fmt:message key="update.Update"/></button>
                                </form>
                            </td>
                            <td align="center">
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