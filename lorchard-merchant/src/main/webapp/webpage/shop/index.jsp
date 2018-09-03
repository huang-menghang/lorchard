<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/static/css/shop/index.css">
<title>店铺概述</title>
</head>
<body>
	<jsp:include page="/common/left-side.jsp" />
	<div class="tpl-content-wrapper">
		<div class="shop-content">
			<div class="">
				<div class="header-logo"><img src="<%=basePath %>/static/icon/icon.png"></div>
				<div class="header-info">
					<div class="header-name">碎碎宝宝0818</div>
					<div class="header-danbao">
						<div><i class="layui-icon active">&#xe672;</i>个人认证</div>
						<div><i class="layui-icon active">&#xe672;</i>担保交易</div>
						<div><i class="layui-icon">&#xe672;</i>线下门店</div>
					</div>
				</div>
				<div class="header-action">
					<div class="primary">发布商品</div>
					<div class="primary">新建微商城</div>
					<div class="primary">访问店铺</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="board">
				<div class="board-box">
					<div class="border-value">30</div>
					<div class="border-title">昨日游览量</div>
				</div>
				<div class="board-box">
					<div class="border-value">30</div>
					<div class="border-title">昨日访客数</div>
				</div>
				<div class="board-box">
					<div class="border-value">30</div>
					<div class="border-title">昨日商品游览量</div>
				</div>
				<div class="board-box">
					<div class="border-value">30</div>
					<div class="border-title">昨日商品访客数</div>
				</div>
				<div class="board-box">
					<div class="border-value">30</div>
					<div class="border-title">商品</div>
				</div>
			</div>
			<div id="board-diagram" style="width:860px; height: 400px;">
			</div>
		</div>		
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript" src="<%=basePath%>/static/plugin/echarts/echarts.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/shop/index.js"></script>
</body>
</html>