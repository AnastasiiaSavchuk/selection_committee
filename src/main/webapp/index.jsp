<%@ include file="/view/includes/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>About University</title>
    <style>
        <%@ include file="css/index.css" %>
    </style>
</head>
<body>
<%@ include file="view/includes/header.jsp" %>

<div class="slideshow">
    <div class="start-photo">
        <img src="image/GAF1.jpg" alt="" width="2560" height="550">
    </div>
    <div class="start-photo">
        <img src="image/GAF2.jpg" alt="" width="2560" height="550">
    </div>
    <div class="start-photo">
        <img src="image/GAF3.jpg" alt="" width="2560" height="550">
    </div>

    <div style="text-align: center; margin-top: -2.5%;">
        <button class="dot" onclick="currentSlide(1)"></button>
        <button class="dot" onclick="currentSlide(2)"></button>
        <button class="dot" onclick="currentSlide(3)"></button>
    </div>
</div>

<div class="container indexMainCtr">
    <h2><fmt:message key="about.About"/></h2>
    <hr>
    <div id="about-us-text-id">
        <p class="indexText"><fmt:message key="index.sentence1"/></p>
        <p class="indexText"><fmt:message key="index.sentence2"/></p>
        <p class="indexText"><fmt:message key="index.sentence3"/></p>
    </div>
</div>
<script>
    <%@include file="js/slideShow.js"%>
</script>
</body>
</html>
