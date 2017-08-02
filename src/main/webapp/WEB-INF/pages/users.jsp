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
            text-align: center;
            font-weight: bold;
            cursor: default;
        }
    </style>
    <title>Users Page</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/index">Back to Main</a>

<br/>
<br/>

<h1>Users List</h1>

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
                    <button onclick="location.href = '/edit/${user.user_id}'">Edit</button>
                </td>
                <td class="button-container">
                    <button onclick="location.href = '/remove/${user.user_id}'">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<br/>
<br/>

<h1>Create a User</h1>

<c:url var="addAction" value="/users/add"/>

<form:form action="${addAction}" modelAttribute="user">
    <table>
        <c:if test="${!empty user.login}">
            <tr>
                <td>
                    <form:label path="user_id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="user_id" readonly="true" disabled="true"/>
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
                <form:input path="login" minlength="4" maxlength="45" required="required" placeholder="User login"/>
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
                    <form:password path="password" readonly="true" disabled="true"/>
                </c:if>
                <c:if test="${empty user.login}">
                    <form:password path="password" minlength="4" maxlength="45у" required="required" placeholder="User password"/>
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
                    <form:option value="user" label="User"/>
                    <form:option value="admin" label="Admin"/>
                </form:select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.login}">
                    <input type="submit"
                           value="<spring:message text="Edit"/>"/>
                </c:if>
                <c:if test="${empty user.login}">
                    <input type="submit"
                           value="<spring:message text="Create"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>