<%@ include file="/view/includes/init.jsp" %>
<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="../../css/login.css" %>
    </style>
</head>
<body>

<%@ include file="../includes/header.jsp" %>

<div class="login-page">
    <div class="form">
        <form class="login-form" method="post" action="controller">
            <input type="hidden" name="command" value="login"/>
            <h2><fmt:message key="login.Login"/></h2>
            <hr>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="email-label">
                        <i class="fa fa-user-circle" aria-hidden="true"></i></span></div>
                <input class="form-control" name="email" type="email"
                       placeholder="<fmt:message key="email.Email"/>" required></div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="password-label">
                        <i class="fa fa-key" aria-hidden="true"></i></span></div>
                <input class="form-control" name="password" type="password"
                       placeholder="<fmt:message key="password.Password"/>" required></div>

            <div class="text-center">
                <button type="submit" class="btn btn-customized"><fmt:message key="login.Login"/></button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
