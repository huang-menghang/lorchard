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
						<div class="shop-qrcode">
							<p style="color: #333">微信扫码访问小程序</p>
							<p style="width: 180px; height: 180px;">
								<img style="width: 180px; height: 180px;"
									src="<%=basePath%>/static/images/my.png">
							</p>
							<p>
								<a href="#" style="color: #38f;">下载小程序</a>
							</p>
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

			<!--待办事项模块-->

			<div class="layui-row layui-col-space15" style="width:96%;">
				<div class="layui-col-md6">
					<div class="layui-card">
						<div class="layui-card-header">快捷方式</div>
						<div class="layui-card-body">

							<div class="layui-carousel layadmin-carousel layadmin-shortcut"
								lay-anim="" lay-indicator="inside" lay-arrow="none"
								style="width: 100%; height: 208px;">
								<div carousel-item="">
									<ul class="layui-row layui-col-space10 layui-this" id="ul">
										<li class="layui-col-xs3"><a href="<%=basePath%>/set?title=setIndex">
												<i class="layui-icon layui-icon-console">&#xe770;</i> <cite>我的资料</cite>
										</a></li>
										<li class="layui-col-xs3"><a href="<%=basePath%>/order?title=orderAll">
												<i class="layui-icon layui-icon-chart">&#xe655;</i> <cite>查看订单</cite>
										</a></li>
										<li class="layui-col-xs3"><a
											href="<%=basePath%>/goods?title=goodsIndex"> <i
												class="layui-icon layui-icon-find-fill">&#xe698;</i> <cite>查看商品</cite>
										</a></li>
										<li class="layui-col-xs3"><a
											href="<%=basePath%>/finance?title=financeIndex"> <i
												class="layui-icon layui-icon-survey">&#xe65e;</i> <cite>查看余额</cite>
										</a></li>
										<li class="layui-col-xs3"><a href="<%=basePath%>/finance?title=financeReceived">
												<i class="layui-icon layui-icon-user">&#xe735;</i> <cite>提现</cite>
										</a></li>
										<li class="layui-col-xs3"><a
											lay-href="set/system/website"> <i
												class="layui-icon layui-icon-set">&#xe607;</i> <cite>帮助</cite>
										</a></li>
										<li class="layui-col-xs3"><a href="https://www.ysdevelop.com/">
												<i class="layui-icon layui-icon-chat">&#xe62e;</i> <cite>关于源盛</cite>
										</a></li>
										<li class="layui-col-xs3"><a
											href="https://www.ysdevelop.com//contactPortal"> <i
												class="layui-icon layui-icon-template-1">&#xe606;</i> <cite>留言板</cite>
										</a></li>
									</ul>
								</div>
								<div class="layui-carousel-ind"></div>
								<button class="layui-icon layui-carousel-arrow" lay-type="sub"></button>
								<button class="layui-icon layui-carousel-arrow" lay-type="add"></button>
							</div>
						</div>
					</div>
				</div>

				<div class="layui-col-md6">
					<div class="layui-card">
						<div class="layui-card-header">待办事项</div>
						<div class="layui-card-body">

							<div class="layui-carousel layadmin-carousel layadmin-backlog"
								lay-anim="" lay-indicator="inside" lay-arrow="none"
								style="width: 100%; height: 208px;">
								<div carousel-item="">
									<ul class="layui-row layui-col-space10 layui-this" id="ul">
										<li class="layui-col-xs6"><a
											href="<%=basePath%>/order?title=orderUnDelivered" class="layadmin-backlog-body">
												<h3 id="h">待发货订单</h3>
												<p>
													<cite id="orderUnDelivered"></cite>
												</p>
										</a></li>
										<li class="layui-col-xs6"><a href="<%=basePath%>/order?title=orderUnReceived"
											class="layadmin-backlog-body">
												<h3 id="h">待收货订单</h3>
												<p>
													<cite id="orderUnReceived"></cite>
												</p>
										</a></li>
										<li class="layui-col-xs6"><a
											href="<%=basePath%>/order?title=orderRefund" class="layadmin-backlog-body">
												<h3 id="h">退款中的订单</h3>
												<p>
													<cite id="orderRefund"></cite>
												</p>
										</a></li>
										<li class="layui-col-xs6"><a href="<%=basePath%>/order?title=orderUnpaid"
											class="layadmin-backlog-body">
												<h3 id="h" >待支付订单</h3>
												<p>
													<cite id="orderUnpaid"></cite>
												</p>
										</a></li>
									</ul>
								</div>
								<div class="layui-carousel-ind"></div>
								<button class="layui-icon layui-carousel-arrow" lay-type="sub"></button>
								<button class="layui-icon layui-carousel-arrow" lay-type="add"></button>
							</div>
						</div>
					</div>
				</div>

			</div>
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