<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-custom navbar-fixed-top">
    <div class="container">
        <div class="row" align="center">

            <div class="col-md-4">
                <div class="navbar-header page-scroll" style="margin-top: 8px">
                    <button class="btn btn-success" onclick="location.href=
                            '${pageContext.request.contextPath}/index'">Main</button>
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
                                    <a class="myinfo" style="color:#49A827" href="${pageContext.request.contextPath}/info">My info</a><br>
                                </sec:authorize>

                                <a class="mytickets" style="color:#49A827" href="${pageContext.request.contextPath}/mytickets">My tickets</a><br>
                                <a class="sbbsearch" style="color:#49A827" href="${pageContext.request.contextPath}/search">Search</a><br>

                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a class="sbbstations" style="color:#49A827" href="${pageContext.request.contextPath}/admin/stations">Stations</a><br>
                                    <a class="sbbboards" style="color:#49A827" href="${pageContext.request.contextPath}/admin/boards">Boards</a><br>
                                    <a class="sbbnewboard" style="color:#49A827" href="${pageContext.request.contextPath}/admin/boardsadd">New board</a><br>
                                    <a class="sbbusers" style="color:#49A827" href="${pageContext.request.contextPath}/admin/users">Users</a><br>
                                </sec:authorize>

                                <a style="color:#49A827" href="${pageContext.request.contextPath}/index#contact">Contact</a><br>
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
