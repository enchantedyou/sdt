<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>分组数</label>
		<div class="layui-input-block">
			<input type="text" name="upperLimit" max="6" lay-verify="int" id="upperLimit" class="layui-input" value="20">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>序列号</label>
		<div class="layui-input-block">
			<input type="text" name="sequence" max="6" lay-verify="required" id="sequence" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="shardingHashSubmit">提交</button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>
<hr class="layui-bg-gray"/>
<fieldset class="layui-elem-field layui-field-title">
	<legend>预览</legend>
	<div class="layui-field-box">
		<pre class="layui-code" lay-skin="notepad" id="showShardingHash"></pre>
	</div>
</fieldset>