<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>/static/css/merchant/merchant-information.css">
<link rel="stylesheet" href="<%=basePath%>/static/plugin/city-select/citySet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商铺申请</title>
<body>
<div class="container">
	<div class="portrait">
		<img src="<%=basePath%>/static/icon/icon.png">
	</div>
	<div class="title">商户信息</div>
	<div class="separate">
		<div class="laber"><span>*</span>商户名称</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的商户名称">
		<div class="clear"></div>
	</div>
	<div class="separate">
		<div class="laber"><span>*</span>商户地址</div>
		<input type="text" autocomplete="off" name="city" placeholder="请选择省/市/区">
		<div class="clear"></div>
	</div>
	<div class="separate">
		<div class="laber"><span>*</span>详细地址</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的具体地址">
		<div class="clear"></div>
	</div>
	<div class="introduction">
		<div class="laber"><span>*</span>商户简介</div>
		<textarea type="text" autocomplete="off" name="name" placeholder="请填写您的商户简介"></textarea>
		<div class="clear"></div>
	</div>
	
	
	<div class="title">运营人信息</div>
	<div class="separate">
		<div class="laber"><span>*</span>姓名</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的姓名">
		<div class="clear"></div>
	</div>
	<div class="separate">
		<div class="laber"><span>*</span>手机号码</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的手机号码">
		<div class="clear"></div>
	</div>
	<div class="separate">
		<div class="laber"><span>*</span>QQ号</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的QQ号">
		<div class="clear"></div>
	</div>
	<div class="separate">
		<div class="laber"><span>*</span>微信号</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的微信号">
		<div class="clear"></div>
	</div>
	<div class="separate">
		<div class="laber"><span>*</span>邮箱</div>
		<input type="text" autocomplete="off" name="name" placeholder="请填写您的邮箱">
		<div class="clear"></div>
	</div>
	<div class="buttom">创建店铺</div>
</div>
<jsp:include page="/context/js-tags.jsp" />
<script type="text/javascript" src="<%=basePath%>/static/plugin/city-select/Popt.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugin/city-select/city.json.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/plugin/city-select/citySet.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/merchant/merchant-apply.js"></script>

</body>
</html>