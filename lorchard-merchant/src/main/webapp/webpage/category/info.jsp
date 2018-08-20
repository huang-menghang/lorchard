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
					<li class="layui-this ops-title"></li>
				</ul>
				<div class="layui-form" style="margin-top: 10px;">
					<div class="layui-form-item">
						<label class="layui-form-label">分类名称</label>
						<div class="layui-input-inline input-custom-width">
								<input type="text" name="name"  readonly="readonly" 
								class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
					    <label class="layui-form-label">上级分类名称</label>
					    <div class="layui-input-inline input-custom-width">
							<input type="text" name="parentCategoryName"  readonly="readonly" 
								class="layui-input">
						</div>
					</div>
					
					<div class="layui-form-item">
						<label class="layui-form-label">分类图片
						</label>
						<div class="layui-input-inline input-custom-width">
							<img alt=" " border="none" name="image"
								style="display: none"
								width="100px" height="50px">
						</div>
					</div>


					
                    <div class="layui-form-item">
						<label class="layui-form-label">分类索引</label>
					    <div class="layui-input-inline input-custom-width">
							<input type="text" name="index"  readonly="readonly" 
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">分类描述</label>
						<div class="layui-input-inline input-custom-width">
							<textarea name="description"  placeholder="请输入分类描述" class="layui-textarea"></textarea>
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
		src="<%=basePath%>/static/js/category/info.js"></script>

</body>
</html>