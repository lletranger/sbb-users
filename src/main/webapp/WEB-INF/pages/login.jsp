<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta firstName="viewport" content="width=device-width, initial-scale=1.0">
    <meta firstName="description" content="Middle-earth Railroads">
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">

    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <title>Login page</title>
    <style>
        body {
            background: linear-gradient(90deg, #ffffff 10%, #ffffff 90%);
            color: #fff;
            font-family: 'Open Sans', sans-serif;
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
                    <a class="navbar-brand" href="index.jsp"></a>
                </div>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-main-collapse">
                <div class="col-md-2 right col-md-offset-2">
                    <p class="mail" style="font: bold"><i class="setting fa fa-cog"></i></p>
                    <div id="menu">
                        <div id="arrow"></div>
                        <div id="logout">
                            <a style="color:#49A827" href="${pageContext.request.contextPath}/index">Back to Main</a><br>
                            <a style="color:#49A827" href="${pageContext.request.contextPath}/register">Register</a><br>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 center-block">
                    <ul class="list nav navbar-nav" style="align-items: center">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content">
    <div class="container" align="center">
        <h2 align="center">Login</h2><br>

        <c:url var="getIn" value="/login"/>
        <form:form action="${getIn}" modelAttribute="loginUser">

            <div class="row">
                <div id="form-group-email" class="form-group col-lg-4 col-lg-offset-4">
                    <tr></tr>

                    <form:input style="color:#49A827" path="login" minlength="4" maxlength="45" required="required"
                                placeholder="Login"/>
                </div>
            </div>

            <div class="row">
                <div id="form-group-password" class="form-group col-lg-4 col-lg-offset-4">
                    <form:password style="color:#49A827" path="password" minlength="4" maxlength="45"
                                   required="required" placeholder="Password"/>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-lg-4 col-lg-offset-4" style="color: #49A827">
                    <input type="checkbox" onclick="if(password.type=='text')password.type='password';
                    else password.type='text';"/> Show password</span>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button type="submit" class="btn btn-success">Login</button>
                    <a href="${pageContext.request.contextPath}/register" class="btn btn-success">Register</a>
                </div>
            </div>
        </form:form>

    </div>
    <br>
    <br>

</section>
<!-- Core JavaScript Files -->
<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<c:url value="/resources/js/custom.js"/>"></script>
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
</body>
</html>