<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<html>
<head>
    <title>Signup</title>
    <style>
        <%@include file="../../css/login.css" %>
    </style>
</head>
<body>
<%@ include file="../jspf/header.jspf" %>
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
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</body>
</html>
