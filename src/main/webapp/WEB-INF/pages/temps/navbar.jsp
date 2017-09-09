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

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="row" align="center">
            <div class="col-md-4">
                <div class="navbar-header page-scroll" style="margin-top: 8px">
                    <button class="btn btn-success"
                            onclick="location.href='${pageContext.request.contextPath}/index'">
                        Main</button>
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