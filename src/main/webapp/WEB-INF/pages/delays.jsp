<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

        table.sortable td {
            text-align: center;
            color: #ffffff;
        }

    </style>

    <title>Delay Page</title>
    <link href="<c:url value="/resources/css/success-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>
<br>

<div class="form-group col-lg-4 col-lg-offset-4" align="center">
    <p align="center"><a href="${pageContext.request.contextPath}/index">Back to Main</a></p>
    <p align="center"><a href="${pageContext.request.contextPath}/boards">Boards</a></p>
</div>

<div class="container" style="width:100%;">

    <div align="center" class="container">
        <h1 align="center" style="color: #ffffff">Add a delay for board <a
                href="/boarddata/${board.board_id}">'${board.name}'</a>,<br>
            departing from ${fromName} to ${toName} at ${board.departure}</h1>


        <c:url var="addDelay" value="/delay/add/${board.board_id}"/>
        <form:form action="${addDelay}" modelAttribute="delay">

        <div class="row">
            <div id="form-group-password" class="form-group col-lg-4 col-lg-offset-4">
                <spring:message text="Delay time: "/>
                <form:input style="color:#000000" path="delay" pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}"
                            required="required" placeholder="HH:MM"/>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-lg-4 col-lg-offset-4">
                <button type="submit" class="btn btn-success">Add delay</button>
            </div>
            </form:form>

        </div>
    </div>
</div>
</body>
</html>