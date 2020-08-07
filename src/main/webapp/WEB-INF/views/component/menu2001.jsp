<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="dataSourceSubmit">查询</button>
	</div>
</form>
<hr class="layui-bg-gray"/>

<table class="layui-table" id="bodyTable" lay-filter="bodyTable">
</table>
<script type="text/html" id="gridOperate">
	<a class="layui-btn layui-btn-xs" lay-event="dsEdit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="dsDel">删除</a>
</script>
<script type="text/html" id="gridAdd">
	<a class="layui-btn layui-btn-xs" lay-event="dsAdd">+新增</a>
</script>