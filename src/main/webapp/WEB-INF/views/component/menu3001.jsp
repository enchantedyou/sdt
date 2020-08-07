<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>仓库类型</label>
		<div class="layui-input-block">
			<select name="repositoryType" lay-verify="required" id="rpoSelect" lay-filter="rpoSelect"></select>
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="iobusVerSubmit">提交</button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>
<hr class="layui-bg-gray"/>
<fieldset class="layui-elem-field layui-field-title">
	<legend>预览</legend>
	<div class="layui-field-box">
		<pre class="layui-code" lay-skin="notepad" id="showIobusVer"></pre>
	</div>
</fieldset>