<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<jsp:include page="/context/css-tags.jsp" />
<style type="text/css">
     <style type ="text /css">.uploader-list {
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
							<div class="layui-col-md4">基本信息</div>
						</div>
						<div class="layui-form" style="margin-top: 10px;">
							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>商品名称：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="name" required="true" value="" diyRule="^[\\u4E00-\\u9FA5A-Za-z0-9_]+$" diyCheck="不能有特殊字符"
										autocomplete="off" placeholder="请输入商品名称" class="layui-input"
										lay-verType="alert">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label"><span class="x-red">*</span>商品描述：</label>
								<div class="layui-input-inline input-custom-width">
									<textarea name="description" lay-verify="" required="true"
										diyCheck="商品描述不能为空" autocomplete="off" placeholder="请输入商品描述"
										class="layui-textarea"></textarea>
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>商品类别：</label>
								<div class="layui-input-block"
									style="width: 190px; text-align: center; margin-left: 14%">
									<select name="parentId" lay-filter="reportType">
										<option></option>
									</select>
								</div>
							</div>

							<div class="layui-upload" style="margin-left: 2%">
								    
								<div class="layui-input-inline input-custom-width layui-upload">
									<div class="layui-box layui-upload-button"
										style="float: left; left: 0px; border: 2px solid #D2D2D2; width: 150px; background-color:#009688; text-align: center; margin-left: 17%;color:white">
										<span class="layui-upload-icon"><i class="layui-icon"></i>上传图片</span>
									</div>
									<input id="uploadImage" name="imageFile" multiple="true"
										class="layui-input image-upload" type="file"
										autocomplete="off" style="opacity: 0;">
								</div>
								<blockquote class="layui-elem-quote layui-quote-nm"
									style="margin-top: 10px; margin-left: 6%; margin-right: 35%;">
									    预览图：     
									<div class="layui-upload-list uploader-list"
										style="overflow: auto;" id="uploader-list">    </div>
								</blockquote>
							</div>

							<div class="layui-form-item" style="display: none">
								<label class="layui-form-label">图片路径</label>
								<div class="layui-input-inline" style="width: 190px;">
									<input type="text" readonly="readonly" value=""
										placeholder="请上传图片" name="previewImagePath" required="true"
										autocomplete="off" class="layui-input">
								</div>
							</div>


							<!--商品规格 -->
							<div class="layui-col-md9">
								<div class="layui-col-md4">商品规格</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>商品规格：</label>
								<div class="layui-input-block"
									style="width: 190px; text-align: center; margin-left: 14%">
									<select name="type" lay-filter="reportType">
										<option></option>
										<option value="0">500g/元</option>
										<option value="1">1份/元</option>
									</select>
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span class="x-red">*</span>商品价格：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="originalPrice" required="true"
										 value="" autocomplete="off" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
										placeholder="请输入商品价格" class="layui-input" lay-verType="alert">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>折后价：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="minPrice" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
										required="true" value="" autocomplete="off"
										placeholder="请输入商品折后价" class="layui-input" lay-verType="alert">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label"><span class="x-red">*</span>规格描述:</label>
								<div class="layui-input-inline input-custom-width">
									<textarea name="specificationsDescription" required="true"
										placeholder="请输入规格描述" class="layui-textarea"></textarea>
								</div>
							</div>

							<!--其他信息  -->
							<div class="layui-col-md9">
								<div class="layui-col-md4">其他信息</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>库存：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="stock" required="true"
										 value="" autocomplete="off" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
										placeholder="请输入剩余库存" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">
								<span class="x-red">*</span>是否推荐：</label>
								<div class="layui-input-block"
									style="width: 190px; text-align: center; margin-left: 14%">
									<select name="recommend" lay-filter="reportType">
										<option></option>
										<option value="0">设置为非推荐商品</option>
										<option value="1">设置为推荐商品</option>
									</select>
								</div>
							</div>


							<div class="layui-input-block"
								style="margin-left: 15%; margin-bottom: 5%">
								<button class="layui-btn" type="button" lay-submit="确认添加"
									lay-filter="goods_edit"></button>
							</div>


						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/js/goods/set.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>

</body>
</html>