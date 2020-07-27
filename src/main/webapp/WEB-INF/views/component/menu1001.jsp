<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>交易流程编号</label>
		<div class="layui-input-block">
			<input type="text" name="flowtranId" max="6" lay-verify="required" id="flowtranId" class="layui-input" placeholder="例如:ln6001">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>PTE模板</label>
		<div class="layui-input-block">
			<select name="pteModule" lay-verify="required" id="PTESelect" lay-filter="PTESelect"></select>
		</div>
	</div>
	<div class="layui-form-item" id="listNameDiv" style="display: none">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>列表名称</label>
		<div class="layui-input-block">
			<select name="listName" id="listName" lay-filter="listNameSelect"></select>
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="PTESubmit">提交</button>
		<button type="hidden" class="layui-btn" title="下载" style="display: none" id="PTEDownload" lay-submit lay-filter="PTEDownload"><i class="layui-icon">&#xe601;</i></button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>
<hr class="layui-bg-gray"/>
<fieldset class="layui-elem-field layui-field-title">
	<legend>预览</legend>
	<div class="layui-field-box">
		<pre class="layui-code" lay-skin="notepad" id="showJson"></pre>
	</div>
</fieldset>