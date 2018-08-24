var merchant_login_ops ={
	init:function(){
		this.eventBind();
	},
	validateForm:function(){
	  	return $(".formarea").validate();
	},
	eventBind:function(){
		// 登录
		var that = this;
		
		$(".formarea .login").click(function(e){
			$that = $(this);
			$that.addClass('disable');
			var mobile = $('.formarea input[name="mobile"]').val();
			var password = $('.formarea input[name="password"]').val();
			var verifyCode = $('.formarea input[name="verifyCode"]').val();
			var data={
				mobile:mobile,
				name:name,
				password:password,
			    verifyCode:verifyCode
			};
			if(that.validateForm().form()){
			   $.ajax({
				   url:WEB_ROOT+'/merchant/login',
				   type:'POST',
				   data:data,
				   dataType:'json'
			   }).done(function(res){
				  var callback = null;
				  if(res.code == 0){
					 callback = function(){
						 winodw.location.href = WEB_ROOT+"/index";
					 };
				  }else{
					 callback = function() {
					     $thta.removeClass('disable');
					}
				  }
				  common_ops.alert(res.msg,callback);
			   }).fail(function(res){
				  common_ops.alert("服务器异常");
			   });
			}else{
				$that.removeClass('disable');
			}
		});
		// 点击刷新验证码
		$(".formarea #captchaImg").click(function(e){
			$(this).attr('src',WEB_ROOT+'/merchant/captcha.jpg')
		});
		
		// 点击到注册
		$(".formarea .register").click(function(){
			window.location.href = WEB_ROOT+"/merchant/register";
		});
		
		
		
	}
		
};
$(function(){
	merchant_login_ops.init();
})