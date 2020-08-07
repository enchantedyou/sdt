<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>模块编号</label>
		<div class="layui-input-block">
			<select name="moduleId" lay-verify="required" id="moduleSelect" lay-filter="moduleSelect"></select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>JIRA编号</label>
		<div class="layui-input-block">
			<input type="text" name="jiraId" max="6" lay-verify="required" id="jiraId" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="diffSubmit">提交</button>
		<button type="button" class="layui-btn" title="复制" data-clipboard-target="#showDiffs" id="copy-diffs-btn"><i class="layui-icon">&#xe66d;</i></button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>
<hr class="layui-bg-gray"/>
<fieldset class="layui-elem-field layui-field-title">
	<legend>预览</legend>
	<div class="layui-field-box">
		<pre class="layui-code" lay-skin="notepad" id="showDiffs"></pre>
	</div>
</fieldset>