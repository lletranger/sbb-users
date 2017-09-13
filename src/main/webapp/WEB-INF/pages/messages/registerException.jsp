<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>

    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title>Exception</title>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll" style="margin-top: 8px">
                    <button class="btn btn-success"
                            onclick="location.href='${pageContext.request.contextPath}/register'">
                        Back</button>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">${registerException}</h1>
        <div style="margin-bottom: 20px"><img src="${pageContext.request.contextPath}/resources/img/Duplicates.jpg"></div>
        <h2 align="center" style="color: #49a827">Please, choose another one</h2>
    </div>
</section>

<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>
<script src="<c:url value="/resources/js/sorttable.js"/>"></script>

</body>
</html>