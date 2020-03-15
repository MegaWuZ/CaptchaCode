<%--
  Created by IntelliJ IDEA.
  User: wuzheyi
  Date: 2020/3/14
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <img src="code.jsp" id = "code">
    <a href="javascript:void(0);" onclick="changeCode()">看不清？点我</a>
    <script>
      function changeCode() {
         document.getElementById("code").src = "code.jsp?d=" + new Date().getTime();
      }
    </script>
  </body>
</html>
