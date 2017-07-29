<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  <title>User Details</title>
</head>
<body>
<a href="../users">Back to Users</a>

<br/>
<br/>

<h1>User Details ${user.login}</h1>

<table class="sortable">
  <tr>
    <th width="40">ID</th>
    <th width="120">Login</th>
    <th width="120">Password</th>
    <th width="120">Status</th>
  </tr>
  <tr>
    <td>${user.user_id}</td>
    <td>${user.login}</td>
    <td>${user.password}</td>
    <td>${user.role}</td>
  </tr>
</table>
</body>
</html>