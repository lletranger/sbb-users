<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>

    <jsp:include page="../temps/navbar.jsp"/>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title>Oups..!</title>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: red">Something went wrong!</h1>
        <h2 align="center" style="color: red">(on our site :)</h2>
        <div style="margin-bottom: 20px"><img src="${pageContext.request.contextPath}/resources/img/WentWrong.gif"></div>
        <h2 align="center" style="color: red">Please, come back later</h2>
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