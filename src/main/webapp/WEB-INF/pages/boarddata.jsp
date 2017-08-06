<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

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

        table.sortable td {
            text-align: center;
            color: #ffffff;
        }

    </style>
    <title>Board Details</title>
    <link href="<c:url value="/resources/css/success-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>
<br>
<div class="container" align="center">
    <div class="row">
        <div class="form-group col-lg-4 col-lg-offset-4" align="center">
            <p align="center"><a href="${pageContext.request.contextPath}/index">Back to Main</a></p>
            <p align="center"><a href="${pageContext.request.contextPath}/boards">Boards</a></p>
        </div>
    </div>
</div>

<div class="container" style="width:100%;">

    <div align="center" class="container">

        <h1 align="center" style="color: #ffffff">Details on board '${boardDetailed.name}'</h1>
        <h2 align="center" style="color: #ffffff">Departing from
            station ${boardDetailed.from} to ${boardDetailed.to} at ${boardDetailed.departure}, estimate
            journey time ${boardDetailed.journeyTime},<br>total distance ${boardDetailed.distance}
            km, average speed ${boardDetailed.averageSpeed} km/h, ${passengers.size()} passengers on board:</h2>

        <c:if test="${!empty passengers}">
            <table class="sortable">
                <tr>
                    <th width="100">Name</th>
                    <th width="100">Surname</th>
                    <th class="sorttable_nosort" width="80">Birth Date</th>
                </tr>
                <c:forEach items="${passengers}" var="passenger">
                    <tr>
                        <td>${passenger.name}</td>
                        <td>${passenger.surname}</td>
                        <td>${passenger.birth_date}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <c:if test="${empty passengers}">
            <h1 style="color: red">No passengers for that trip yet!</h1>
        </c:if>
    </div>
</div>
</body>
</html>