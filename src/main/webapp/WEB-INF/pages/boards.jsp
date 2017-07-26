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

        </tr>
        <c:forEach items="${boards}" var="board">
           <tr>
               <td>${board.name}</td>

               <c:forEach items="${stations}" var="station">
                   <c:if test="${station.station_id eq board.from_id}">
                       <td>${station.name}</td>
                   </c:if>

                   <c:if test="${station.station_id eq board.to_id}">
                        <td>${station.name}</td>
                   </c:if>
                </c:forEach>


               <c:forEach items="${departures}" var="dep">
                   <c:if test="${board.board_id eq dep.key}">
                       <td>${dep.value}</td>
                   </c:if>
               </c:forEach>

               <c:forEach items="${arrivals}" var="arrival">
                   <c:if test="${board.board_id eq arrival.key}">
                       <td>${arrival.value}</td>
                   </c:if>
               </c:forEach>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
