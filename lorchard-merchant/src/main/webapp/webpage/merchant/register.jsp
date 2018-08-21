<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/static/css/merchant/register.css">
<title>商家注册</title>
</head>
<body>
<div class="container">
		<div class="header">
			<a href="">
				<img src="<%=basePath%>/static/icon/icon.png">
				小果源</a>
			<div>免费注册</div>
		</div>
		<div class="center">
			<div class="classification">
				<div class="label">手机号码</div>
				<input type="text" class="content" name="mobile" placeholder="注册后使用手机号登陆">
				<div class="clear"></div>
			</div>
			<div class="classification">
				<div class="label">短信验证码</div>
				<input type="text" class="content" name="mobile" style="width: 230px;" placeholder="填写6位短信验证码" maxlength="6">
				<button type="button">获取验证码</button>
				<div class="clear"></div>
			</div>
			<div class="classification">
				<div class="label">设置密码</div>
				<input type="text" class="content" name="mobile" placeholder="8~20个字，包含字母和数字">
				<div class="clear"></div>
			</div>
			<div class="classification">
				<div class="label">确认密码</div>
				<input type="text" class="content" name="mobile" placeholder="请再次输入密码">
				<div class="clear"></div>
			</div>
			<div class="buttom">立即注册</div>
			<div class="login">已有账号,立即登陆</div>
		</div>
		<div class="footer"></div>
	</div>
</body>
</html>