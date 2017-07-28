<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta firstName="viewport" content="width=device-width, initial-scale=1.0">
    <meta firstName="description" content="Middle-earth Railroads">
    <title> | MeR</title>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
<!-- Preloader -->

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand" href="../../index.jsp">
                    </a>
                    <div><span id="txt"></span></div>
                    <h2></h2>
                </div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->

            <div class="collapse navbar-collapse navbar-main-collapse">

                <div class="col-md-2 right col-md-offset-2">

                </div>
                <div class="col-md-4 right ">
                    <ul class="list nav navbar-nav ">
                        <li><a href="/../../index.jsp">Home</a></li>
                        <li><a href='/users'>Users</a></li>
                        <li><a href="/stations">Stations</a></li>
                        <li><a href="/boards">Boards</a></li>
                    </ul>
                </div>
            </div>

            <!-- /.navbar-collapse -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</nav>

<a href="../../index.jsp">Back</a>

<h1>Station List</h1>

<c:if test="${!empty allStations}">
    <table class="tg">
        <tr>
            <th width="40">ID#</th>
            <th width="80">Name</th>
            <th width="65">X crd, km.</th>
            <th width="65">Y crd, km.</th>
        </tr>
        <c:forEach items="${allStations}" var="station">
            <tr>
                <td>${station.station_id}</td>
                <td>${station.name}</td>
                <td>${station.x}</td>
                <td>${station.y}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<div>
<h1>Add a Station</h1>

<c:url var="addAction" value="/stations/add"/>

<form:form action="${addAction}" commandName="station">
    <table>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="x">
                    <spring:message text="X coord."/>
                </form:label>
            </td>
            <td>
                <form:input path="x"/>
        </tr>
        <tr>
            <td>
                <form:label path="y">
                    <spring:message text="Y coord."/>
                </form:label>
            </td>
            <td>
                <form:input path="y"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="<spring:message text="Add Station"/>"/>
</form:form>
</div>
</body>
</html>
