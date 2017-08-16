<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <title> | MeR</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar">
<!-- Preloader -->
<div id="preloader">
    <div id="load"></div>
</div>

<!-- Navigation -->
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand" href="index.jsp"></a>
                </div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->

            <div class="collapse navbar-collapse navbar-main-collapse">
                <div class="col-md-2 right col-md-offset-2">
                    <c:choose>
                        <c:when test="${sessionUser.role ne 'anon'}">
                            <p class="mail">${sessionUser.login}<i class="setting fa fa-cog"></i></p>
                            <div id="menu">
                                <div id="arrow"></div>
                                <div id="logout">
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My tickets</a><br>
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>
                                    <c:if test="${sessionUser.role eq 'admin'}">
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/stations">Stations</a><br>
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/boards">Boards</a><br>
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/users">Users</a><br>
                                    </c:if>
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/logout">Log out</a><br>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login">
                                <button class="btn btn-success log-btn">Log in</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-4 center-block">
                    <ul class="list nav navbar-nav" style="align-items: center">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<!-- Section: intro -->
<section id="intro" class="intro">

    <div class="slogan">
        <h2>Middle-earth railroads</h2>
        <h3 style="font-size: 35px">Go to Mordor today!</h3>
    </div>

    <div class="page-scroll">
        <a href="${pageContext.request.contextPath}/search" class="btn btn-circle">
            <p class="animated">Search</p>
            <i class="fa fa-angle-double-down fa-2x animated"></i>
        </a>
    </div>
</section>
<!-- /Section: intro -->


<!-- Section: services -->
<section id="service" class="home-section text-center bg-gray">
    <div class="heading-about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="wow bounceInDown" data-wow-delay="0.4s">
                        <div class="section-heading">
                            <h3>Stations</h3>
                            <i class="fa fa-2x fa-angle-down"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="col-md-12">
            <div class="carousel slide" id="myCarousel">
                <div class="carousel-inner">
                    <div class="item active">
                        <ul class="thumbnails">
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=6&id2=0">
                                            <img src="${pageContext.request.contextPath}/resources/img/shire.jpg" alt="#"></a></div>
                                    <div class="caption">
                                        <h3>Shire</h3>
                                        <h4>Land of vegans and vapers.</h4>
                                    </div>
                                </div>
                            </li>
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=2&id2=0">
                                            <img src="${pageContext.request.contextPath}/resources/img/isengard.jpg" alt="#"></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Isengard</h3>
                                        <h4>Nicest observation deck. Lift out of service though.</h4>

                                    </div>
                                </div>
                            </li>
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=3&id2=0">
                                        <img src="${pageContext.request.contextPath}/resources/img/mordor.jpg" alt=""></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Mordor</h3>
                                        <h4>Epic scenery, lots of hot springs.</h4>

                                    </div>
                                </div>
                            </li>
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=5&id2=0">
                                            <img src="${pageContext.request.contextPath}/resources/img/rohan.jpg" alt=""></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Rohan</h3>
                                        <h4>Human wasteland in the middle of nowhere.</h4>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="item">
                        <ul class="thumbnails">
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=1&id2=0">
                                            <img src="${pageContext.request.contextPath}/resources/img/gondor.jpg" alt=""></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Gondor</h3>
                                        <h4>Marble city. So many stairs.</h4>
                                    </div>
                                </div>
                            </li>
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=4&id2=0">
                                            <img src="${pageContext.request.contextPath}/resources/img/rivendell.jpg" alt=""></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Rivendell</h3>
                                        <h4>Elven place with good food. Very posh.</h4>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>

                <nav>
                    <ul class="control-box pager">
                        <li><a data-slide="prev" href="#myCarousel" style="font-size: 15px; color: #49a827"><i class="fa fa-chevron-left"></i></a></li>
                        <li><a data-slide="next" href="#myCarousel" style="font-size: 15px; color: #49a827"><i class="fa fa-chevron-right"></i></a>
                        </li>
                    </ul>
                </nav>



            </div><!-- /#myCarousel -->
        </div>
    </div>
</section>
<!-- /Section: services -->




