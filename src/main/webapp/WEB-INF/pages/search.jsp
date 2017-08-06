<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>

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
    <script src="/resources/js/sorttable.js"></script>
    <title>Search page</title>

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
            padding: 10px;

        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 50px;
            width: 9%;
        }

    </style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
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
                        <c:when test="${sessionUser.role ne 'anon'}">
                            <p class="mail">${sessionUser.login}<i class="setting fa fa-cog"></i></p>

                            <div id="menu">
                                <div id="arrow"></div>
                                <div id="logout">
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/logout">Log out</a><br>
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My tickets</a><br>
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>
                                    <c:if test="${sessionUser.role eq 'admin'}">
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/boards">Boards</a><br>
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/users">Users</a><br>
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/stations">Stations</a><br>
                                    </c:if>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login">
                                <button class="btn btn-success log-btn">Log in</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-4 center-block">
                    <ul class="list nav navbar-nav" style="align-items: center">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content" style="vertical-align: middle">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">Find Boards by Stations</h1>
        <h4 align="center" style="color: #545454">Choose departure and/or arrival</h4>

        <form action="searchboards">

            <label style="color: #545454">From: </label>
            <select name="id1">
                <option value="0">- Select -</option>
                <c:forEach items="${stations}" var="station">
                    <option
                            <c:if test="${station.station_id eq s1}">selected</c:if>
                            value="${station.station_id}">${station.name} </option>
                </c:forEach>
            </select>
            <label style="color: #545454">to: </label>
            <select name="id2">
                <option value="0">- Select -</option>
                <c:forEach items="${stations}" var="station">
                    <option value="${station.station_id}"
                            <c:if test="${station.station_id eq s2}">selected</c:if>>${station.name} </option>
                </c:forEach>
            </select>

            <button type="submit" class="btn btn-success" style="width: 10%">Search</button>
        </form>

<c:if test="${!empty dtos}">
        <section class="content">
            <div class="container" align="center">
                <h1 align="center"style="color: #49a827">Available Boards</h1>

                <table class="sortable">
                <tr>
                    <th>Route</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Departure</th>
                    <th class="sorttable_numeric">Distance</th>
                    <th class="sorttable_numeric">Average Speed</th>
                    <th class="sorttable_numeric">Journey Time</th>
                    <th>Expected Arrival</th>
                    <th class="sorttable_numeric">Delay</th>
                    <th>Arrival</th>
                    <th class="sorttable_nosort"></th>
                </tr>
                <c:forEach items="${dtos}" var="dto">
                    <tr>
                        <td>${dto.name}</td>
                        <td>${dto.from}</td>
                        <td>${dto.to}</td>
                        <td>${dto.departure}</td>
                        <td>${dto.distance} km</td>
                        <td>${dto.averageSpeed} km/h</td>
                        <td>${dto.journeyTime}</td>
                        <td>${dto.expectedArrival}</td>
                        <td>${dto.delay}</td>
                        <td>${dto.arrival}</td>
                        <c:if test="${sessionUser.role != 'anon'}">
                            <c:choose>
                                <c:when test="${dto.ticketsAvailable}">
                                    <td>
                                        <button class="btn btn-success"
                                                onclick="location.href = '/ticket/add/${dto.id}'">Buy Ticket
                                        </button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color:red">Unavailable</td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${sessionUser.role == 'anon'}">
                            <td>
                                <button class="btn btn-success"
                                        onclick="location.href = '/login'">Buy Ticket
                                </button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
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