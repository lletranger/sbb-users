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
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
            font-weight: bold;
            cursor: default;
        }

        table.sortable td{
            text-align: center;
            color: #ffffff;
        }

    </style>

    <title>Stations Page</title>
    <link href="<c:url value="/resources/css/error-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>
<br>
<div class="container" align="center">
    <div class="row">
        <div class="form-group col-lg-4 col-lg-offset-4" align="center">
            <p align="center"><a href="${pageContext.request.contextPath}/index">Back to Main</a></p>
        </div>
    </div>
</div>

<div class="container" style="width:100%;">

    <div align="center" class="container" style="width:49%; float:left;">
        <h1 align="center" style="color: #ffffff">Stations List</h1>
        <c:if test="${!empty allStations}">
        <table class="sortable" style="color: #ffffff;">
            <tr>
                <th width="50">ID</th>
                <th width="125">Name</th>
                <th width="100">X km.</th>
                <th width="100">Y km.</th>
            </tr><c:forEach items="${allStations}" var="station">
            <tr>
                <td>${station.station_id}</td>
                <td>${station.name}</td>
                <td>${station.x}</td>
                <td>${station.y}</td>
            </tr>
        </c:forEach>
        </table>
        </c:if>
    </div>

    <div align="center" class="container" style="width:49%; float:right;">
        <h1 align="center">Add a Station</h1>
    <c:url var="addAction" value="/stations/add"/>
    <form:form action="${addAction}" commandName="station">
        <table>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Name: "/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" maxlength="45" required="required" placeholder="Station name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="x">
                        <spring:message text="X coord.: "/>
                    </form:label>
                </td>
                <td>
                        <form:input path="x" pattern="^-?[0-9]{1,3}" required="required" placeholder="-999 to 999 in km"/>
            </tr>
            <tr>
                <td>
                    <form:label path="y">
                        <spring:message text="Y coord.: "/>
                    </form:label>
                </td>
                <td>
                    <form:input path="y" pattern="^-?[0-9]{1,3}" required="required" placeholder="-999 to 999 in km"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="<spring:message text="Add station"/>"/>
    </form:form>
    </div>
</div>
</body>
</html>