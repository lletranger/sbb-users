<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Delay page</title>
    <link href="<c:url value="/resources/css/social-buttons-3.css"/>"  rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css"/>"  rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>

<section class="content">
    <div class="container" align="center">
        <h2 align="center">Add a delay for board "${board.name}"<br> departing ${board.departure} from ${from.name} to ${to.name}</h2><br>

        <c:url var="addDelay" value="/delay/add${board.board_id}"/>
        <form:form action="${addDelay}" modelAttribute="delay">

            <div class="row">
                <div id="form-group-password" class="form-group col-lg-4 col-lg-offset-4">
                    <form:input style="color:#49A827" path="delay" pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}" required="required" placeholder="Delay time HH:MM"/>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-lg-4 col-lg-offset-4">
                    <button type="submit" class="btn btn-success">Add</button>
                </div>
                <div class="form-group col-lg-4 col-lg-offset-4" align="center">
                    <p align="center"><a href="${pageContext.request.contextPath}/boards">Back to Boards</a></p>
                </div>
            </div>
        </form:form>

    </div>
</section>
</body>
</html>
