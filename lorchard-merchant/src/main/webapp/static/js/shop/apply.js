var shop_apply_ops ={
   init:function(){
	   this.eventBind();
   },
   validateForm:function(){
	   return $(".formarea").validate();
   },
   eventBind:function(){
	   var that = this;
	   // 点击申请
	   $(".apply").click(function(){		   
		 $that = $(this);
		 $that.addClass("disable");
		 if(that.validateForm().form()){
			var name = $(".formarea input[name='name']").val();
			var detailAddress = $(".formarea input[name='detail-address']").val();
			var description = $(".formarea textarea[name='description']").val();
			var operatorName = $(".formarea input[name='operatorName']").val();
			var mobile = $(".formarea input[name='mobile']").val();
			var qq = $(".formarea input[name='qq']").val();
			var wechatNo = $(".formarea input[name='wechatNo']").val();
			var email = $(".formarea input[name='email']").val();
			// 校验省市区
			var province = $(".formarea input[name='hcity']").val();
			var city = $(".formarea input[name='hproper']").val();
			var town = $(".formarea input[name='harea']").val();
			// 获取省id
			var province_id = $(".formarea input[name='hcity']").attr('data-id');
		    if(city === null || city == undefined ){
		    	common_ops.alert('城市或则区不能为空');
		    }	
	        console.log("in_array-->"+$.inArray(province_id,["1000","1500","5000","11500"]));	 
		    if($.inArray(province_id,["1000","1500","5000","11500"])== -1&&town == undefined){
		    	common_ops.alert('县区不能为空');
		    }
		    
		    $.ajax({
		    	url:WEB_ROOT+'/shop/apply',
		    	data:{
		    		name:name,
		    		detailAddress:detailAddress,
		    		description:description,
		    		operatorName:operatorName,
		    		mobile:mobile,
		    		qq:qq,
		    		wechatNo:wechatNo,
		    		email:email,
		    		province:province,
		    		city:city,
		    		town:town
		    	},
		    	type:'POST',
		        dataType:'json'
		    }).then(function(res){
		    	var callback = null;
		    	if(res.code != 0){
		    		callback = function(){
		    			$that.removeClass("disable");
		    		};
		    	}else{
		    		callback = function(){
		    			// 进入首页界面
		    			window.location.href = WEB_ROOT+'/index';
		    		};
		    	}
		    	common_ops.alert(res.msg, callback);
		    }).fail(function(res){
		    	var callback = function(){
		    		$that.removeClass("disable");
		    	};
		    	console.log(res,callback);
		    });
		 }else{
			 var callback = function(){
			 $that.removeClass("disable");
			 };
			 common_ops.alert("申请失败",callback);
		 }
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


