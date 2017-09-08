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
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <jsp:include page="temps/navbar.jsp"/>

    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <title>My page</title>
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
            width: 85px;
        }

    </style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" align="center">
        <h1 align="center">My Info</h1>

        <c:url var="edit" value="/info"/>
        <form:form action="${editUser}" commandName="editUser">
            <table class="sorttable_nosort" style="color: #545454">
                <tr>
                    <th style='width: 150px;'>
                        <spring:message text="Username: "/>
                    </th>
                    <td>
                        <form:input path="username" minlength="4" maxlength="25" required="required"
                                    placeholder="Username"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <spring:message text="Email: "/>
                    </th>
                    <td>
                        <form:input path="email" pattern="^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$"
                                    minlength="4" maxlength="25" placeholder="Email"/>
                    </td>
                </tr>
                <tr>
                    <th style='width: 150px;'>
                        <spring:message text="Password: "/>
                    </th>
                    <td>
                        <form:password path="password" minlength="4" maxlength="25"
                                       required="required" placeholder="Password"/>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button type=button class="btn btn-success" style="width: 250px" onclick="location.href='${pageContext.request.contextPath}/info'">Edit info</button>
                    </td>
                </tr>
            </table>
        </form:form>
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