<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="sList" value="${sessionScope['subjectList']}"/>
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
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6"><h2 class="panel-title">
                            <fmt:message key="faculty.Faculties"/></h2></div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="hidden-xs">№</th>
                                <th><fmt:message key="subject.Subject"/>></th>
                                <th><fmt:message key="subject.PassingGrade"/></th>
                                <th><em class="fa fa-cog"></em></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sList}" var="faculty" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${faculty.getSubjectList().get(0)}</td>
                                    <td class="td-to-align">${faculty.getPassingGrade()}</td>
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
        </div>
    </div>
</div>
</body>
</html>