<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
    <link href="<c:url value="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"/>" rel="stylesheet" type="text/css">
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

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">Add a delay for board <a href="/admin/boarddata/${board.board_id}">'${board.name}'</a></h1>
        <h4 align="center" style="color: #545454">departing from ${fromName} to ${toName} at ${board.departure}</h4>


        <c:url var="addDelay" value="/admin/delay/add/${board.board_id}"/>
        <form:form action="${addDelay}" modelAttribute="delay">
        <table class="sorttable_nosort" style="color: #545454">

            <tr>
                <th>
                    <form:label path="delay">
                        <spring:message text="Delay time: "/>
                    </form:label>
                </th>
                <td>
                    <form:input type="datetime" style="color:#000000" path="delay"
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
<script src="<c:url value="/resources/js/sorttable.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="https://code.jquery.com/ui/1.12.1/jquery-ui.js"/>"></script>

<%--<script src="<c:url value="/resources/js/custom.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/moment.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/combodate.js"/>"></script>--%>
<%--<script>--%>
    <%--$('input').combodate({--%>
        <%--firstItem: 'name',--%>
        <%--minuteStep: 5--%>
    <%--})--%>
<%--</script>--%>

</body>
</html>