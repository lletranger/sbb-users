<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

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
    <title>User Details</title>
    <link href="<c:url value="/resources/css/admin-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>
<br>
<div class="container" align="center">
    <div class="row">
        <div class="form-group col-lg-4 col-lg-offset-4" align="center">
            <p align="center"><a href="${pageContext.request.contextPath}/index">Back to Main</a></p>
            <p align="center"><a href="${pageContext.request.contextPath}/users">Users</a></p>
        </div>
    </div>
</div>

<div class="container" style="width:100%;">

    <div align="center" class="container">

        <h1 align="center" style="color: #ffffff">'${user.login}' Details</h1>
        <table class="sortable">
            <tr>
                <th width="40">ID</th>
                <th width="120">Login</th>
                <th width="120">Password</th>
                <th width="120">Status</th>
            </tr>
            <tr>
                <td>${user.user_id}</td>
                <td>${user.login}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>