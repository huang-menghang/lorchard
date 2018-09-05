;
var i;
var time = 180;
var isCount = false;
var merchant_register_ops = {
	init : function() {
		this.eventBind();
	},
	validateForm:function(){
		return $(".formarea").validate();
	},
	getCode : function() {
		isCount = true;
		i = setInterval(function() {
			$(".smsCode").html(time + ",秒后重新发送");
			time--;
			if (time < 0) {
				i = window.clearInterval(i);
				time = 180;
//				//将session中的code把它删除,120秒以后就失效
//				$.ajax({
//					url:'../member/invalidateMobileMsg',
//					method:'POST',
//					type:'text/json',
//					success:function(res){
//						console.log(res.msg);
//					}
//				})
				$(".checkCode").html("获取手机校验码");
				isCount = false;
			}
		}, 1000);
	},
	eventBind : function() {
		var that = this;
		$(".center .login").click(function() {
			window.location.href = WEB_ROOT + '/merchant/login';
		});
		
		$(".smsCode").click(function(e){
			if (isCount) {
				return;
			}
			$(this).addClass("disable");
			$.ajax({
				url:WEB_ROOT+'/smscode/send',
				type:'POST',
				dataType:'json'
			}).done(function(res){
				if(res.code == 0){
					common_ops.alert('你的手机验证码是'+res.data);
				}else{
					common_ops.alert('手机验证码获取失败');
				}
				that.getCode();
			}).fail(function(res){
				$(this).removeClass('disable');
			});
		})
		
		$(".center .register").click(function(){
			$(this).addClass("disable");
			$that = $(this);
			
			if (that.validateForm().form()) {
				var mobile = $(".formarea input[name='mobile']").val();
				var verifyMoblieMessage = $(".formarea input[name='verifyMoblieMessage']").val();
				var password = $(".formarea input[name='password']").val();
				var surePassword = $(".formarea input[name='surePassword']").val();
				var data={
					mobile:mobile,
					verifyMoblieMessage:verifyMoblieMessage,
					password:password,
					surePassword:surePassword
				};
                $.ajax({
                	url:WEB_ROOT+'/merchant/register',
                	type:'post',
                	data:data,
                    dataType:'json'           	
                }).done(function(res){
                	var callback = null;
                	if(res.code == 0){
                		// 如果注册成功重新登录
                		callback= function(){
                			window.location.href = WEB_ROOT;              	    
                		};
                	}else{
                		callback = function(){
                			$that.removeClass("disable");
                		};
                	};
                	common_ops.alert(res.msg,callback);  	
                }).fail(function(res){
                	$that.removeClass("disable");
                });
			}else{
				$(this).removeClass("disable");
			}
		});
	}

};
$(function() {
	merchant_register_ops.init();

});