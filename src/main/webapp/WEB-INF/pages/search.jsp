<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<form:form action="${addAction}" modelAttribute="user">
    <table class="table-bordered" cellspacing="0" width="100%">

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
