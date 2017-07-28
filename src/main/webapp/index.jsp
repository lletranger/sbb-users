<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta firstName="viewport" content="width=device-width, initial-scale=1.0">
    <meta firstName="description" content="Middle-earth Railroads">
    <title> | MeR</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
<!-- Preloader -->
<div id="preloader">
    <div id="load"></div>
</div>
<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand" href="index.jsp">
                    </a>
                    <div><span id="txt"></span></div>
                    <h2></h2>
                </div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->

            <div class="collapse navbar-collapse navbar-main-collapse">

                <div class="col-md-2 right col-md-offset-2">
                    <c:choose>
                        <c:when test="${user.role != 'anonym'}">
                            <p class="mail">${user.login}<i class="setting fa fa-cog"></i></p>
                            <div id="menu">
                                <div id="arrow"></div>
                                <div id="logout">
                                    <a href="${pageContext.request.contextPath}/user/logout">Log out</a><br>
                                    <c:if test="${user.role == 'anonym'}">
                                            <a href="${pageContext.request.contextPath}/admin0564044">Admin panel</a>
                                    </c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/user/auth">
                                <button class="btn btn-success log-btn">Log in</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-md-4 right ">
                    <ul class="list nav navbar-nav ">
                        <li><a href="/../../index.jsp">Home</a></li>
                        <li><a href="/users">Users</a></li>
                        <li><a href='/boards'>Schedule</a></li>
                        <li><a href="/search">Search</a></li>
                        <%--<li><a href="/stations">Stations</a></li>--%>
                        <%--<li><a href="/tickets">Tickets</a></li>--%>
                        <%--<li><a href="/passengers">Passengers</a></li>--%>
                    </ul>
                </div>
            </div>

            <!-- /.navbar-collapse -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</nav>

<!-- Section: intro -->
<section id="intro" class="intro">

    <div class="slogan">
        <h2>Middle-earth railroads</h2>
        <h4>Go to Mordor today!</h4>
    </div>
    <div class="page-scroll">
        <a href="${pageContext.request.contextPath}/user/registration" class="btn btn-circle">
            <p class="animated">Sign up</p>
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
                                        <img src="${pageContext.request.contextPath}/resources/img/shire.jpg" alt="#">
                                    </div>
                                    <div class="caption">
                                        <h3>Shire</h3>
                                        <h4>Land of vegans and vapers.</h4>
                                    </div>
                                </div>
                            </li>
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <img src="${pageContext.request.contextPath}/resources/img/isengard.jpg" alt="#">
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
                                        <img src="${pageContext.request.contextPath}/resources/img/mordor.jpg" alt="">
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
                                        <img src="${pageContext.request.contextPath}/resources/img/rohan.jpg" alt="">
                                    </div>
                                    <div class="caption">
                                        <h3>Rohan</h3>
                                        <h4>Human wasteland in the middle of nowhere.</h4>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div><!-- /Slide1 -->
                    <div class="item">
                        <ul class="thumbnails">
                            <li class="col-sm-3">
                                <div class="fff">
                                    <div class="thumbnail">
                                        <a href="#"><img src="${pageContext.request.contextPath}/resources/img/gondor.jpg" alt=""></a>
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
                                        <a href="#"><img src="${pageContext.request.contextPath}/resources/img/rivendell.jpg" alt=""></a>
                                    </div>
                                    <div class="caption">
                                        <h3>Rivendell</h3>
                                        <h4>Elven place with good food. Very posh.</h4>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div><!-- /Slide2 -->
                </div>


                <nav>
                    <ul class="control-box pager">
                        <li><a data-slide="prev" href="#myCarousel" class=""><i class="fa fa-chevron-left"></i></a></li>
                        <li><a data-slide="next" href="#myCarousel" class=""><i class="fa fa-chevron-right"></i></a></li>
                    </ul>
                </nav>
                <!-- /.control-box -->

            </div><!-- /#myCarousel -->

        </div><!-- /.col-xs-12 -->

    </div>
</section>
<!-- /Section: services -->

<!-- Section: contact -->
<section id="contact" class="home-section text-center">
    <div class="heading-contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <div class="wow bounceInDown" data-wow-delay="0.4s">
                        <div class="section-heading">
                            <h2>Send us a letter</h2>
                            <i class="fa fa-2x fa-angle-down"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <div class="col-lg-2 col-lg-offset-5">
                <hr class="marginbot-50">
            </div>
        </div>
        <div class="row">
            <div class="col-lg-8">
                <div class="boxed-grey">
                    <form id="contact-form">
                        <div class="row">
                            <div class="col-md-6">

                                <div class="form-group">
                                    <label for="firstName">Name</label><input type="text" class="form-control" id="firstName" placeholder="Your name" required="required" />
                                </div>

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <div class="input-group">
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-envelope"></span>
                                        </span>
                                        <input type="email" class="form-control" id="email" placeholder="Your email" required="required" /></div>
                                </div>
                                <div class="form-group">
                                    <label for="subject">Subject</label>
                                    <input type="text" class="form-control" id="subject" placeholder="Message subject" />
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="message">Message</label>
                                    <textarea message="message" id="message" class="form-control" rows="9" cols="25" required="required" placeholder="Message"></textarea>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <button type="submit" class="btn btn-success pull-right" id="btnContactUs">Send Message</button>
                            </div>

                        </div>
                    </form>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="widget-contact">
                    <h5>Main Office</h5>

                    <address>
                        <strong>White Inc.</strong><br>220b, Black st, Mordor<br>
                        <i class="fa fa-skype"> kosta.eliseev</i><br>
                    </address>

                    <address>
                        <strong>Email</strong><br>
                        <a href="mailto:#">konstelis@gmail.com</a>
                    </address>
                    <address>
                        <strong>Social networks</strong><br>
                        <ul class="company-social">
                            <li class="btn-instagram"><a href="#" target="_blank"><i class="fa fa-facebook"></i></a></li>
                            <li class="btn-instagram"><a href="#" target="_blank"><i class="fa fa-vk"></i></a></li>
                            <li class="btn-instagram"><a href="#" target="_blank"><i class="fa fa-twitter"></i></a></li>
                            <li class="btn-instagram"><a href="#" target="_blank"><i class="fa fa-instagram"></i></a></li>
                            <li class="btn-instagram"><a href="https://github.com/lletranger" target="_blank"><i class="fa fa-github"></i></a></li>

                        </ul>
                    </address>

                </div>
            </div>
        </div>

    </div>
</section>
<!-- /Section: contact -->

<footer>
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