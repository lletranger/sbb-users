<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>

    <jsp:include page="temps/navbar.jsp"/>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <title>Users page</title>

    <style>
        body {
            background: linear-gradient(90deg, rgb(255, 255, 255) 10%, #ffffff 90%);
            color: #49a827;
            font-family: 'Helvetica', sans-serif;
        }

        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
            font-weight: bold;
            cursor: default;

        }

        table.sortable th {
            text-align: center;
            height: 50px;

        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 40px;
            width: 100px;
        }

    </style>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" align="center">
        <h1 align="center">Users List</h1>
        <c:if test="${!empty allUsers}">
            <table class="sortable">
                <tr>
                    <th width="100">Login</th>
                    <th width="100">Status</th>
                    <th class="sorttable_nosort"></th>
                    <th class="sorttable_nosort"></th>
                </tr>
                <c:forEach items="${allUsers}" var="user">
                    <tr>
                        <td><a href="/admin/userdata/${user.user_id}">${user.username}</a></td>
                        <td>${user.role}</td>
                        <c:if test="${(user.user_id ne sessionUser.user_id) && (user.user_id ne 115)}">
                            <td>
                                <c:if test="${user.role ne 'admin'}">
                                    <button class="btn btn-success" style="width: 120px"
                                            onclick="location.href = '${pageContext.request.contextPath}/admin/setadmin/${user.user_id}'">
                                        Make admin
                                    </button>
                                </c:if>
                                <c:if test="${user.role ne 'user'}">
                                    <button class="btn btn-success" style="width: 120px"
                                            onclick="location.href = '${pageContext.request.contextPath}/admin/setuser/${user.user_id}'">
                                        Set user
                                    </button>
                                </c:if>
                            </td>
                            <td>
                                <button class="btn btn-danger" style="width: 120px"
                                        onclick="location.href = '${pageContext.request.contextPath}/admin/remove/${user.user_id}'">Delete user
                                </button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</section>

<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>
<script src="<c:url value="/resources/js/sorttable.js"/>"></script>

</body>
</html>