<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="layui-form">
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>原交易流程编号</label>
		<div class="layui-input-block">
			<input type="text" name="originalFlowtranId" max="6" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>交易流程编号</label>
		<div class="layui-input-block">
			<input type="text" name="flowtranId" max="6" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>交易描述</label>
		<div class="layui-input-block">
			<input type="text" name="longname" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>交易类型</label>
		<div class="layui-input-block">
			<select name="trxnKind" lay-verify="required" id="kindSelect" lay-filter="kindSelect">
				<option value="">请选择</option>
				<option value="F">金融交易</option>
				<option value="P">参数交易</option>
				<option value="Q">查询交易</option>
				<option value="M">维护交易</option>
			</select>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>服务类型</label>
		<div class="layui-input-block">
			<input type="text" name="serviceLocation" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>服务编号</label>
		<div class="layui-input-block">
			<input type="text" name="serviceId" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>复合类型</label>
		<div class="layui-input-block">
			<input type="text" name="complexLocation" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>复合类型编号</label>
		<div class="layui-input-block">
			<input type="text" name="complexId" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>服务变量名称</label>
		<div class="layui-input-block">
			<input type="text" name="valName" lay-verify="required" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>输入字段列表</label>
		<div class="layui-input-block">
			<textarea name="inputFields" lay-verify="required" class="layui-textarea"/>
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label"><i class="layui-icon input-icon">*</i>输出字段列表</label>
		<div class="layui-input-block">
			<textarea name="outputFields" class="layui-textarea"/>
		</div>
	</div>
	<div class="layui-form-item layui-form-btn">
		<button type="button" class="layui-btn" lay-submit lay-filter="trxnBuildSubmit">提交</button>
		<button type="reset" class="layui-btn layui-btn-primary">重置</button>
	</div>
</form>