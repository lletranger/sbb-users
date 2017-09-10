<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<jsp:include page="temps/navbar.jsp"/>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
<link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
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
                    <th width="50">Delayable</th>
                </tr>
                <c:forEach items="${dtos}" var="dto">
                    <tr>
                        <td><a href="${pageContext.request.contextPath}/admin/boarddata/${dto.id}">${dto.id}</a></td>
                        <td><a href="${pageContext.request.contextPath}/admin/boarddata/${dto.id}">${dto.name}</a></td>
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
                                        onclick="location.href = '${pageContext.request.contextPath}/admin/delay/add/${dto.id}'">
                                    Add Delay
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
        <script src="<c:url value="/resources/js/sorttable.js"/>"></script>
        <%--<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.12/datatables.min.js"></script>--%>


        <%--<c:if test="${!empty dtos}">
            <table id="example" class="display sortable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th width="50">ID</th>
                    <th width="50">Board Name</th>
                    <th width="50">From</th>
                    <th width="50">To</th>
                    <th width="50">Departure</th>
                    <th width="50">Journey Time</th>
                    <th width="50">Expected Arrival</th>
                    <th width="50">Delay</th>
                    <th width="50">Arrival</th>
                        &lt;%&ndash;<th width="50">Delayable</th>&ndash;%&gt;
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dtos}" var="dto">
                    <tr>
                            &lt;%&ndash;<td><a href="${pageContext.request.contextPath}/admin/boarddata/${dto.id}">${dto.id}</a></td>&ndash;%&gt;
                            &lt;%&ndash;<td><a href="${pageContext.request.contextPath}/admin/boarddata/${dto.id}">${dto.name}</a></td>&ndash;%&gt;
                        <td>${dto.id}</td>
                        <td>${dto.name}</td>
                        <td>${dto.from}</td>
                        <td>${dto.to}</td>
                        <td>${dto.departure}</td>
                        <td>${dto.journeyTime}</td>
                        <td>${dto.expectedArrival}</td>
                        <td>${dto.delay}</td>
                        <td>${dto.arrival}</td>
                            &lt;%&ndash;<c:if test="${dto.canAddDelay ne 'false'}">&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<button class="btn btn-success"&ndash;%&gt;
                            &lt;%&ndash;onclick="location.href = '${pageContext.request.contextPath}/admin/delay/add/${dto.id}'">&ndash;%&gt;
                            &lt;%&ndash;Add Delay&ndash;%&gt;
                            &lt;%&ndash;</button>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;
                            &lt;%&ndash;</c:if>&ndash;%&gt;
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

    </div>
</section>

<script>
    $(function () {
        $("#example").dataTable({
            "lengthMenu": [[10, 25, -1], [10, 25, "All"]],
            columnDefs: [
                {
                    targets:0, 1, 10,
                    render: function ( data, type, row, meta ) {
                        if(type === 'display'){
                            data = '<a href="basic.php?game=' + encodeURIComponent(data) + '">' + data + '</a>';
                        }

                        return data;
                    }
                }
            ]
        })
</script>--%>

</body>
</html>