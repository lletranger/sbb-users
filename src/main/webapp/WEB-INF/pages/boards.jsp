<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
    <title>Boards page</title>

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
            padding: 10px;

        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 50px;
            width: 10%;
        }

    </style>
</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/index'">Main</button>
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
        <h1 align="center" style="color: #49a827">Boards List</h1>

        <c:if test="${!empty dtos}">
            <table class="sortable">
                <tr>
                    <th width="50">ID</th>
                    <th width="50" class="sorttable_alpha">Board Name</th>
                    <th width="50">From</th>
                    <th width="50">To</th>
                    <th width="50">Departure</th>
                    <th width="50" class="sorttable_numeric">Journey Time</th>
                    <th width="50">Expected Arrival</th>
                    <th width="50" class="sorttable_numeric">Delay</th>
                    <th width="50">Arrival</th>
                    <th class="sorttable_nosort"></th>
                </tr>
                <c:forEach items="${dtos}" var="dto">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/boarddata/${dto.id}">${dto.id}</a></td>
                        <td><a href="${pageContext.request.contextPath}/boarddata/${dto.id}">${dto.name}</a></td>
                        <%--<td>${dto.name}</a></td>--%>
                        <td>${dto.from}</td>
                        <td>${dto.to}</td>
                        <td>${dto.departure}</td>
                        <td>${dto.journeyTime}</td>
                        <td>${dto.expectedArrival}</td>
                        <td>${dto.delay}</td>
                        <td>${dto.arrival}</td>
                        <c:if test="${dto.canAddDelay ne 'false'}">
                            <td>
                                <button class="btn btn-success"
                                        onclick="location.href = '${pageContext.request.contextPath}/delay/add/${dto.id}'">Add Delay
                                </button>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</section>

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">Add Board</h1>

        <c:url var="addBoard" value="/boards/add"/>
        <form:form action="${addBoard}" commandName="boardDto">

            <table class="sorttable_nosort" style="color: #545454">
                <tr>
                    <th style='width: 100px;'>
                        <form:label path="name">
                            <spring:message text="Name: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:input path="name" maxlength="10" required="required" placeholder="Board name"
                                    style="width: 150px"/>
                    </td>
                </tr>
                <tr>
                    <th style='width: 100px;'>
                        <form:label path="train_id">
                            <spring:message text="Train: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:select path="train_id">
                            <c:forEach items="${trains}" var="train">
                                <form:option value="${train.train_id}"
                                             label="${train.seats} seats, ${train.speed_percents*45/100} km/h"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th style='width: 100px;'>
                        <form:label path="from_id">
                            <spring:message text="From: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:select path="from_id">
                            <c:forEach items="${stations}" var="station">
                                <form:option value="${station.station_id}" label="${station.name}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th style='width: 100px;'>
                        <form:label path="to_id">
                            <spring:message text="To: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:select path="to_id">
                            <c:forEach items="${stations}" var="station">
                                <form:option value="${station.station_id}" label="${station.name}"/>
                            </c:forEach>
                        </form:select>
                    </td>

                </tr>
                <tr>
                    <th style='width: 100px;'>
                        <form:label path="departure">
                            <spring:message text="Depart: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:input path="departure"
                                    pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}"
                                    required="required" placeholder="HH:MM" style="width: 150px"/>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success" style="width: 150px">Add Board</button>
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

</body>
</html>