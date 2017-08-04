<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <script src="/resources/js/sorttable.js"></script>

    <style>
        table.sortable thead {
            background-color: rgba(255, 227, 1, 0);
            color: #545454;
            font-size: 20px;
            font-weight: bold;
            cursor: default;
        }

        table.sortable td{
            text-align: center;
            color: #ffffff;
        }

    </style>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#datepicker" ).datepicker({
                dateFormat : 'mm/dd/yy',
                changeMonth : true,
                changeYear : true,
                yearRange: '-100y:c+nn',
                maxDate: '-1d'
            });
        });
    </script>

    <title>Ticket Page</title>
    <link href="<c:url value="/resources/css/admin-style.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
</head>

<body>
<br>

<div class="form-group col-lg-4 col-lg-offset-4" align="center">
    <p align="center"><a href="${pageContext.request.contextPath}/index">Back to Main</a></p>
    <p align="center"><a href="${pageContext.request.contextPath}/boards">Boards</a></p>
</div>

<div align="center" class="container">
    <h1 align="center">Buy Ticket</h1>
    <c:url var="addTicket" value="/ticket/add${board.board_id}"/>
    <form:form action="${addTicket}" commandName="passenger" method="post">
        <table>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="First name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name" maxlength="45" required="required" placeholder="Name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="surname">
                        <spring:message text="Second name"/>
                    </form:label>
                </td>
                <td>
                        <form:input path="surname" maxlength="45" required="required" placeholder="Surname"/>
            </tr>
            <tr>
                <td>
                    <form:label path="birth_date">
                        <spring:message text="Birth date"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="birth_date" id="datepicker" required="required" placeholder="Birth date" readonly="true"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="<spring:message text="Buy ticket"/>"/>
    </form:form>
</div>
</body>
</html>