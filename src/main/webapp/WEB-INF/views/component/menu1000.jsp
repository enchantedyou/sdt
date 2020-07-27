<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<i class="layui-icon input-icon">*</i>
		<label class="layui-form-label">关键字</label>
		<input type="text" name="key" lay-verify="required" class="layui-input">
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="dictSearch">查询</button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>
<hr class="layui-bg-gray"/>

<table class="layui-table" id="bodyTable" lay-filter="bodyTable">
</table>