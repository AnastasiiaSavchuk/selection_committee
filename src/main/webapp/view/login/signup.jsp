<%@ include file="/view/includes/init.jsp" %>
<html>
<head>
    <title>Signup</title>
    <style>
        <%@include file="../../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="../includes/header.jsp" %>
<div class="login-page">
    <div class="form">
        <form class="signup-form" method="post" action="controller">
            <input type="hidden" name="command" value="signup"/>
            <h2><fmt:message key="signup.Signup"/></h2>
            <hr>
            <div class="input-group mb-3"><input class="form-control" name="email" type="email"
                                                 placeholder="<fmt:message key="email.Email"/>" required/></div>
            <div class="input-group mb-3"><input class="form-control" name="password" type="password"
                                                 placeholder="<fmt:message key="password.Password"/>" required/></div>
            <div class="input-group mb-3"><input class="form-control" name="confirmPassword" type="password"
                                                 placeholder="<fmt:message key="signup.ConfirmPassword"/>" required/>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="signup.Signup"/></button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
