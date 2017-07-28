<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>

    <script src="/resources/js/sorttable.js"></script>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/dataTables.bootstrap.min.js" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/jquery.dataTables.min.js" />" rel="stylesheet">

    <style>
        table.sortable thead {
            background-color:#eee;
            color:#666666;
            font-weight: bold;
            cursor: default;
        }
    </style>
</head>
<body>
<a href="../boards">Back to Boards</a>

<h1>Passengers for Board ${board.name}</h1>
<h3>Departing ${board.departure} from station ${from} to ${to},<br/>
    estimate journey time ${time}, total distance ${distance} km., average speed ${speed} km/h.<br/>
    ${onBoard.size()} passengers was found.
</h3>


<c:if test="${!empty onBoard}">
    <table id="table1" class="table" cellspacing="0" width="100%">
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
