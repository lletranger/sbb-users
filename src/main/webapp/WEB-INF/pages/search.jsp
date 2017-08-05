<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<html>
<head>
    <script src="/resources/js/sorttable.js"></script>

    <style>
        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
            font-weight: bold;
            cursor: default;
        }

        table.sortable td{
            text-align: center;
            color: #ffffff;
        }

    </style>
    <title>Search Page</title>
    <link href="<c:url value="/resources/css/admin-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
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
                            <a style="color:#49A827" href="${pageContext.request.contextPath}/login">Login</a><br>
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

        <h1 align="center">Find Boards by From and To Stations</h1>
        <h3 align="center">Choose departure or arrival station, or both</h3>

        <form action="searchboards">

    <label for="id1">From:</label>
    <select name="id1">
        <option value="0">-</option>
        <c:forEach var="station" items="${stations}">
            <option value="${station.station_id}"/>
            <c:out value="${station.name}"/>
            </option>
        </c:forEach>
    </select>

    <label for="id2"> To:</label>
    <select name="id2">
        <option value="0">-</option>
        <c:forEach var="station" items="${stations}">
            <option value="${station.station_id}"/>
            <c:out value="${station.name}"/>
            </option>
        </c:forEach>
    </select>
    <input type="submit" value="Search"/>

</form>

        <c:if test="${!empty dtos}">

            <h1>Available Boards</h1>
            <table class="sortable">
                <tr>
                    <th width="100">Board Name</th>
                    <th width="100">From</th>
                    <th width="100">To</th>
                    <th width="100">Departure</th>
                    <th class="sorttable_numeric" width="100">Distance</th>
                    <th class="sorttable_numeric" width="100">Average Speed</th>
                    <th class="sorttable_numeric" width="100">Journey Time</th>
                    <th width="100">Expected Arrival</th>
                    <th class="sorttable_numeric" width="100">Delay</th>
                    <th width="100">Arrival</th>
                </tr>
                <c:forEach items="${dtos}" var="dto">
                    <tr>
                        <td>${dto.name}</td>
                        <td>${dto.from}</td>
                        <td>${dto.to}</td>
                        <td>${dto.departure}</td>
                        <td>${dto.distance}</td>
                        <td>${dto.averageSpeed}</td>
                        <td>${dto.journeyTime}</td>
                        <td>${dto.expectedArrival}</td>
                        <td>${dto.delay}</td>
                        <td>${dto.arrival}</td>
                        <c:if test="${sessionUser.role != 'anon'}">
                            <c:choose>
                                <c:when test="${dto.ticketsAvailable}">
                                    <td class="button-container">
                                        <button onclick="location.href = '/ticket/add/${dto.id}'">Buy ticket</button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color:red">Unavailable</td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</section>
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
