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

<h1>Add a Station</h1>

<c:url var="addAction" value="/stations/add"/>

<form:form action="${addAction}" commandName="station">
    <table>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="x">
                    <spring:message text="X coordinate"/>
                </form:label>
            </td>
            <td>
                <form:input path="x"/>
        </tr>
        <tr>
            <td>
                <form:label path="y">
                    <spring:message text="Y coordinate"/>
                </form:label>
            </td>
            <td>
                <form:input path="y"/>
            </td>
        </tr>
        <tr>
            <input type="submit" value="<spring:message text="Add Station"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
