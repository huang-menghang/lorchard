var category_set_ops = {
	init : function() {
		this.inintComponent();
	},
	
	// 校验表单
	validateForm : function() {
		return $(".formarea").validate();
	},
	renderFrom:function(){
		 layui.use('form', function(){
			   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			   form.render();
		 });
	},
	
	inintComponent : function() {	
		var that = this;
		//获取商家id
		var merchantId;
		$.ajax({
  			url:WEB_ROOT+'/set/merchantId',
  			type:'GET',
  			dataType:'json'
  		}).done(function(res){
  			console.log("res.data"+res.data)
  			merchantId=res.data;
	  		$.ajax({
	  			url:WEB_ROOT+'/set/query?merchantId='+merchantId,
	  			type:'GET',
	  			dataType:'json'
	  		}).done(function(res){
	  			if(res.code == 0){
	  				//将从后台获取的值赋给jsp
	  				$('.layui-tab-content input[name="name"]').val(res.data.name);
	  				$('.layui-tab-content input[name="province"]').val(res.data.province);
	  				$('.layui-tab-content input[name="city"]').val(res.data.city);
	  				$('.layui-tab-content input[name="town"]').val(res.data.town);
	  				$('.layui-tab-content input[name="detailAddress"]').val(res.data.detailAddress);
	  				$('.layui-tab-content textarea[name="description"]').val(res.data.description);
	  				$('.layui-tab-content input[name="operatorName"]').val(res.data.operatorName);
	  				$('.layui-tab-content input[name="mobile"]').val(res.data.mobile);
	  				$('.layui-tab-content input[name="qq"]').val(res.data.qq);
	  				$('.layui-tab-content input[name="wechatNo"]').val(res.data.wechatNo);
	  				$('.layui-tab-content input[name="email"]').val(res.data.email);
	  			}else{
	  				common_ops.alert(res.msg);
	  			}		
	  			
	  		});
	  			
  		});
		
		
		$(".layui-input-block .layui-btn").click(function(event) {
			$that = $(this);
			// 在点击事件之间需要讲按钮置灰
			$that.addClass('layui-btn-disabled');
			$that.attr("disabled","true");
			if (that.validateForm().form()) {
			//从jsp获取值
               var name = $('.layui-form input[name="name"]').val();
               var province = $('.layui-form input[name="province"]').val();
			   var city = $('.layui-form input[name="city"]').val();
			   var town=$('.layui-form input[name="town"]').val();
			   var detailAddress = $('.layui-form input[name="detailAddress"]').val();
               var description = $('.layui-form textarea[name="description"]').val();
               var operatorName = $('.layui-form input[name="operatorName"]').val();
               var mobile = $('.layui-form input[name="mobile"]').val();			   
			   var qq = $('.layui-form input[name="qq"]').val();
			   var wechatNo = $('.layui-form input[name="wechatNo"]').val();
			   var email = $('.layui-form input[name="email"]').val();
			   
			   //向controller的update传商铺数据
			   $.ajax({
				   url:WEB_ROOT+'/set/update?merchantId='+merchantId,
				   type:"PUT",
				   traditional: true,
				   data:{
					   name:name,
					   province:province,
					   city:city,
					   town:town,
					   detailAddress:detailAddress,
					   description:description,
					   operatorName:operatorName,
					   mobile:mobile,
					   qq:qq,
					   wechatNo:wechatNo,
					   email:email
				   },
				   dataType:'json'
	  			}).done(function(res){
	  				   var callback=function(){
						   $that.removeClass('layui-btn-disabled');
						   $that.removeAttr("disabled");
					   };
					   var msg = res.msg;
					   if(res.code == 0){
						   window.location.href = WEB_ROOT+'/shop?title=shopIndex';
						   common_ops.alert(msg, callback);
					   }else{
						   common_ops.alert(msg, callback);
					   }
	  			});
		  	
			}else{
				$that.removeClass('layui-btn-disabled');
				$that.removeAttr("disabled");
			}
		});

	}	

};


$(function() {
	category_set_ops.init();
});


