<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=basePath%>/static/css/merchant/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>小果源登录</title>
</head>
<body>
	<div class="container">
		<div class="header">
			<a href="#"> <img src="<%=basePath%>/static/icon/icon.png"> 小果源
			</a>
			<div>登录</div>
		</div>
		<div class="center">
			<div class="classification">
				<div class="label">手机号码</div>
				<input type="text" class="content" name="mobile"
					placeholder="注册时填写的手机号">
				<div class="clear"></div>
			</div>

			<div class="classification">
				<div class="label">登录密码</div>
				<input type="text" class="content" name="mobile" placeholder="请输入密码">
				<div class="clear"></div>
			</div>

			<div class="classification">
				<div class="label">验证码</div>
				<input type="text" class="content" name="mobile"
					style="width: 230px;" placeholder="请输入验证码"> <img>
				<div class="clear"></div>
			</div>

			<div class="buttom">立即登录</div>
			<div class="login">免费注册,轻松开店</div>
		</div>
		<div class="footer"></div>
	</div>
</body>
</html>