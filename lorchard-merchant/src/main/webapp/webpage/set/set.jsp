<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="/context/css-tags.jsp" />
<style type="text/css">
    .uploader-list {
		margin-left: -15px;
	}

.uploader-list .handle {
	background-color: black;
	color: white;
	filter: alpha(Opacity = 80);
	-moz-opacity: 0.5;
	opacity: 0.5;
	width: 100px;
	text-align: right;
	height: 18px;
	margin-bottom: -18px;
	display: none;
}

.uploader-list .handle span {
	position: relative margin-right: 5%;
}

.uploader-list .handle span:hover {
	position: relative cursor: pointer;
}

.uploader-list .file-iteme {
	margin: 12px 0 0 15px;
	padding: 1px;
	float: left;
}

.x-red {
	color: red
}

.error {
	color: red
}

.layui-form-item {
	margin-left: 5%;
	padding-bottom: 4%
}

.layui-col-md9 {
	background-color: #e2e2e2;
	height: 40px;
	padding-top: 1%;
	padding-left: 2%;
	margin-top: 2px;
	margin-bottom: 10px;
}
.layui-form-label{
width: 12%; 
margin-right: 2%;
}
</style>
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />
	<div class="tpl-content-wrapper">
		<div class="layui-tab layui-tab-brief main-tab-container">
			<form class="formarea">
				<div class="layui-tab-content">
					<div class="layui-tab layui-tab-card">
						<ul class="layui-tab-title">
							<li class="layui-this ops-title"></li>
						</ul>
						<!--基本信息  -->
						<div class="layui-col-md9">
							<div class="layui-col-md4">设置店铺信息</div>
						</div>
						<div class="layui-form" style="margin-top: 10px;">
							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>店铺名称：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="name" required="true" value="" diyRule="^[\\u4E00-\\u9FA5A-Za-z0-9_]+$" diyCheck="不能有特殊字符"
										autocomplete="off" placeholder="" class="layui-input"
										lay-verType="alert">
								</div>
							</div>



							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>省：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="province" required="true" value="" 
										autocomplete="off" placeholder="" class="layui-input"
										lay-verType="alert" diyRule="^[\u4e00-\u9fa5]{0,}$" diyCheck="请输入汉字">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>市：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="city" required="true" value="" 
										autocomplete="off" placeholder="" class="layui-input"
										lay-verType="alert" diyRule="^[\u4e00-\u9fa5]{0,}$" diyCheck="请输入汉字">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>区县：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="town" required="true" value="" 
										autocomplete="off" placeholder="" class="layui-input"
										lay-verType="alert" diyRule="^[\u4e00-\u9fa5]{0,}$" diyCheck="请输入汉字">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span class="x-red">*</span>详细地址：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="detailAddress" required="true"
										 value="" autocomplete="off" diyRule="^[\\u4E00-\\u9FA5A-Za-z0-9_]+$" diyCheck="不能有特殊字符"
										placeholder="" class="layui-input" lay-verType="alert" style="width: 350px">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span class="x-red">*</span>店铺描述：</label>
								<div class="layui-input-inline input-custom-width">
									<textarea name="description" lay-verify="" required="true"
									    autocomplete="off" placeholder=""
										class="layui-textarea"></textarea>
								</div>
							</div>
							<!--商品规格 -->
							<div class="layui-col-md9">
								<div class="layui-col-md4">运营人信息</div>
							</div>


							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>运营人：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="operatorName" 
										required="true" value="" autocomplete="off" diyRule="^[\u4e00-\u9fa5]{0,}$" diyCheck="请输入汉字"
										placeholder="" class="layui-input" lay-verType="alert">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>手机号码：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="mobile" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
										required="true" value="" autocomplete="off"
										placeholder="" class="layui-input" lay-verType="alert">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>QQ：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="qq" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
										required="true" value="" autocomplete="off"
										placeholder="" class="layui-input" lay-verType="alert">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>微信：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="wechatNo" diyRule="^[\\u4E00-\\u9FA5A-Za-z0-9_]+$" diyCheck="不能有特殊字符"
										required="true" value="" autocomplete="off"
										placeholder="" class="layui-input" lay-verType="alert">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>电子邮箱：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="email" diyRule="^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$" diyCheck="请输入正确的邮箱格式" 
										required="true" value="" autocomplete="off"
										placeholder="" class="layui-input" lay-verType="alert">
								</div>
							</div>

							<div class="layui-input-block"
								style="margin-left: 15%; margin-bottom: 5%">
								<button class="layui-btn" type="button" lay-submit="确认"
									lay-filter="goods_edit">确认</button>
							</div>


						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/js/set/set.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>

</body>
</html>