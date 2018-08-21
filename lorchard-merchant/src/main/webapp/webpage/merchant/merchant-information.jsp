<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>/static/css/merchant/merchant-information.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
* { -ms-word-wrap: break-word; word-wrap: break-word; }
html { -webkit-text-size-adjust: none; text-size-adjust: none; }
html, body {height:100%;width:100%; }
.wrap{width:464px;height:34px;margin:200px auto;border:0;position:relative;}
.input{position:absolute;top:0;left:0;width:457px;margin:0;padding-left:5px;height:30px;line-height:30px;font-size:12px;border:1px solid #c9cacb;}
s{position:absolute;top:1px;right:0;width:32px;height:32px;background:url("img/arrow.png") no-repeat;}
._citys { width: 450px; display: inline-block; border: 2px solid #eee; padding: 5px; position: relative; }
._citys span { color: #05920a; height: 15px; width: 15px; line-height: 15px; text-align: center; border-radius: 3px; position: absolute; right: 10px; top: 10px; border: 1px solid #05920a; cursor: pointer; }
._citys0 { width: 95%; height: 34px; line-height: 34px; display: inline-block; border-bottom: 2px solid #05920a; padding: 0px 5px; font-size:14px; font-weight:bold; margin-left:6px; }
._citys0 li { display: inline-block; line-height: 34px; font-size: 15px; color: #888; width: 80px; text-align: center; cursor: pointer; }
._citys1 { width: 100%; display: inline-block; padding: 10px 0; }
._citys1 a { width: 83px; height: 35px; display: inline-block; background-color: #f5f5f5; color: #666; margin-left: 6px; margin-top: 3px; line-height: 35px; text-align: center; cursor: pointer; font-size: 12px; border-radius: 5px; overflow: hidden; }
._citys1 a:hover { color: #fff; background-color: #05920a; }
.AreaS { background-color: #05920a !important; color: #fff !important; }
</style>
<title>Insert title here</title>
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