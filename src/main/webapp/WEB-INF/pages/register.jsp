<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Register page</title>

    <style>

        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
            /*font-weight: bold;*/
            cursor: default;

        }

        table.sortable th {
            text-align: center;
            font-size: 15px;
            height: 30px;

        }

        table.sortable td {
            text-align: center;
            color: #545454;
            font-size: 15px;
            height: 30px;
        }

        input, select, textarea {
            color: #545454;
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
                    <button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/login'">Back</button>
                </div>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
        </div>
    </div>
</nav>

<!-- Login form -->
<section class="content">
    <div class="container" align="center">
        <h2 align="center">Registration</h2>

        <c:url var="register" value="/register"/>
        <form:form action="${register}" modelAttribute="newUser" accept-charset="utf-8">

            <div class="row" style="color: #545454;">
                <div id="form-group-login" class="form-group col-lg-4 col-lg-offset-4">
                    <form:input path="login" minlength="4" maxlength="25" required="required"
                                placeholder="Login"/>
                </div>
            </div>

            <div class="row" style="color: #545454;">
                <div id="form-group-email" class="form-group col-lg-4 col-lg-offset-4">
                    <form:input path="email" pattern="^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$"
                                minlength="4" maxlength="25" placeholder="Email (optional)"/>
                </div>
            </div>


            <div class="row" style="color: #545454;">
                <div id="form-group-password" class="form-group col-lg-4 col-lg-offset-4">
                    <form:password path="password" minlength="4" maxlength="25"
                                   required="required" placeholder="Password"/>
                </div>
            </div>

            <div class="row" style="font-size: 15px; height: 25px; vertical-align: middle">
                <div class="form-group col-lg-4 col-lg-offset-4" style="color: #545454;">
                    <input id="showPassword" type="checkbox" onclick=
                            "if(password.type == 'text')
                                password.type='password';
                            else password.type='text';"/> Show password</span>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button type="submit" class="btn btn-success" style="width: 81%">Register</button>
                </div>
            </div>
        </form:form>
    </div>
</section>

<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>


</body>
</html>