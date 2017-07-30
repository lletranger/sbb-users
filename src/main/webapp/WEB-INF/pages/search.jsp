<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
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
<a href="../../index.jsp">Back to Main</a>

<c:url var="search" value="/search/"/>
<form:form action="${search}" commandName="board">
<tr>
    <td>
        <form:label path="from_id">
            <spring:message text="From station"/>
        </form:label>
    </td>
    <td>
        <form:label path="to_id">
            <spring:message text="To station"/>
        </form:label>
    </td>
</tr>
<tr>
    <td>
        <form:select path="from_id">
            <form:option value="1" label="Gondor"/>
            <form:option value="2" label="Isengard"/>
            <form:option value="3" label="Mordor"/>
            <form:option value="4" label="Rivendell"/>
            <form:option value="5" label="Rohan"/>
            <form:option value="6" label="Shire"/>
        </form:select>
    </td>
    <td>
        <form:select path="to_id">
            <form:option value="1" label="Gondor"/>
            <form:option value="2" label="Isengard"/>
            <form:option value="3" label="Mordor"/>
            <form:option value="4" label="Rivendell"/>
            <form:option value="5" label="Rohan"/>
            <form:option value="6" label="Shire"/>
        </form:select>
    </td>
    <br/>
    <br/>

    <h1>Find Boards by From and To Stations</h1>

</body>