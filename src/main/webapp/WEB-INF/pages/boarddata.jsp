<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
    <title>Board Details</title>
</head>
<body>
<a href="../boards">Back to Boards</a>

<br/>
<br/>

<h1>Details on Board ${board.name}</h1>
<p>Departing ${board.departure} from station ${from} to ${to}, estimate journey time ${time}, total distance ${distance}
    km., average speed ${speed} km/h., registered ${onBoard.size()} passengers.
</p>
<c:if test="${!empty onBoard}">
    <h3>Passengers:</h3>
    <table class="sortable">
        <tr>
            <th width="100">Name</th>
            <th width="100">Surname</th>
            <th width="80">Birth Date</th>
        </tr>
        <c:forEach items="${onBoard}" var="passenger">
            <tr>
                <td>${passenger.name}</td>
                <td>${passenger.surname}</td>
                <td>${passenger.birth_date}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty onBoard}">
    <h1>No passengers for that trip yet!</h1>
</c:if>

</body>
</html>