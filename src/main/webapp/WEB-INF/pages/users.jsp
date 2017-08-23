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
    <meta firstName="viewport" content="width=device-width, initial-scale=1.0">
    <meta firstName="description" content="Middle-earth Railroads">
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
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

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-main-collapse">
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
                        <td><a href="/userdata/${user.user_id}">${user.login}</a></td>
                        <td>${user.role}</td>
                        <c:if test="${user.user_id ne sessionUser.user_id}">
                            <td>
                                <c:if test="${user.role ne 'admin'}">
                                    <button class="btn btn-success" style="width: 120px"
                                            onclick="location.href = '${pageContext.request.contextPath}/setadmin/${user.user_id}'">
                                        Make admin
                                    </button>
                                </c:if>
                                <c:if test="${user.role ne 'user'}">
                                    <button class="btn btn-info" style="width: 120px"
                                            onclick="location.href = '${pageContext.request.contextPath}/setuser/${user.user_id}' ">
                                        Set user
                                    </button>
                                </c:if>
                            </td>
                            <td>
                                <button class="btn btn-danger" style="width: 120px"
                                        onclick="location.href = '${pageContext.request.contextPath}/remove/${user.user_id}'">Delete user
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
<script src="/resources/js/sorttable.js"></script>
</body>
</html>