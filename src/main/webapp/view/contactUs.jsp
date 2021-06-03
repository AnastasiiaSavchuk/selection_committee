<%@ include file="/view/jspf/directive.jspf" %>
<%@ include file="/view/jspf/localesSettings.jsp" %>
<head>
    <title>Contact Us</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"
          integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
    <style>
        <%@ include file="../css/index.css" %>
    </style>
</head>
<body>
<%@ include file="/view/jspf/header.jspf" %>
<section id="contact">
    <div class="container">
        <h3 class="text-center text-uppercase"><fmt:message key="contacts.Contacts"/></h3>
        <div class="row">
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <i class="fa fa-phone fa-5x mb-3" aria-hidden="true"></i>
                        <h4 class="text-uppercase mb-5"><fmt:message key="contacts.GeneralOffice"/></h4>
                        <p><a href="tel:+380322394111">+38 (032) 239-41-11</a></p>
                        <p><a href="tel:+380322394325">+38 (032) 239-43-25</a></p>

                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <i class="fa fa-phone fa-5x mb-3" aria-hidden="true"></i>
                        <h4 class="text-uppercase mb-5"><fmt:message key="contacts.Admissions"/></h4>
                        <p><a href="tel: +380322553965"> +38 (032) 255-39-65</a></p>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <i class="fa fa-map-marker fa-5x mb-3" aria-hidden="true"></i>
                        <h4 class="text-uppercase mb-5"><fmt:message key="contacts.Address"/></h4>
                        <address><fmt:message key="contacts.Address1"/></address>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-3 my-5">
                <div class="card border-0">
                    <div class="card-body text-center">
                        <i class="fa fa-globe fa-5x mb-3" aria-hidden="true"></i>
                        <h4 class="text-uppercase mb-5"><fmt:message key="contacts.Email"/></h4>
                        <a href="mailto:zag_kan@lnu.edu.ua">zag_kan@lnu.edu.ua</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
        integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
        integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
        crossorigin="anonymous"></script>
</body>
</html>