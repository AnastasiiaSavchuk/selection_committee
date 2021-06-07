<%@ include file="/view/includes/init.jsp" %>
<c:set var="role" value="${sessionScope['applicantRole']}"/>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="https://www.kryogenix.org/code/browser/sorttable/sorttable.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" id="bootstrap-css">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<style>
    <%@include file="../../css/header.css" %>
</style>

<header id="header">
    <a href="${pageContext.request.contextPath}/index.jsp">
        <div class="pull-left">
            <h1 class="scrollto"><fmt:message key="title.Title"/></h1></div>
    </a>
    <nav id="nav-menu-container">
        <div class="nav-menu">
            <c:if test="${not empty role}">
                <c:choose>
                    <c:when test="${role == 'ADMIN'}">
                        <p class="menu-active">
                            <a href="${pageContext.request.contextPath}/controller?command=adminPage">
                                <fmt:message key="admin.Admin"/></a></p>
                    </c:when>

                    <c:when test="${role == 'USER'}">
                        <p class="menu-active"><a
                                href="${pageContext.request.contextPath}/controller?command=applicantPage">
                            <fmt:message key="applicant.Applicant"/></a></p>
                    </c:when>
                </c:choose>
            </c:if>

            <p class="menu-active"><a href="${pageContext.request.contextPath}/controller?command=getFaculties">
                <fmt:message key="faculty.Faculties"/></a></p>

            <c:if test="${role == 'ADMIN'}">
                <p class="menu-active">
                    <a href="${pageContext.request.contextPath}/controller?command=getSubjects">
                        <fmt:message key="subject.Subjects"/></a></p>
            </c:if>

            <p class="menu-active"><a href="${pageContext.request.contextPath}/view/contactUs.jsp">
                <fmt:message key="contacts.Contacts"/></a></p>

            <c:if test="${empty role}">
                <p class="menu-active">
                    <a href="${pageContext.request.contextPath}/controller?command=loginChoice">
                        <fmt:message key="login.Login"/></a></p>

                <p class="menu-active">
                    <a href="${pageContext.request.contextPath}/controller?command=signupChoice">
                        <fmt:message key="signup.Signup"/></a></p>
            </c:if>

            <c:if test="${not empty role}">
                <p class="menu-active"><a href="${pageContext.request.contextPath}/controller?command=logout">
                    <fmt:message key="logout.Logout"/></a></p>
            </c:if>

            <p class="menu-active">
                <a class="nav-link" href="" id="english-id" onclick="setLanguage(this); return false;">EN</a></p>

            <p class="menu-active">
                <a class="nav-link" href="" id="ukrainian-id" onclick="setLanguage(this); return false;">UK</a></p>
        </div>
    </nav>
</header>
<script>
    <%@include file="../../js/header.js" %>
</script>
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