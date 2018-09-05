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
				<div class="header-logo">
					<img src="<%=basePath%>/static/icon/icon.png">
				</div>
				<div class="header-info">
					<div class="header-name"></div>
					<div class="header-danbao">
						<div>
							<i class="layui-icon active">&#xe672;</i>个人认证
						</div>
						<div>
							<i class="layui-icon active">&#xe672;</i>担保交易
						</div>
						<div>
							<i class="layui-icon">&#xe672;</i>线下门店
						</div>
					</div>
				</div>
				<div class="header-action">
					<div class="primary realse-goods">发布商品</div>
					<div class="primary ">新建微商城</div>
					<div class="access-shop" style="float: left;">
						<div class="primary access-shop">访问店铺</div>
					   <div class="shop-qrcode" >
		                   <p style="color:#333">微信扫码访问小程序</p>
		                   <p style="width: 180px; height: 180px;">
			               <img style="width: 180px; height: 180px;" src="<%=basePath%>/static/images/my.png">
		                   </p>
		                  <p><a href="#" style="color: #38f;">下载小程序</a></p>
	                   </div>
					</div>
				</div>
			</div>
			<div class="clear"></div>
			<div class="board">
				<div class="board-box">
					<div class="border-value yesterday-pageView"></div>
					<div class="border-title">昨日游览量</div>
				</div>
				<div class="board-box">
					<div class="border-value yesterday-visitorNumber"></div>
					<div class="border-title">昨日访客数</div>
				</div>
				<div class="board-box">
					<div class="border-value yesterday-goodsView"></div>
					<div class="border-title">昨日商品游览量</div>
				</div>
				<div class="board-box">
					<div class="border-value yesterday-goodsAceessNumber"></div>
					<div class="border-title">昨日商品访客数</div>
				</div>
				<div class="board-box">
					<div class="border-value goodsCount"></div>
					<div class="border-title">商品</div>
				</div>
			</div>
			<div id="board-diagram" style="width: 860px; height: 400px;"></div>
		</div>
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/echarts/echarts.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/echarts/chart.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/shop/index.js"></script>
</body>
</html>