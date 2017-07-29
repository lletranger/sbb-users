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
            background-color:#eee;
            color:#666666;
            font-weight: bold;
            cursor: default;
        }
    </style>
    <title>Stations</title>
</head>
<body>
<a href="../../index.jsp">Back to Main</a>

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

<h1>Add Station</h1>

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
                    <spring:message text="X coord."/>
                </form:label>
            </td>
            <td>
                <form:input path="x"/>
        </tr>
        <tr>
            <td>
                <form:label path="y">
                    <spring:message text="Y coord."/>
                </form:label>
            </td>
            <td>
                <form:input path="y"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="<spring:message text="Add Station"/>"/>
</form:form>
</div>
</body>
</html>
