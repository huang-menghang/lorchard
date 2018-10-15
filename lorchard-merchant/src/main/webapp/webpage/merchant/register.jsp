<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="<%=basePath%>/static/css/merchant/register.css">
<style type="text/css">
  .error{
    color:red;
    font-size: 14px;
    height: 40px;
    line-height: 40px;
  }
</style>	
<title>商家注册</title>
</head>
<body>
	<div class="container">
		<div class="header">
			<a href=""> <img src="<%=basePath%>/static/icon/icon.png">
				小果源
			</a>
			<div>免费注册</div>
		</div>
		<form class="formarea">
		<div class="center">
		    
			<div class="classification">
				<div class="label">手机号码</div>
				<input type="text" class="content" name="mobile"
				required="true" diyRule="^((1[3|4|5|7|8][0-9]{1})\d{8})$" diyCheck="手机号码格式不正确" placeholder="注册后使用手机号登陆">
				<div class="clear"></div>
			</div>
			<div class="classification">
				<div class="label">短信验证码</div>
				<input type="text" class="content" name="verifyMoblieMessage"
					style="width: 230px;" placeholder="填写6位短信验证码" required="true" minlength="6" maxlength="6">
				<button type="button" class="smsCode" >获取验证码</button>
				<div class="clear"></div>
			</div>
			<div class="classification">
				<div class="label">设置密码</div>
				<input type="password" class="content" required="true" minlength="8" maxlength="20" name="password"
					placeholder="8~20个字，包含字母和数字">
				<div class="clear"></div>
			</div>
			<div class="classification">
				<div class="label">确认密码</div>
				<input type="password" class="content" required="true" minlength="6" maxlength="20" name="surePassword"
					placeholder="请再次输入密码">
				<div class="clear"></div>
			</div>
			
			<div class="classification" style="padding-bottom: 1%;margin-top: 6%;">
			<div class="label" style="width:44%;"> 
                                我已阅读并同意<button  type="button" class="agreement" style="margin-left:36%;margin-top:-15%;">《源盛用户协议》</button>
             </div>
             <input type="checkbox"  required="true" name="agree" id="agreeProtocol" tabindex="-1" class="agree" checked="" style="height:20px;width:3%;margin:3%;margin-top:2%;" placeholder="请勾选">                  
            </div>
			
			<div>
			<div class="register">立即注册</div>
			</div>
			<div class="login" >已有账号,立即登陆</div>
		</div>
		</form>
		<div class="footer"></div>
	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/merchant/register.js"></script>
</body>
</html>