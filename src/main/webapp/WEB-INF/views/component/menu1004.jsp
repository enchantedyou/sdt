<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>源对象编号</label>
		<div class="layui-input-block">
			<input type="text" name="sourceEntityId" max="50" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>源变量名</label>
		<div class="layui-input-block">
			<input type="text" name="sourceVarName" max="20" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>目标对象编号</label>
		<div class="layui-input-block">
			<input type="text" name="targetEntityId" max="50" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>目标变量名</label>
		<div class="layui-input-block">
			<input type="text" name="targetVarName" max="20" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="buildSetFieldSubmit">提交</button>
		<button type="button" class="layui-btn" title="复制" data-clipboard-target="#showSetStatement" id="copy-diffs-btn"><i class="layui-icon">&#xe66d;</i></button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>
<hr class="layui-bg-gray"/>
<fieldset class="layui-elem-field layui-field-title">
	<legend>预览</legend>
	<div class="layui-field-box">
		<pre class="layui-code" lay-skin="notepad" id="showSetStatement"></pre>
	</div>
</fieldset>