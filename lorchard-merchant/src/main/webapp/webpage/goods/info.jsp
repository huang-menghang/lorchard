<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<style type="text/css">
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

img {
	float: left;
	margin-left: 10px;
}

.layui-form-label {
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
						<div class="layui-form" style="margin-top: 10px;">
							<!--基本信息  -->
							<div class="layui-col-md9">
								<div class="layui-col-md4">基本信息</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">商品名称:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="name" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">商品描述:</label>
								<div class="layui-input-inline input-custom-width">
									<textarea name="description" placeholder="请输入商品描述"
										class="layui-textarea"></textarea>
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">分类名称:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="parentCategoryName" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							<div style="margin-bottom: 8%; margin-left: 5%">
								<label class="layui-form-label">图片一览:</label>
							</div>
							<div>
								<blockquote class="layui-elem-quote layui-quote-nm"
									style="margin-top: 10px; margin-left: 8%; margin-right: 35%;">
									    预览图：     
									<div class="layui-upload-list uploader-list"
										style="overflow: auto;" id="uploader-list">    </div>
								</blockquote>
							</div>


							<!--商品规格 -->
							<div class="layui-col-md9">
								<div class="layui-col-md4;margin-right:2%">商品规格</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">商品规格:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="specificationsDescription"
										readonly="readonly" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">价格:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="originalPrice" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">最低价格:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="minPrice" readonly="readonly"
										class="layui-input">
								</div>
							</div>


							<!--其他信息  -->
							<div class="layui-col-md9">
								<div class="layui-col-md4" style="width: 12%; margin-right: 2%">其他信息:</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">是否推荐:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="recommend" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">库存量:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="stock" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">销售量:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="sales" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">好评数量:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="numberGoodReputation"
										readonly="readonly" class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block" style="margin-left: 8%">
									<INPUT onclick="history.go(-1)" type="button" value="返回"
										class="layui-btn layui-btn-lg"
										style="margin-left: 6%; margin-top: 2%">
									</button>
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
		src="<%=basePath%>/static/js/goods/info.js"></script>

</body>
</html>