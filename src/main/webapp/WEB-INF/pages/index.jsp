<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title> | MeR</title>
    <style>
        .thumbnails {
            margin: 0;
            padding: 4px;
        }

        .another{
            min-height: 240px;
            max-height: 240px;
        }
    </style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                </div>
            </div>

            <div class="collapse navbar-collapse navbar-main-collapse">
                <div class="col-md-2 right col-md-offset-2">
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                        <p class="mail"><sec:authentication property="principal.username" /><i class="setting fa fa-cog"></i></p>
                        <div id="menu">
                            <div id="arrow"></div>
                            <div id="logout">
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/info">My info</a><br>
                                </sec:authorize>
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My tickets</a><br>
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/stations">Stations</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/boards">Boards</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/boardsadd">New board</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/users">Users</a><br>
                                </sec:authorize>
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/logout">Log out</a><br>
                            </div>
                        </div>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                        <a href="${pageContext.request.contextPath}/login">
                            <button class="btn btn-success">Log in</button>
                        </a>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</nav>

<!-- Preloader -->
<div id="preloader"><div id="load"></div></div>

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

<!-- Section: services -->
<section id="service" class="home-section text-center bg-gray" style="padding-top: 65px; padding-bottom: 100px;">
    <div class="heading-about">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="wow bounceInDown" data-wow-delay="0.4s">
                        <div class="section-heading">
                            <h2 style="color: #FFF">Stations</h2>
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
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=6&id2=0&time1=&time2=">
                                            <img src="${pageContext.request.contextPath}/resources/img/shire.jpg"
                                                 alt="#"></a></div>
                                    <div class="caption">
                                        <h3>Shire</h3>
                                        <h4>Land of vegans and vapers.</h4>
                                    </div>
                                </div>
                            </li>
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=2&id2=0&time1=&time2=">
                                            <img src="${pageContext.request.contextPath}/resources/img/isengard.jpg"
                                                 alt="#"></a>
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
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=3&id2=0&time1=&time2=">
                                            <img src="${pageContext.request.contextPath}/resources/img/mordor.jpg"
                                                 alt=""></a>
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
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=5&id2=0&time1=&time2=">
                                            <img src="${pageContext.request.contextPath}/resources/img/rohan.jpg"
                                                 alt=""></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Rohan</h3>
                                        <h4>Human wasteland in the middle of nowhere.</h4>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>

                    <div class="item another">
                        <ul class="thumbnails">

                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=1&id2=0&time1=&time2=">
                                            <img src="${pageContext.request.contextPath}/resources/img/gondor.jpg"
                                                 alt=""></a>
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
                                        <a href="${pageContext.request.contextPath}/searchboards?id1=4&id2=0&time1=&time2=">
                                            <img src="${pageContext.request.contextPath}/resources/img/rivendell.jpg"
                                                 alt=""></a>
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
                        <li><a data-slide="prev" href="#myCarousel" style="font-size: 15px; color: #49a827"><i
                                class="fa fa-chevron-left"></i></a></li>
                        <li><a data-slide="next" href="#myCarousel" style="font-size: 15px; color: #49a827"><i
                                class="fa fa-chevron-right"></i></a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</section>

<!-- /Section: services -->

<section id="contact" class="home-section text-center">
    <div class="heading-contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="wow bounceInDown" data-wow-delay="0.4s">
                        <div class="section-heading">
                            <h2>Get in touch</h2>
                            <i class="fa fa-2x fa-angle-down"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">

        <!-- Line -->
        <div class="row"><div class="col-lg-2 col-lg-offset-5"><hr class="marginbot-50"></div></div>


        <div class="row">

            <div class="col-lg-8">
                <div class="boxed-grey">
                    <form action="/send">
                        <div class="row" style="color: #545454">

                            <!--Left column -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <h4 style="color: #545454; margin: 0">Name</h4>
                                </div>
                                <div class="form-group">
                                    <input id="e-name" name="e-name" placeholder="Name" required="required">
                                </div>
                                <div class="form-group">
                                    <h4 style="color: #545454; margin: 15px 0 0 0">Your Email</h4>
                                </div>
                                <div class="form-group">
                                    <input id="e-adr" name="e-adr" placeholder="Address" pattern="^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$" required="required">
                                </div>
                                <div class="form-group">
                                    <h4 style="color: #545454; margin: 15px 0 0 0">Subject</h4>
                                </div>
                                <div class="form-group">
                                    <input id="e-sbj" name="e-sbj" placeholder="Subject" required="required">
                                </div>
                            </div>

                            <!-- Central column -->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <h4 style="color: #545454; margin: 0">Message</h4>
                                </div>
                                <div class="form-group">
                                    <textarea name="e-msg" id="e-msg" rows="7" cols="25" placeholder="Message Text" required="required"></textarea>
                                </div>
                                <div class="col-md-12">
                                    <button class="btn btn-success" style="width: 100%; margin: 15px 0 0 0">Send Message</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Right column -->
            <div class="col-lg-4">
                <div class="widget-contact">
                    <h3 style="color: #545454; margin: 0">Main Office</h3>

                    <address>
                        <i style="color: #545454">White Inc.</i>
                        <br>
                        <i style="color: #545454; margin: 0">221b, Black st., Mordor</i><br><br>
                        <h4 style="color: #545454; margin: 0">Skype</h4>
                        <i class="fa fa-skype" style="color: #545454; margin: 0"></i>
                        <a style="color: #545454; margin: 0" href="skype:kosta.eliseev">kosta.eliseev</a>
                    </address>

                    <address>
                        <h4 style="color: #545454; margin: 0">Email</h4>
                        <i class="fa fa-envelope" style="color: #545454; margin: 0"></i>
                        <a style="color: #545454; margin: 0" href="mailto:konstelis@gmail.com">konstelis@gmail.com</a>
                    </address>

                    <address>
                        <h4 style="color: #545454; margin: 0">Social networks</h4>
                        <ul class="company-social" style="margin: 7px 0 0 0">
                            <li class="social-github">
                                <a href="https://github.com/lletranger" target="_blank"><i class="fa fa-github"></i>
                                </a>
                            </li>
                            <li class="social-facebook">
                                <a href="https://www.facebook.com/yeliseyev.kot" target="_blank"><i class="fa fa-facebook"></i>
                                </a>
                            </li>
                            <li class="social-vk">
                                <a href="https://vk.com/id435491840" target="_blank"><i class="fa fa-vk" style="margin: 9px 1px 0 0 "></i>
                                </a>
                            </li>
                            <li class="social-twitter">
                                <a href="#" target="_blank">
                                    <i class="fa fa-twitter"></i>
                                </a>
                            </li>
                        </ul>
                    </address>

                </div>
            </div>
        </div>

    </div>
</section>
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

<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>

</body>
</html>