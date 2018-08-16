<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<style type="text/css">
.x-red{
  color: red
}
.error{
  color: red
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
					<li class="layui-this">添加商品分类</li>
				</ul>
				<div class="layui-form" style="margin-top: 10px;">
					<div class="layui-form-item">
						<label class="layui-form-label"><span class="x-red">*</span>分类名称</label>
						<div class="layui-input-inline input-custom-width">
							<input type="text" name="name" required="true"
								value="" autocomplete="off" placeholder="请输入分类名称"
								class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label"><span class="x-red">*</span>上级分类</label>
					    <div class="layui-input-block" style="width: 190px; text-align: center;margin-left: 80px">
							<select name="parentId"  lay-filter="reportType" >
								<option >请选择上级分类</option>
								<option value="0">顶层分类</option>
							</select>
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label"> <span class="x-red">*</span>图片上传
						</label>
						<div class="layui-input-inline input-custom-width layui-upload">
							<div class="layui-box layui-upload-button"
								style="float: left; left: 0px; border: 1px solid #D2D2D2; width: 150px; background-color: #98a3b9; text-align: center;">
								<span class="layui-upload-icon"><i class="layui-icon"></i>上传图片</span>
							</div>
							<input id="uploadImage" name="imageFile" 
								class="layui-input image-upload" type="file"
								autocomplete="off" style="opacity: 0;">
						</div>
						<div class="layui-input-inline input-custom-width">
							<img alt=" " border="none" name="image"
								style="display: none"
								width="100px" height="50px">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">图片路径</label>
						<div class="layui-input-inline" style="width: 190px;">
							<input type="text" readonly="readonly" value=""
								placeholder="请上传图片" name="imagePath" required="true"
								autocomplete="off" class="layui-input">
						</div>
					</div>

					
                    <div class="layui-form-item">
						<label class="layui-form-label"><span class="x-red">*</span>分类索引</label>
						<div class="layui-input-block" style="width: 190px; text-align: center;margin-left: 80px">
							<select name="index" lay-filter="reportType" >
								<option >请选分类索引</option>
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">分类描述</label>
						<div class="layui-input-inline input-custom-width">
							<textarea name="description" lay-verify="" required="true" diyCheck="分类描述不能为空"
								autocomplete="off" placeholder="请输入分类描述" class="layui-textarea"></textarea>
						</div>
					</div>

					<div class="layui-form-item" >
						<div class="layui-input-block" style="margin-left: 8%">
							<button class="layui-btn" type="button" lay-submit="" lay-filter="category_add"></button>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
    </div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/js/category/set.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
    <script type="text/javascript" 
       src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>	

</body>
</html>