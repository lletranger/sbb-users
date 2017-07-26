<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Stations page</title>
</head>
<body>
<a href="../../index.jsp">Back</a>

<h1>Station List</h1>

<c:if test="${!empty allStations}">
    <table class="tg">
        <tr>
            <th width="40">ID</th>
            <th width="100">Name</th>
            <th width="50">X</th>
            <th width="50">Y</th>
        </tr>
        <c:forEach items="${allStations}" var="station">
            <tr>
                <td>${station.station_id}</td>
                <td>${station.name}</td>
                <td>${station.x}</td>
                <td>${station.y}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
