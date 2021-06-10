<%@ include file="/view/includes/init.jsp" %>
<html>
<head>
    <title>Signup Details</title>
    <style>
        <%@include file="../../css/login.css" %>
    </style>
</head>
<body>

<%@ include file="../includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="signupDetails"/>
            <h2><fmt:message key="signup.Signup"/></h2>
            <hr>
            <div class="input-group mb-3"><input class="form-control" name="firstName" type="text"
                                                 placeholder="<fmt:message key="applicant.FirstName"/>" required/></div>
            <div class="input-group mb-3"><input class="form-control" name="middleName" type="text"
                                                 placeholder="<fmt:message key="applicant.MiddleName"/>" required/>
            </div>
            <div class="input-group mb-3"><input class="form-control" name="lastName" type="text"
                                                 placeholder="<fmt:message key="applicant.LastName"/>" required/></div>
            <div class="input-group mb-3"><input class="form-control" name="city" type="text"
                                                 placeholder="<fmt:message key="applicant.City"/>" required/></div>
            <div class="input-group mb-3"><input class="form-control" name="region" type="text"
                                                 placeholder="<fmt:message key="applicant.Region"/>" required/></div>
            <div class="input-group mb-3"><input class="form-control" name="schoolName" type="text"
                                                 placeholder="<fmt:message key="applicant.SchoolName"/>" required/>
            </div>
            <h4><fmt:message key="applicant.InsertCertificate"/></h4>
            <div class="input-group mb-3">
                <input type="file" class="form-control" required/>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="signup.Signup"/></button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
