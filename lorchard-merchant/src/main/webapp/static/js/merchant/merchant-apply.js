var merchant_apply_ops = {
	init:function(){
	  this.inintComponent();
	  this.eventBind();
	},	
	inintComponent:function(){
		
	},
	eventBind:function(){
		$('input[name="city"]').click(function(e){
			SelCity(this,e);
		});
	}
};
$(function(){
	merchant_apply_ops.init();
});