<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<html lang="en">
<head>
    <title>Error</title>
    <link href="<c:url value="/resources/css/social-buttons-3.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>
<br>
<p style="font-size: 45px" align="center">User with login '${existingUser}' already exists!</p>
<br>

<div class="container" align="center">

    <img src="${pageContext.request.contextPath}/resources/img/logintaken.jpg">

    <div class="row">
        <br>
        <p style="font-size: 35px" align="center">Please, choose another one</p>
        <div class="form-group col-lg-4 col-lg-offset-4" align="center">
            <p align="center"><a href="${pageContext.request.contextPath}/register">Registration</a></p>
            <p align="center"><a href="${pageContext.request.contextPath}/index">Back to Main</a></p>
        </div>
    </div>

</div>
</body>
</html>