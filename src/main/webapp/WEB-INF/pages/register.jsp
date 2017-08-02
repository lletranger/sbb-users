<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login page</title>
    <link href="<c:url value="/resources/css/social-buttons-3.css"/>"  rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css"/>"  rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>

<!-- Login form -->
<section class="content">
    <div class="container" align="center">
        <h2 align="center">New User Registration</h2><br>

        <c:url var="register" value="/register"/>
        <form:form action="${register}" modelAttribute="newUser" >

            <div class="row">
                <div id="form-group-email" class="form-group col-lg-4 col-lg-offset-4">
                    <tr></tr>
                    <form:label path="Login"/>
                    <form:input style="color:#49A827" path="login" minlength="4" maxlength="45" required="required" placeholder="Login"/>
                </div>
            </div>

            <div class="row">
                <div id="password" class="form-group col-lg-4 col-lg-offset-4">
                    <form:password style="color:#49A827" path="password" minlength="4" maxlength="45" required="required" placeholder="Password"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button type="submit" class="btn btn-success">Register</button>
                </div>
            </div>
        </form:form>

        <div class="row">
            <div class="form-group col-lg-4 col-lg-offset-4">
                <a href="${pageContext.request.contextPath}/register">Register</a>
                <a>  </a>
                <a href="${pageContext.request.contextPath}/index">Back to Main</a>

            </div>
        </div>

    </div>
</section>
</body>
</html>