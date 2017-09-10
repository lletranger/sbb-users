<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

    <jsp:include page="../temps/navbar.jsp"/>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title>Your ticket</title>

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
            width: 200px;

        }

        table.sortable th {
            text-align: right;
            padding-right: 25px;
            height: 40px;
            width: 200px;
        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 40px;
            width: 200px;
        }

    </style>

</head>


<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">Details on ticket</h1>
        <table class="sortable" style="color: #545454">
            <tr>
                <th class="sorttable_nosort" >Passenger name:</th>
                <td>${passenger.name}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Passenger surname:</th>
                <td>${passenger.surname}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Passenger birth date:</th>
                <td>${passenger.birth_date}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Ticket number:</th>
                <td>${ticket.ticket_id}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Board name:</th>
                <td>${board.name}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >From:</th>
                <td>${from}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >To:</th>
                <td>${to}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Departing:</th>
                <td>${board.departure}</td>
            </tr>
        </table>
    </div>

     <br><h2 align="center" style="color: #49a827">Have a nice trip!</h2>
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