<%--<!-- Section: contact -->--%>
<%--<section id="contact" class="home-section text-center">--%>
    <%--<div class="heading-contact">--%>
        <%--<div class="container">--%>
            <%--<div class="row">--%>
                <%--<div class="col-lg-8 col-lg-offset-2">--%>
                    <%--<div class="wow bounceInDown" data-wow-delay="0.4s">--%>
                        <%--<div class="section-heading">--%>
                            <%--<h2>Send us a letter</h2>--%>
                            <%--<i class="fa fa-2x fa-angle-down" style="font-size: 65px; color: #fff106"></i>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="container">--%>

        <%--<div class="row">--%>
            <%--<div class="col-lg-2 col-lg-offset-5">--%>
                <%--<hr class="marginbot-50">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>
            <%--<div class="col-lg-8">--%>
                <%--<div class="boxed-grey">--%>
                    <%--<form id="contact-form">--%>
                        <%--<div class="row">--%>
                            <%--<div class="col-md-6">--%>


                                <%--<form:form method="POST" commandName="send" action="sendEmail">--%>
                                    <%--From: <form:input  path="from"/> <br/>--%>
                                    <%--To: <form:input  path="to"/> <br/>--%>
                                    <%--Subject: <form:input path="subject"/><br/>--%>
                                    <%--Message: <form:input path="message"/><br/>--%>
                                    <%--<input type="submit" value="Send">--%>
                                <%--</form:form>--%>
                                <%----%>
                                <%--<c:url var="sendMail" value="/sendEmail"/>--%>
                                <%--<form:form action="${sendMail}" commandName="mail">--%>

                                <%--<div class="form-group">--%>
                                    <%--<label for="firstName">Name</label>--%>
                                    <%--<input type="text" class="form-control" id="firstName"--%>
                                           <%--placeholder="Your name" required="required"/>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<label for="from">Email</label>--%>
                                    <%--<div class="input-group">--%>
                                        <%--<span class="input-group-addon">--%>
                                            <%--<span class="glyphicon glyphicon-envelope"></span>--%>
                                        <%--</span>--%>
                                        <%--<input type="email" class="form-control" id="email" placeholder="Your email"--%>
                                               <%--required="required"/></div>--%>
                                <%--</div>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label for="subject">Subject</label>--%>
                                    <%--<input type="text" class="form-control" id="subject" placeholder="Message subject"/>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="col-md-6">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label for="message">Message</label>--%>
                                    <%--<textarea message="message" id="message" class="form-control" rows="9" cols="25"--%>
                                              <%--required="required" placeholder="Message"></textarea>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="col-md-12">--%>
                                <%--<button type="submit" class="btn btn-success pull-right" id="btnContactUs">Send--%>
                                    <%--Message--%>
                                <%--</button>--%>
                            <%--</div>--%>


                            <%--</form:form>--%>

                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            <%--</div>--%>

            <%--<div class="col-lg-4">--%>
                <%--<div class="widget-contact">--%>
                    <%--<h5>Main Office</h5>--%>

                    <%--<address>--%>
                        <%--<strong>White Inc.</strong><br>220b, Black st, Mordor<br>--%>
                        <%--<i class="fa fa-skype"> kosta.eliseev</i><br>--%>
                    <%--</address>--%>

                    <%--<address>--%>
                        <%--<strong>Email</strong><br>--%>
                        <%--<a href="mailto:#">konstelis@gmail.com</a>--%>
                    <%--</address>--%>
                    <%--<address>--%>
                        <%--<strong>Social networks</strong><br>--%>
                        <%--<ul class="company-social">--%>
                            <%--<li class="btn-facebook"><a href="https://www.facebook.com/yeliseyev.kot" target="_blank"><i class="fa fa-facebook"></i></a></li>--%>
                            <%--<li class="btn-vk"><a href="https://vk.com/id435491840" target="_blank"><i class="fa fa-vk"></i></a></li>--%>
                            <%--<li class="btn-instagram"><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>--%>
                            <%--<li class="btn-instagram"><a href="#" target="_blank"><i class="fa fa-instagram"></i></a>--%>
                            <%--</li>--%>
                            <%--<li class="btn-instagram"><a href="https://github.com/lletranger" target="_blank"><i--%>
                                    <%--class="fa fa-github"></i></a></li>--%>

                        <%--</ul>--%>
                    <%--</address>--%>

                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

    <%--</div>--%>
<%--</section>--%>
<!-- /Section: contact -->

<footer id="footer">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-lg-12">
                <div class="wow shake" data-wow-delay="0.4s">
                    <div class="page-scroll marginbot-30">
                        <a href="#intro" id="totop" class="btn btn-circle">
                            <i class="fa fa-angle-double-up animated"></i>
                        </a>
                    </div>
                </div>
                <p style="color: #FFFFFF">&copy; Copyright 2017 - Kosta Eliseev. All rights reserved.</p>
            </div>
        </div>
    </div>
</footer>

<!-- Core JavaScript Files -->
<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<c:url value="/resources/js/custom.js"/>"></script>
</body>
</html>