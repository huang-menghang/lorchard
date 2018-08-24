var shop_apply_ops ={
   init:function(){
	   this.eventBind();
   },
   validateForm:function(){
	   return $(".formarea").validate();
   },
   eventBind:function(){
	   var that = this;
	   $(".apply").click(function(){
		  that.validateForm().form();
	   });
	   // 地址选择组件
	   $("input[name='province']").click(function(e){
		   SelCity(this,e);
	   });
   }
}
$(function(){
   shop_apply_ops.init();	
})


