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
            background-color:#eee;
            color:#666666;
            font-weight: bold;
            cursor: default;
        }
    </style>
    <title>Boards</title>
</head>
<body>
<a href="/index.jsp">Back to Main</a>

<br/>
<br/>

<h1>Full Schedule</h1>

<c:if test="${!empty boards}">
    <table class="sortable">

        <tr>
            <th width="40">ID</th>
            <th width="100">Board Name</th>
            <th width="100">From</th>
            <th width="100">To</th>
            <th width="100">Departure</th>
            <th width="100">Journey Time</th>
            <th width="100">Estimated Arrival</th>
            <th width="100">Delay</th>
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
            </tr>
        </c:forEach>
    </table>
</c:if>


<br/>
<br/>

<h1>Add Board</h1>

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
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="train_id">
                    <spring:message text="Train model"/>
                </form:label>
            </td>
            <td>
                <form:select path="train_id">
                    <form:option value="1" label="10 seats" />
                    <form:option value="2" label="12 seats" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="from_id">
                    <spring:message text="From station"/>
                </form:label>
            </td>
            <td>
                <form:select path="from_id">
                    <form:option value="1" label="Gondor" />
                    <form:option value="2" label="Isengard" />
                    <form:option value="3" label="Mordor" />
                    <form:option value="4" label="Rivendell" />
                    <form:option value="5" label="Rohan" />
                    <form:option value="6" label="Shire" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="to_id">
                    <spring:message text="To station"/>
                </form:label>
            </td>
            <td>
                <form:select path="to_id">
                    <form:option value="1" label="Gondor" />
                    <form:option value="2" label="Isengard" />
                    <form:option value="3" label="Mordor" />
                    <form:option value="4" label="Rivendell" />
                    <form:option value="5" label="Rohan" />
                    <form:option value="6" label="Shire" />
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
                <form:input path="departure"/>
            </td>
        </tr>
        <tr><td>
            <input type="submit" value="<spring:message text="Add Board"/>"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
