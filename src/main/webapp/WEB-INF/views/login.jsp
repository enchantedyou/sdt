<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>开发助手平台</title>
    <link rel="stylesheet" href="../layui/css/layui.css"/>
    <link rel="stylesheet" href="../css/login.css">
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
</head>
<body id="login">
    <div class="login">
        <h2>开发助手平台</h2>
        <form class="layui-form" id="login-form">
            <div class="layui-form-item">
                <input type="text" name="userAcct" placeholder="账号" lay-verify="required" class="layui-input">
                <i class="layui-icon input-icon">&#xe66f;</i>
            </div>
            <div class="layui-form-item">
                <input type="password" name="userPwd" placeholder="密码" lay-verify="required" class="layui-input" id="user-pwd">
                <i class="layui-icon input-icon">&#xe673;</i>
            </div>
            <div class="layui-form-item">
                <select name="datasourceId" lay-verify="required" id="dataSourceSelect"></select>
            </div>
            <div class="layui-form-item">
                <button type="button" style="width: 100%" class="layui-btn" lay-submit lay-filter="login" id="login-btn">登录</button>
            </div>
        </form>
    </div>
    <input type="hidden" class="basePath" value="${basePath }">
    <script type="text/javascript" src="../layui/layui.all.js"></script>
    <script type="text/javascript" src="../js/clipboard.min.js"></script>
    <script type="text/javascript" src="../js/lib.js"></script>
</body>
</html>
