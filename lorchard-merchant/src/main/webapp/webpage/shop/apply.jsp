<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=basePath%>/static/css/shop/shop-apply.css">
<link rel="stylesheet"
	href="<%=basePath%>/static/plugin/city-select/citySet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商铺申请</title>
<style type="text/css">
.error {
	color: red;
	font-size: 14px;
	height: 40px;
	line-height: 40px;
}
#description-error{
    height:100%
}
</style>
<body>
	<div class="container">
		<form class="formarea">
			<div class="portrait">
				<img src="<%=basePath%>/static/icon/icon.png">
			</div>
			<div class="title">商户信息</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>商户名称
				</div>
				<input type="text" required="true" autocomplete="off" name="name"
					placeholder="请填写您的商户名称">
				<div class="clear"></div>
			</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>商户地址
				</div>
				<input type="text" autocomplete="off" name="province"
					placeholder="请选择省/市/区" required="true" readonly="readonly">
				<div class="clear"></div>
			</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>详细地址
				</div>
				<input type="text" required="true" autocomplete="off"
					name="detail-address" placeholder="请填写您的具体地址">
				<div class="clear"></div>
			</div>
			<div class="introduction">
				<div class="laber">
					<span>*</span>商户简介
				</div>
				<textarea type="text" required="true" autocomplete="off"
					name="description" style="height:115px" placeholder="请填写您的商户简介"></textarea>
				<div class="clear"></div>
			</div>
			<div class="title">运营人信息</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>姓名
				</div>
				<input type="text" required="true" autocomplete="off"
					name="operatorName" placeholder="请填写您的姓名">
				<div class="clear"></div>
			</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>手机号码
				</div>
				<input type="text" required="true"
					diyRule="^((1[3|4|5|7|8][0-9]{1})\d{8})$" diyCheck="手机号码格式不正确"
					autocomplete="off" name="mobile" placeholder="请填写您的手机号码">
				<div class="clear"></div>
			</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>QQ号
				</div>
				<input type="text" required="true" diyRule="^[1-9][0-9]{4,9}$"
					diyCheck="QQ格式不正确" autocomplete="off" name="qq"
					placeholder="请填写您的QQ号">
				<div class="clear"></div>
			</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>微信号
				</div>
				<input type="text" required="true" autocomplete="off"
					name="wechatNo" placeholder="请填写您的微信号">
				<div class="clear"></div>
			</div>
			<div class="separate">
				<div class="laber">
					<span>*</span>邮箱
				</div>
				<input type="text"
					diyRule="^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$"
					diyCheck="邮箱地址不正确" autocomplete="off" name="email"
					placeholder="请填写您的邮箱">
				<div class="clear"></div>
			</div>
		</form>
		<div class="apply">创建店铺</div>
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/city-select/Popt.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/city-select/city.json.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/city-select/citySet.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/shop/apply.js"></script>
</body>
</html>