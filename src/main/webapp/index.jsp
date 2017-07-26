<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>JavaSchool</title>
  <script>
      function startTime() {
          var today = new Date();
          var monthNames = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November','December'];
          var dayNames = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
          var h = today.getHours();
          var m = today.getMinutes();
          var s = today.getSeconds();
          var d = today.getDate();
          var mn = monthNames[today.getMonth()];
          var y = today.getFullYear();
          var w = dayNames[today.getDay()];
          m = checkTime(m);
          s = checkTime(s);
          document.getElementById('txt').innerHTML =
              h + ":" + m + ":" + s;
          document.getElementById('date').innerHTML =
             d + " of " + mn + " " + y + ", " + w;
          var t = setTimeout(startTime, 500);
      }
      function checkTime(i) {
          if (i < 10) {i = "0" + i}
          return i;
      }
  </script>
</head>
<body onload="startTime()">
 <h1 align="center">T-Systems JavaSchool Task!</h1>
 <p align="center" style="font-size: 25px">Today is <span id="date"></span></p>
 <p align="center" style="font-size: 25px">Current time <span id="txt"></span></p>
 <p class="button-container" align="center" ><button style="height:25px;width:200px;font-size: large" onclick="location.href = '/users'">Users List</button></p>
 <p class="button-container" align="center" ><button style="height:25px;width:200px;font-size: large" onclick="location.href = '/stations'">Stations List</button></p>
</body>
</html>