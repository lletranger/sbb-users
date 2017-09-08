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
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <jsp:include page="temps/navbar.jsp"/>

    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <title>Stations page</title>
    <style>
        body {
            background: linear-gradient(90deg, rgb(255, 255, 255) 10%, #ffffff 90%);
            color: #49a827;
            font-family: 'Helvetica', sans-serif;
        }

        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
            font-weight: bold;
            cursor: default;

        }

        table.sortable th {
            text-align: center;
            height: 50px;

        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 40px;
            width: 85px;
        }

    </style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" style="width:49%; float:left" align="center">
        <h1 align="center">Stations List</h1>
        <c:if test="${!empty allStations}">
            <table class="sortable">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>X km.</th>
                    <th>Y km.</th>
                </tr>
                <c:forEach items="${allStations}" var="station">
                    <tr>
                        <td>${station.station_id}</td>
                        <td>${station.name}</td>
                        <td>${station.x}</td>
                        <td>${station.y}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>

    <div class="container" style="width:49%; float:right" align="center">
        <h1 align="center">Add Station</h1>

        <c:url var="addAction" value="/admin/stations/add"/>
        <form:form action="${addAction}" commandName="station">
            <table class="sorttable_nosort" style="color: #545454">
                <tr>
                    <th style='width: 100px;'>
                        <spring:message text="Name: "/>
                    </th>
                    <td>
                        <form:input path="name" maxlength="45" required="required"
                                    placeholder="Station name" style="width: 150px; font-weight: normal;"/>
                    </td>
                </tr>
                <tr>
                    <th style='width: 100px;'>
                        <spring:message text="X coord.: "/>
                    </th>
                    <td>
                        <form:input path="x" pattern="^-?[0-9]{1,3}" required="required"
                                    placeholder="-999 to 999 in km" style="width: 150px"/>
                    </td>
                </tr>
                <tr>
                    <th style='width: 100px;'>
                        <spring:message text="Y coord.: "/>
                    </th>
                    <td>
                        <form:input path="y" pattern="^-?[0-9]{1,3}" required="required"
                                    placeholder="-999 to 999 in km" style="width: 150px;"/>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <input class="btn btn-success" style="width: 150px"
                               type="submit" value="<spring:message text="Add station"/>"/>
                    </td>
                </tr>
            </table>
        </form:form>
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