<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Boards</title>
</head>
<body>
<a href="../../index.jsp">Back</a>

<h1>Boards List</h1>

<c:if test="${!empty boards}">
    <table class="tg">

        <tr>
            <th width="100">Name</th>
            <th width="100">From</th>
            <th width="100">To</th>
            <th width="75">Departure</th>
            <th width="75">Arrival</th>
            <th width="75">Delays</th>
        </tr>

        <c:forEach items="${boards}" var="board">
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
                    <c:forEach items="${arrivals}" var="arrival">
                        <c:if test="${board.board_id eq arrival.key}">
                            ${arrival.value}
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
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
