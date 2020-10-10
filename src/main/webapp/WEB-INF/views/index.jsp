<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>开发助手平台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="../layui/css/layui.css"/>
    <link rel="stylesheet" href="../css/index.css">
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
</head>

<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">开发助手平台</div>
        <ul class="layui-nav layui-layout-left">
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <c:if test="${sessionScope.userInfo != null}">
                    <a href="javascript:;">${sessionScope.userInfo.userAcct}</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="user_login_out">退出</a></dd>
                    </dl>
                </c:if>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item">
                    <a href="javascript:;">元数据</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="m1000">[1000]字典搜索</a></dd>
                        <dd><a href="javascript:;" id="m1001">[1001]PTE代码生成</a></dd>
                        <dd><a href="javascript:;" id="m1002">[1002]接口文档生成</a></dd>
                        <dd><a href="javascript:;" id="m1003" style="display: none">[1003]交易模型生成</a></dd>
                        <dd><a href="javascript:;" id="m1004">[1004]赋值语句生成</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">数据源</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="m2000" style="display: none">[2000]日终批量</a></dd>
                        <dd><a href="javascript:;" id="m2001">[2001]数据源列表查询</a></dd>
                        <dd><a href="javascript:;" id="m2002">[2002]数据库解锁</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">私服</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="m3000">[3000]合并请求差异</a></dd>
                        <dd><a href="javascript:;" id="m3001">[3001]服务总线最新版本</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">脚本</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="m4000">[4000]交易脚本生成</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">其他</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" id="m5000">[5000]分片哈希值查询</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body"></div>
</div>
<input type="hidden" class="basePath" value="${basePath }">
<script type="text/javascript" src="../layui/layui.all.js"></script>
<script type="text/javascript" src="../js/clipboard.min.js"></script>
<script type="text/javascript" src="../js/lib.js"></script>
</body>
</html>
