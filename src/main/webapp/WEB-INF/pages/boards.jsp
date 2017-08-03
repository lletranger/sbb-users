<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <script src="/resources/js/sorttable.js"></script>
    <style>
        table.sortable thead {
            background-color: #eee;
            color: #666666;
            font-weight: bold;
            cursor: default;
        }
    </style>
    <title>Boards Page</title>
</head>
<body>
<a href="index">Back to Main</a>

<br/>
<br/>

<h1>Full Schedule</h1>

<c:if test="${!empty boards}">
    <table id="boards1" class="sortable">

        <tr>
            <th width="40">ID</th>
            <th width="100">Board Name</th>
            <th width="100">From</th>
            <th width="100">To</th>
            <th width="100">Departure</th>
            <th class="sorttable_numeric" width="100">Journey Time</th>
            <th width="100">Expected Arrival</th>
            <th class="sorttable_numeric" width="100">Delay</th>
            <th width="100">Arrival</th>
        </tr>

        <c:forEach items="${boards}" var="board">
            <tr>
                <td>
                    <a href="/boarddata/${board.board_id}">${board.board_id}</a>
                </td>

                <td>
                    <a href="/boarddata/${board.board_id}">${board.name}</a>
                </td>

                <td>
                    <c:forEach items="${stations}" var="station">
                        <c:if test="${station.station_id eq board.from_id}">
                            ${station.name}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${stations}" var="station">
                        <c:if test="${station.station_id eq board.to_id}">
                            ${station.name}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${departures}" var="dep">
                        <c:if test="${board.board_id eq dep.key}">
                            ${dep.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${journeyTime}" var="jt">
                        <c:if test="${board.board_id eq jt.key}">
                            ${jt.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${arrivals}" var="ea">
                        <c:if test="${board.board_id eq ea.key}">
                            ${ea.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${delays}" var="delay">
                        <c:if test="${board.board_id eq delay.key}">
                            ${delay.value}
                        </c:if>
                    </c:forEach>
                </td>

                <td>
                    <c:forEach items="${totalTime}" var="arrival">
                        <c:if test="${board.board_id eq arrival.key}">
                            ${arrival.value}
                        </c:if>
                    </c:forEach>
                </td>
                <td class="button-container">
                    <button onclick="location.href = '/delay/add${board.board_id}'">Add a Delay</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<script>
    $(document).ready(function() {
        $('boards1').DataTable( {
            "order": [[ 5, "asc" ]]
        } );
    } );
</script>
<br/>
<br/>

<h1>Add a Board</h1>

<c:url var="addBoard" value="/boards/add"/>

<form:form action="${addBoard}" commandName="board">

    <table>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" maxlength="10" required="required" placeholder="Board name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="train_id">
                    <spring:message text="Train Model"/>
                </form:label>
            </td>
            <td>
                <form:select path="train_id">
                    <c:forEach items="${trains}" var="train">
                        <form:option value="${train.train_id}" label="${train.seats} seats ${train.speed_percents}% speed"/>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="from_id">
                    <spring:message text="From Station"/>
                </form:label>
            </td>
            <td>
                <form:select path="from_id">
                    <c:forEach items="${stations}" var="station">
                        <form:option value="${station.station_id}" label="${station.name}"/>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="to_id">
                    <spring:message text="To Station"/>
                </form:label>
            </td>
            <td>
                <form:select path="to_id">
                    <c:forEach items="${stations}" var="station">
                        <form:option value="${station.station_id}" label="${station.name}"/>
                    </c:forEach>
                </form:select>
            </td>

        </tr>
        <tr>
            <td>
                <form:label path="departure">
                    <spring:message text="Departing Time"/>
                </form:label>
            </td>
            <td>
                <form:input path="departure" pattern="([0-1]{1}[0-9]{1}|20|21|22|23):[0-5]{1}[0-9]{1}" required="required" placeholder="HH:MM"/>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="<spring:message text="Add"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>