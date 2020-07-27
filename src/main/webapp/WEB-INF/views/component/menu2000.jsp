<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<i class="layui-icon input-icon">*</i>
		<label class="layui-form-label">法人代码</label>
		<input type="text" name="busiOrgId" lay-verify="required" class="layui-input layui-disabled" disabled="disabled">
	</div>
	<div class="layui-form-item">
		<i class="layui-icon input-icon">*</i>
		<label class="layui-form-label">交易日期</label>
		<input type="text" name="trxnDate" lay-verify="required" class="layui-input layui-disabled" disabled="disabled">
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="batchSearch" id="batch-search-btn">查询</button>
		<button type="button" class="layui-btn" lay-submit lay-filter="batchRun">提交</button>
	</div>
</form>
<hr class="layui-bg-gray"/>

<table class="layui-table" id="bodyTable" lay-filter="bodyTable">
</table>