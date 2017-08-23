<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
    <title>New delay</title>
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
        }

    </style>
</head>


<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/boards'">Boards</button>
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
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My tickets</a><br>
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>
                                    <c:if test="${sessionUser.role eq 'admin'}">
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/stations">Stations</a><br>
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/boards">Boards</a><br>
                                        <a  style="color:#49A827" href="${pageContext.request.contextPath}/users">Users</a><br>
                                    </c:if>
                                    <a  style="color:#49A827" href="${pageContext.request.contextPath}/logout">Log out</a><br>
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
        <h1 align="center" style="color: #49a827">Add a delay for board <a href="/boarddata/${board.board_id}">'${board.name}'</a></h1>
        <h4 align="center" style="color: #545454">departing from ${fromName} to ${toName} at ${board.departure}</h4>


        <c:url var="addDelay" value="/delay/add/${board.board_id}"/>
        <form:form action="${addDelay}" modelAttribute="delay">
        <table class="sorttable_nosort" style="color: #545454">

            <tr>
                <th>
                    <form:label path="delay">
                        <spring:message text="Delay time: "/>
                    </form:label>
                </th>
                <td>
                    <form:input style="color:#000000" path="delay"
                                pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}"
                                required="required" placeholder="HH:MM"/></td>
            </tr>
            <tr>
                <th></th>
                <td>
                    <input class="btn btn-success" style="width: 185px"
                           type="submit" value="<spring:message text="Add delay"/>"/>
                </td>
            </tr>
            </form:form>
        </table>
    </div>
</section>



<script src="<c:url value="/resources/js/jquery-2.1.3.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.scrollTo.js"/>"></script>
<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>
<script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</body>
</html>