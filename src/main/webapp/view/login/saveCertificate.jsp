<%@ include file="../includes/init.jsp" %>
<html>
<head>
    <title>User DashBoard</title>
    <style>
        <%@include file="../../css/login.css" %>
    </style>
</head>
<body>

<%@ include file="../includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" name="certUploadForm" method="post" enctype="multipart/form-data"
              action='${pageContext.request.contextPath}/controller?command=saveCertificate'>
            <h2><fmt:message key="applicant.InsertCertificate"/></h2>
            <hr>
            <div class="input-group mb-3">
                <input class="form-control" type="file" name="certificateFile" id="certificateFile" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="save.Save"/></button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
