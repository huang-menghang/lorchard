;
var merchant_register_ops = {
	init : function() {
		this.eventBind();
	},
	validateForm:function(){
		return $(".formarea").validate();
	},
	
	eventBind : function() {
		var that = this;
		$(".center .login").click(function() {
			window.location.href = WEB_ROOT + '/merchant/login';
		});
		
		$(".center .register").click(function(){
			common_ops.alert("ll");
			$(this).addClass("disable");
			if (that.validateForm().form()) {
				var mobile = $(".formarea input[name='mobile']").val();
				var verifyMoblieMessage = $(".formarea input[name='verifyMoblieMessage']").val();
				var password = $(".formarea input[name='password']").val();
				var surePassword = $(".formarea input[name='surePassword']").val();
				
				console.log("mobile="+mobile);
				console.log("verifyMoblieMessage="+verifyMoblieMessage);
				console.log("password="+password);
				console.log("surePassword="+surePassword);
				
			}else{
				$(this).removeClass("disable");
			}
		});
	}

};
$(function() {
	merchant_register_ops.init();

});