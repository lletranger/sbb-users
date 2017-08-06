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
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <title>Stations page</title>
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

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand" href="index.jsp">
                    </a>
                    <div><span id="txt"></span></div>
                    <h2></h2>
                </div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->

            <div class="collapse navbar-collapse navbar-main-collapse">
                <div class="col-md-2 right col-md-offset-2">
                    <c:choose>
                        <c:when test="${sessionUser.role ne 'anon'}">
                            <p class="mail">${sessionUser.login}<i class="setting fa fa-cog"></i></p>

                            <div id="menu">
                                <div id="arrow"></div>
                                <div id="logout">
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/logout">Log
                                        out</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My
                                        tickets</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/boards">Boards</a><br>
                                    <a style="color:#49A827"
                                       href="${pageContext.request.contextPath}/users">Users</a><br>
                                    <a style="color:#49A827"
                                       href="${pageContext.request.contextPath}/stations">Stations</a><br>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/login">
                                <button class="btn btn-success log-btn">Log in</button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>

                <div class="col-md-4 center-block">
                    <ul class="list nav navbar-nav" style="align-items: center">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">Buy Ticket</h1>
        <h4 align="center" style="color: #545454">To trip from ${fromTicket} to ${toTicket} at ${board.departure}</h4>

        <c:url var="addTicket" value="/ticket/add/${board.board_id}"/>
        <form:form action="${addTicket}" commandName="passengerDto" method="post">
            <table>
                <tr>
                    <td>
                        <form:label path="name">
                            <spring:message text="First name: "/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="name" maxlength="45" required="required" placeholder="Name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="surname">
                            <spring:message text="Second name: "/>
                        </form:label>
                    </td>
                    <td>
                            <form:input path="surname" maxlength="45" required="required" placeholder="Surname"/>
                </tr>
                <tr>
                    <td>
                        <form:label path="birth_date">
                            <spring:message text="Birth date: "/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="birth_date" id="datepicker" required="required" placeholder="Birth date"
                                    readonly="true"/>
                    </td>
                </tr>
                <tr></tr>
                <input type="submit" value="<spring:message text="Buy ticket"/>"/></tr>
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
<script src="/resources/js/sorttable.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        $("#datepicker").datepicker({
            dateFormat: 'dd/mm/yy',
            changeMonth: true,
            changeYear: true,
            yearRange: '-100y:c+nn',
            maxDate: '-1d'
        });
    });
</script>
</body>
</html>