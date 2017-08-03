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
            background-color: #eee;
            color: #666666;
            font-weight: bold;
            cursor: default;
        }
    </style>
    <title>Search Page</title>
</head>
<body>
<a href="index">Back to Main</a>

<h1>Find Boards by From and To Stations</h1>
<h3>Choose departure or arrival station, or both</h3>

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

<c:if test="${!empty boardList}">

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
                            ${dep.value}:00
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${distance}" var="distance">
                        <c:if test="${board.board_id eq distance.key}">
                            ${distance.value} km
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${avSpeed}" var="avs">
                        <c:if test="${board.board_id eq avs.key}">
                            ${avs.value} km/h
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${journeyTime}" var="time">
                        <c:if test="${board.board_id eq time.key}">
                            ${time.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${arrivals}" var="arr">
                        <c:if test="${board.board_id eq arr.key}">
                            ${arr.value}:00
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
                            ${arrival.value}:00
                        </c:if>
                    </c:forEach>
                </td>
                <c:if test="${sessionUser.role != 'anon'}">
                    <c:choose>
                        <c:when test="${ticketsAvailable.get(board.board_id) eq 'true'}">
                            <td class="button-container">
                                <button onclick="location.href = '/delay/add${board.board_id}'">Buy ticket</button>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td style="color:red">Tickets unavailable</td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
