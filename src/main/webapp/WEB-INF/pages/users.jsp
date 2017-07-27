<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>

    <script src="/resources/js/sorttable.js"></script>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/dataTables.bootstrap.min.js" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/jquery.dataTables.min.js" />" rel="stylesheet">

    <style>
    table.sortable thead {
    background-color:#eee;
    color:#666666;
    font-weight: bold;
    cursor: default;
    }
    </style>

    <title>Users Page</title>
</head>
<body>
<a href="../../index.jsp">Back</a>

<br/>
<br/>

<h1>Users List</h1>



<c:if test="${!empty allUsers}">
    <table id="table1" class="table" cellspacing="0" width="100%">
        <tr>
            <th width="40">ID</th>
            <th width="100">Login</th>
            <th width="80">Status</th>
        </tr>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.user_id}</td>
                <td><a href="/userdata/${user.user_id}" target="_blank">${user.login}</a></td>
                <td>${user.role}</td>
                <td class="button-container"><button onclick="location.href = '/edit/${user.user_id}'">Edit</button></td>
                <td class="button-container"><button onclick="location.href = '/remove/${user.user_id}'">Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Add a User</h1>

<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" commandName="user">
    <table class="table-bordered" cellspacing="0" width="100%">
        <c:if test="${!empty user.login}">
            <tr>
                <td>
                    <form:label path="user_id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="user_id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="user_id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="login">
                    <spring:message text="Login"/>
                </form:label>
            </td>
            <td>
                <form:input path="login"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <c:if test="${!empty user.login}">
                <form:password path="password" />
                </c:if>
                <c:if test="${empty user.login}">
                    <form:input path="password"/>
                </c:if>

            </td>
        </tr>
        <tr>
            <td>
                <form:label path="role">
                    <spring:message text="Role"/>
                </form:label>
            </td>
            <td>
                <form:select path="role">
                    <form:option value="user" label="User" />
                    <form:option value="admin" label="Admin" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.login}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.login}">
                    <input type="submit"
                           value="<spring:message text="Add User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
