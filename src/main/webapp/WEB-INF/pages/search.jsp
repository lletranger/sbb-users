<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <script src="/resources/js/sorttable.js"></script>
    <style>
        table.sortable thead {
            background-color: #eee;
            color: #666666;
            font-weight: bold;
            cursor: default;
        }
    </style>
    <title>Search Page</title>
</head>
<body>
<a href="../../index.jsp">Back to Main</a>

    <h1>Find Boards by From and To Stations</h1>


<form action="searchboards">

    <label for="id1">From:</label>
    <input type="number" id="id1" name="id1" placeholder="id1"/> and/or

    <label for="id2">To:</label>
    <input type="number" id="id2" name="id2" placeholder="id2"/>

    <input type="submit" value="Search"/>
</form>

<c:if test="${!empty boardList}">

<h1>Available Boards</h1>
    <table class="sortable">
        <tr>
            <th width="100">Board Name</th>
            <th width="100">From</th>
            <th width="100">To</th>
            <th width="100">Departure</th>
            <th width="100">Distance</th>
            <th width="100">Average Speed</th>
            <th width="100">Journey Time</th>
            <th width="100">Estimated Arrival</th>
            <th width="100">Delay</th>
            <th width="100">Arrival</th>
        </tr>

        <c:forEach items="${boardList}" var="board">
            <tr>
                <td>${board.name}</td>

                <td>
                    <c:forEach items="${stations}" var="station">
                        <c:if test="${station.station_id eq board.from_id}">
                            ${station.name}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${stations}" var="station">
                        <c:if test="${station.station_id eq board.to_id}">
                            ${station.name}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${departures}" var="dep">
                        <c:if test="${board.board_id eq dep.key}">
                            ${dep.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${journeyTime}" var="jt">
                        <c:if test="${board.board_id eq jt.key}">
                            ${jt.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${arrivals}" var="ea">
                        <c:if test="${board.board_id eq ea.key}">
                            ${ea.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${delays}" var="delay">
                        <c:if test="${board.board_id eq delay.key}">
                            ${delay.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${totalTime}" var="arrival">
                        <c:if test="${board.board_id eq arrival.key}">
                            ${arrival.value}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>