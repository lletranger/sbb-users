<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-social.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login page</title>

    <style>

        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
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

<nav class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll" style="margin-top: 8px">
                    <button class="btn btn-success"
                            onclick="location.href='${pageContext.request.contextPath}/index'">
                        Main</button>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content" style="vertical-align: middle">
    <div class="container" align="center">
        <h2 align="center">Login</h2>

        <c:url var="getIn" value="/login"/>
        <form:form action="${getIn}" modelAttribute="loginUser">

            <div class="row" style="color: #545454;">
                <div lang="en" id="form-group-username" class="form-group col-lg-4 col-lg-offset-4">
                    <form:input path="username" minlength="4" maxlength="45"
                                required="required" placeholder="Username"/>
                </div>
            </div>

            <div class="row" style="color: #545454;">
                <div id="form-group-password" class="form-group col-lg-4 col-lg-offset-4">
                    <form:password path="password" minlength="4" maxlength="45"
                                   required="required" placeholder="Password"/>
                </div>
            </div>

            <div class="row" style="font-size: 15px; height: 25px; vertical-align: middle">
                <div class="form-group col-lg-4 col-lg-offset-4" style="color: #545454;">
                    <input title="chbx" type="checkbox" onclick=
                            "if(password.type == 'text')password.type='password';
                    else password.type='text';"/>
                    Show password</span>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button class="btn btn-success" style="width: 133px">Login</button>
                    <button type=button class="btn btn-success" style="width: 133px" onclick="location.href='${pageContext.request.contextPath}/register'">Register</button>
                </div>

                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button type=button class="btn btn-facebook" style="width: 292px; float: none">Login with Facebook</button>
                </div>

                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button type=button class="btn btn-vk" style="width: 292px; float: none">Login with VK</button>
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