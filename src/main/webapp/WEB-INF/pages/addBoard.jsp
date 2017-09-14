<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>

    <style>
        .sbbnewboard {
            color: #545454 !important;
        }
    </style>

    <jsp:include page="temps/navbar.jsp"/>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/blue.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/login-style.css"/>" rel="stylesheet">
    <link href="<c:url value="http://fonts.googleapis.com/css?family=Pacifico"/>" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
            width: 100px;
            padding: 10px;
        }

        table.sortable td {
            text-align: center;
            color: #545454;
            height: 50px;
            width: 10%;
        }

        form select {
            width:180px;
        }

        form input {
            width: 180px;
        }
    </style>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar">

<section class="content">
    <div class="container" align="center">
        <h1 align="center" style="color: #49a827">New Board</h1>

        <c:url var="addBoard" value="/admin/boardsadd"/>
        <form:form action="${addBoard}" commandName="boardDto">

            <table class="sorttable_nosort" style="color: #545454">
                <tr>
                    <th>
                        <form:label path="name">
                            <spring:message text="Name: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:input path="name" maxlength="10" required="required"
                                    placeholder="Board name" pattern="[a-zA-Zа-яА-ЯёЁ0-9 -]+"/>
                    </td>
                </tr>
                <tr>
                    <th>
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
                    <th>
                        <form:label path="from_id">
                            <spring:message text="From: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:select id="one" path="from_id" required="required">
                            <c:forEach items="${stations}" var="station">
                                <form:option value="${station.station_id}" label="${station.name}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th>
                        <form:label path="to_id">
                            <spring:message text="To: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:select id="two" path="to_id" required="required">
                            <c:forEach items="${stations}" var="station">
                                <form:option value="${station.station_id}" label="${station.name}"/>
                            </c:forEach>
                        </form:select>
                    </td>

                </tr>
                <tr>
                    <th>
                        <form:label path="departure">
                            <spring:message text="Depart: "/>
                        </form:label>
                    </th>
                    <td>
                        <form:input id="time1" data-format="HH:mm" data-template="HH:mm" path="departure"
                                    pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}"
                                    required="required" placeholder="HH:MM"/>
                    </td>
                </tr>
                <tr>
                    <th></th>
                    <td>
                        <button class="btn btn-success" style="width: 180px">Add Board</button>
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
<script src="<c:url value="/resources/js/sorttable.js"/>"></script>

<script type="text/javascript">
    $(function () {
        $("#one").change(function (e) {
            $("#two").empty();

            var options =
                $("#one option").filter(function(e){
                    return $(this).attr("value") !== $("#one option:selected").val();
                }).clone();

            $("#two").append(options);
        });
    });
</script>

</body>
</html>