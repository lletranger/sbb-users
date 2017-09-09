<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title>Passenger error</title>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll" style="margin-top: 8px">
                    <button class="btn btn-success"
                            onclick="location.href='${pageContext.request.contextPath}/ticket/add/${dupeBoard.board_id}'">
                        Ticket</button>
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
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">${dupePassenger.name} ${dupePassenger.surname} already has ticket</h1>
        <h1 align="center" style="color: #49a827">for board '${dupeBoard.name}' departing ${dupeBoard.departure}!</h1>
        <div style="margin-bottom: 20px"><img src="${pageContext.request.contextPath}/resources/img/noplaces.jpg"></div>
        <h2 align="center" style="color: #49a827">Have a nice journey :)</h2>
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