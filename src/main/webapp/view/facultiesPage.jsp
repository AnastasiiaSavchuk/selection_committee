<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<c:set var="fList" value="${sessionScope['facultyList']}"/>
<html>
<head>
    <title>Title</title>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet'
          type='text/css'>
    <style>
        <%@ include file="../css/faculty.css" %>
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
                            <fmt:message key="faculties.Faculties"/></h2></div>
                    </div>
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-list">
                            <thead>
                            <tr>
                                <th class="hidden-xs">№</th>
                                <th><fmt:message key="faculties.Faculties"/>
                                    <a id="nameSort" onclick="facultiesSortDash(this)">
                                        <span class="glyphicon glyphicon-arrow-down"></span></a></th>

                                <th><fmt:message key="faculty.BudgetPlaces"/>
                                    <a id="budgetSort" href="" onclick="facultiesSortDash(this)">
                                        <span class="glyphicon glyphicon-arrow-down"></span></a></th>

                                <th><fmt:message key="faculty.TotalPlaces"/>
                                    <a id="totalSort" href="" onclick="facultiesSortDash(this)">
                                        <span class="glyphicon glyphicon-arrow-down"></span></a></th>

                                <th><fmt:message key="subject.Subjects"/>
                                    <span class="glyphicon glyphicon-arrow-down"></span></a></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${fList}" var="faculty" varStatus="loop">
                            <tr>
                                <td>${loop.index + 1}</td>
                                <td>${faculty.getFacultyList().get(0)}</td>
                                <td class="td-to-align">${faculty.getBudgetQty()}</td>
                                <td class="td-to-align">${faculty.getTotalQty()}</td>
                                </c:forEach>
                            </tr>
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