<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<html>
<nav class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                    <a class="navbar-brand" href="../index.jsp"></a>
                </div>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->

            <div class="collapse navbar-collapse navbar-main-collapse">
                <div class="col-md-2 right col-md-offset-2">
                    <sec:authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                        <p class="mail"><sec:authentication property="principal.username" /><i class="setting fa fa-cog"></i></p>
                        <div id="menu">
                            <div id="arrow"></div>
                            <div id="logout">
                                <a style="color:#49A827" href="${pageContext.request.contextPath}/info">My info</a><br>
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

                <div class="col-md-4 center-block">
                    <ul class="list nav navbar-nav" style="align-items: center">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>