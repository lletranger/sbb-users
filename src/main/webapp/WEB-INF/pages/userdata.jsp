<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
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
    <jsp:include page="temps/navbar.jsp"/>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <title>User details</title>
    <style>
        body {
            background: linear-gradient(90deg, rgb(255, 255, 255) 10%, #ffffff 90%);
            color: #49a827;
            font-family: 'Helvetica', sans-serif;
        }

        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            cursor: default;
            text-align: left;

        }

        table.sortable th {
            text-align: center;
            height: 40px;
        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 40px;
        }

    </style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" align="center">
        <h1 align="center">'${user.username}' Details</h1>

        <table class="sortable">
            <tr>
                <th class="sorttable_nosort" width="40">ID</th>
                <th class="sorttable_nosort" width="120">Login</th>
                <th class="sorttable_nosort" width="120">Email</th>
                <th class="sorttable_nosort" width="120">Status</th>
            </tr>
            <tr>
                <td>${user.user_id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
            </tr>
        </table>
    </div>
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