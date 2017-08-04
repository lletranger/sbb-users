<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="true" %>

<html>
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
    <title>Users Page</title>
    <link href="<c:url value="/resources/css/admin-style.css"/>" rel="stylesheet">
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

    <div align="center" class="container">

        <h1 align="center" style="color: #ffffff">My Tickets List</h1>
        <c:if test="${!empty ticketsDto}">
            <table class="sortable">
                <tr>
                    <th width="50">ID</th>
                    <th width="100">Board</th>
                    <th width="100">From</th>
                    <th width="100">To</th>
                    <th width="100">Departure</th>
                    <th width="100">Name</th>
                    <th width="100">Surname</th>
                    <th width="150">Birth Date</th>
                </tr>
                <c:forEach items="${ticketsDto}" var="dto">
                    <tr>
                        <td>${dto.id}</td>
                        <td>${dto.boardName}</td>
                        <td>${dto.from}</td>
                        <td>${dto.to}</td>
                        <td>${dto.departure}</td>
                        <td>${dto.passName}</td>
                        <td>${dto.passSurname}</td>
                        <td>${dto.passBirthDate}</td>
                        <td>
                            <button onclick="location.href = '/annulticket/${dto.id}'">Cancel ticket</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>