<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<html>
<head>
    <title>Applicant Page</title>
    <style>
        <%@ include file="../../css/admin.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>
<div class="container">
    <div class="tab">
        <button class="tablinks" onclick="openPage(event, 'faculties')" id="defaultOpen"><fmt:message
                key="faculty.Faculties"/></button>
        <button class="tablinks" onclick="openPage(event, 'applications')"><fmt:message
                key="application.Applications"/></button>
    </div>

    <div id="faculties" class="tabcontent">
        <h3><fmt:message key="faculty.Faculties"/></h3>
        <p>London is the capital city of England.</p>
    </div>
    <div id="subjects" class="tabcontent">
        <h3><fmt:message key="faculty.Faculties"/></h3>
        <p>London is the capital city of England.</p>
    </div>
</div>
<script>
    <%@include file="../../js/admin.js"%>
</script>
</body>
</html>
