<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>交易流程编号</label>
		<div class="layui-input-block">
			<input type="text" name="flowtranId" max="6" lay-verify="required" id="flowtranId" class="layui-input" placeholder="例如:ln6001">
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="hidden" class="layui-btn" title="下载" id="intfDownload" lay-submit lay-filter="intfDownload"><i class="layui-icon">&#xe601;</i></button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>