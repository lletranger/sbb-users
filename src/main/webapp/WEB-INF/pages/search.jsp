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

    <jsp:include page="temps/navbar.jsp"/>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title>Search page</title>

    <style>
        body {
            background: linear-gradient(90deg, rgb(255, 255, 255) 10%, #ffffff 90%);
            color: #545454;
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

<section class="content" style="vertical-align: middle">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">Find Boards by Stations</h1>
        <h4 align="center" style="color: #545454">Choose departure and/or arrival</h4>

        <form action="${pageContext.request.contextPath}/searchboards">

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

            <label style="color: #545454">departing</label>
            <br>
            <label style="color: #545454">after: </label>
            <input id="time1" data-format="HH:mm" data-template="HH:mm" name="time1" value=${time1}>

            <label style="color: #545454"> and before: </label>
            <input id="time2" data-format="HH:mm" data-template="HH:mm" name="time2" value=${time2}>
            <br>
            <button class="btn btn-success" style="width: 10%">Search</button>
        </form>

<c:if test="${!empty dtos}">
        <section class="content">
            <div class="container" align="center">
                <h1 align="center" style="color: #49a827">Available Boards</h1>

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
                        <c:choose>
                            <c:when test="${dto.ticketsAvailable}">
                                <td>
                                    <button class="btn btn-success"
                                            onclick="location.href = '${pageContext.request.contextPath}/ticket/add/${dto.id}'">Buy Ticket
                                    </button>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td style="color:red">Unavailable</td>
                            </c:otherwise>
                        </c:choose>
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
        <script src="<c:url value="/resources/js/moment.min.js"/>"></script>
        <script src="<c:url value="/resources/js/combodate.js"/>"></script>
        <script src="<c:url value="/resources/js/sorttable.js"/>"></script>

        <script>
            $('input').combodate({
                firstItem: 'name',
                minuteStep: 5
            })
        </script>

</body>
</html>