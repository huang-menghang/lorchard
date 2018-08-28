<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>/static/css/merchant/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小果源登录</title>
<style type="text/css">
  .error{
    color:red;
    font-size: 14px;
    height: 40px;
    line-height: 40px;
  }
</style>
</head>
<body>
	<div class="container">
	    <form class="formarea">
		<div class="header">
			<a href="#"> <img src="<%=basePath%>/static/icon/icon.png"> 小果源
			</a>
			<div>登录</div>
		</div>
		<div class="center">
			<div class="classification">
				<div class="label">手机号码</div>
				<input type="text" class="content" name="mobile"
				  required="true" diyRule="^((1[3|4|5|7|8][0-9]{1})\d{8})$" diyCheck="手机号码格式不正确"	placeholder="注册时填写的手机号">
				<div class="clear"></div>
			</div>

			<div class="classification">
				<div class="label">登录密码</div>
				<input type="password" required="true" minlength="8" maxlength="20" class="content" name="password" placeholder="请输入密码">
				<div class="clear"></div>
			</div>

			<div class="classification">
				<div class="label">验证码</div>
				<input type="text" class="content" name="verifyCode"
					style="width: 250px;"  required="true" minlength="4" maxlength="4" placeholder="请输入验证码">	
					
				<img id="captchaImg" style="width: 120px;height:44px" src="<%=basePath%>/merchant/captcha.jpg" />
			<div class="clear"></div>	
			</div>

			<div class="login">立即登录</div>
			<div class="register">免费注册,轻松开店</div>
			<div class="password-forget">忘记密码?</div>
		</div>
		</form>
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/merchant/login.js"></script>
</body>
</html>