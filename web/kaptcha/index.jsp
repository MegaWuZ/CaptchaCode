<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/8/23
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关于验证码框架之---Kaptcha</title>
</head>
<body>
    <form action="submit.action">
        <input type="text" name="kaptcha" value="" />
        <img src="http://localhost:8080/CaptcahCode/kaptcha.jpg" />
    </form>
</body>
</html>
