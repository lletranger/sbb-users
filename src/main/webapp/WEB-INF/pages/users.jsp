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

    <h1 align="center" style="color: #ffffff">Users List</h1>
        <c:if test="${!empty allUsers}">
            <table class="sortable">
                <tr>
                    <th width="40">ID</th>
                    <th width="100">Login</th>
                    <th width="100">Status</th>
                </tr>
                <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td><a href="/userdata/${user.user_id}">${user.user_id}</td>

                        <td><a href="/userdata/${user.user_id}">${user.login}</a></td>

                        <td>${user.role}</td>

                        <td class="button-container">
                            <c:if test="${user.role ne 'admin'}">
                                <button onclick="location.href = '/setadmin/${user.user_id}'">Make Admin</button>
                            </c:if>
                            <c:if test="${user.role ne 'user'}">
                                <button onclick="location.href = '/setuser/${user.user_id}' ">Set User</button>
                            </c:if>
                        </td>

                        <td class="button-container">
                            <button onclick="location.href = '/remove/${user.user_id}'">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>