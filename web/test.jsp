<%--
  Created by IntelliJ IDEA.
  User: wuzheyi
  Date: 2020/3/15
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码比较</title>
</head>
<body>
    <form action="submit.action">
        <p> <input type="text" name="kaptcha" id="code" value="" maxlength="4" placeholder="请输入验证码"/>
        <img src="code.jsp" id="changeCode"/>
        </p>
        <p>
            <input type="button" id="login" value="登录">
        </p>
        <div id="result"></div>
    </form>
    <script src="js/jquery-1.12.4.min.js"></script>
    <script>
        $(function () {
           $("changeCode").on("click",function () {
              $(this).attr("src","code.jsp?d="+new Date().getTime());
           });
            //给登录按钮绑定点击事件
            $("#login").on("click",function () {
                var code = $("#code").val();
                //alert(code);
                var params = {"code":code};
                $.post("http://localhost:8080/CaptcahCode/login",params,function (data) {
                    if(data == "success"){
                        $("#result").html("验证码输入正确");
                    }else {
                        $("#result").html("验证码输入有误，请重新输入");
                        $("#code").val("").focus();
                    }

                });
            })
        });
    </script>
</body>
</html>
