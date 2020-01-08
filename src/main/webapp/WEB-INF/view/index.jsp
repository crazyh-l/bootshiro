<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Full Layout - jQuery EasyUI Demo</title>
</head>
<body>
    hello world
   <%-- <form action="doLogin" method="post">
        <input name="username" placeholder="请输入用户名"/>
        <input name="password" placeholder="请输入密码"/>
    <input type="submit" value="提交"/>
    </form>--%>

    <input id="username" name="username" placeholder="请输入用户名"/>
    <input id="password" name="password" placeholder="请输入密码"/>
    <input id="submit" type="button" value="提交"/>
<script src="/js/jquery.min.js"></script>
<script>
    $("#submit").click(function(){

        let username = $("#username").val();
        let password = $("#password").val();

        let user = {
            username : username,
            password : password
        }

        $.ajax({
            url : 'doLogin',
            type: 'post',
            data:user,
            success: function(data) {
                alert("登录成功")
            }
        })

    })


</script>
</body>
</html>