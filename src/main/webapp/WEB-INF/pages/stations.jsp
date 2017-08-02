<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
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

    <title>Stations Page</title>
</head>
<body>
<a href="index">Back to Main</a>

<br/>
<br/>

<h1>Stations List</h1>
<c:if test="${!empty allStations}">
    <table class="sortable">
        <tr>
            <th width="40">ID</th>
            <th width="100">Name</th>
            <th width="50">X crd, km.</th>
            <th width="50">Y crd, km.</th>
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
<div>

    <br/>
    <br/>

    <h1>Add a Station</h1>

    <c:url var="addAction" value="/stations/add"/>

    <form:form action="${addAction}" commandName="station">
        <table>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Station Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" maxlength="45" required="required" placeholder="Name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="x">
                        <spring:message text="X Coordinate"/>
                    </form:label>
                </td>
                <td>
                        <form:input path="x" pattern="^-?[0-9]{1,3}" required="required" placeholder="-999 to 999 km."/>
            </tr>
            <tr>
                <td>
                    <form:label path="y">
                        <spring:message text="Y Coordinate"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="y" pattern="^-?[0-9]{1,3}" required="required" placeholder="-999 to 999 km."/>
                </td>
            </tr>
        </table>
        <input type="submit" value="<spring:message text="Add"/>"/>
    </form:form>
</div>
</body>
</html>