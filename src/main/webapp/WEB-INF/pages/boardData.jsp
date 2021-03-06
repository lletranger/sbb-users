<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>

    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />" rel="stylesheet">
    <title>Board details</title>

    <style>
        body {
            background: linear-gradient(90deg, rgb(255, 255, 255) 10%, #ffffff 90%);
            color: #49a827;
            font-family: 'Helvetica', sans-serif;
        }

        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            cursor: default;
            text-align: left;
            width: 200px;

        }

        table.sortable th {
            text-align: left;
            height: 40px;
            width: 200px;
        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 40px;
            width: 200px;
        }

    </style>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll" style="margin-top: 8px">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <button class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/admin/boards'">Back</button>
                </div>
            </div>

            <div class="collapse navbar-collapse navbar-main-collapse">
                <div class="col-md-2 right col-md-offset-2">
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                        <p class="mail"><sec:authentication property="principal.username" /><i class="setting fa fa-cog"></i></p>
                        <div id="menu">
                            <div id="arrow"></div>
                            <div id="logout">
                                <sec:authorize access="hasRole('ROLE_USER')">
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/info">My info</a><br>
                                </sec:authorize>
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My tickets</a><br>
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/stations">Stations</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/boards">Boards</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/boardsadd">New board</a><br>
                                    <a style="color:#49A827" href="${pageContext.request.contextPath}/admin/users">Users</a><br>
                                </sec:authorize>
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/logout">Log out</a><br>
                            </div>
                        </div>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                        <a href="${pageContext.request.contextPath}/login">
                            <button class="btn btn-success">Log in</button>
                        </a>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
</nav>

<section class="content">
    <div class="container" style="width:49%; float:left" align="center">
        <h1 align="center" style="color: #49a827">Details on board</h1>

        <table class="sortable" style="color: #545454">
            <tr>
                <th class="sorttable_nosort" >Board name:</th>
                <td>${boardDetailed.name}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Departing from:</th>
                <td>${boardDetailed.from}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Destination:</th>
                <td>${boardDetailed.to}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Departure time:</th>
                <td>${boardDetailed.departure}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Journey time:</th>
                <td>${boardDetailed.journeyTime}</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Route distance:</th>
                <td>${boardDetailed.distance} km</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Average speed:</th>
                <td>${boardDetailed.averageSpeed} km/h</td>
            </tr>
            <tr>
                <th class="sorttable_nosort" >Passengers on board:</th>
                <td>${passengers.size()}</td>
            </tr>
        </table>
    </div>

<c:if test="${!empty passengers}">
    <div class="container" style="width:49%; float:right" align="center">
        <h1 align="center" style="color: #49a827">Passengers on board</h1>
        <table class="sortable" style="color: #545454">
            <tr>
                <th style="text-align: center;" width="100">Name</th>
                <th style="text-align: center;" width="100">Surname</th>
                <th style="text-align: center;" class="sorttable_nosort" width="80">Birth Date</th>
            </tr>
            <c:forEach items="${passengers}" var="passenger">
                <tr>
                    <td>${passenger.name}</td>
                    <td>${passenger.surname}</td>
                    <td>${passenger.birth_date}</td>
                </tr>
            </c:forEach>
        </table>
        </c:if>

        <c:if test="${empty passengers}">
            <h2 style="text-align: center; color: red">No passengers for that trip yet!</h2>
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
<script src="<c:url value="https://code.jquery.com/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="https://code.jquery.com/ui/1.12.1/jquery-ui.js"/>"></script>

</body>
</html>