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
			$(this).addClass("disable");
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
                		callback=window.location.href = WEB_ROOT+'/merchant/applyMerchant';              	    
                	}else{
                		callback = null;
                	};
                	common_ops.alert(res.msg,callback);
                }).fail(function(res){
                	
                })
				
				
				
				
				
				
				
			}else{
				$(this).removeClass("disable");
			}
		});
	}

};
$(function() {
	merchant_register_ops.init();

});