var shop_index_ops = {
	// 初始化参数
	init : function() {
		this.inintComponent();
		this.eventBind();
	},
	// 初始化组件
	inintComponent : function() {
//		//营业打烊管理
		layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
//			laydate = layui.laydate;// 日期插件
//			laypage = layui.laypage;// 分页
//			layer = layui.layer;// 弹出层
	        layer.msg('欢迎您使用小果园后台系统！');
//			 // 日期组件
//			 var start = {
//						elem : ".layui-form-item input[name='startTime']",
//						min : '2017-01-01 23:59:59',
//						max : '2099-06-16 23:59:59',
//						format : 'yyyy-MM-dd HH:mm:ss',
//						type : 'datetime',
//						trigger : 'click',
//						zIndex : 99999999,
//						ready : function(data) {
//							start.min = data; // 开始日选好后，重置结束日的最小日期
//						},
//						change : function(value, date, endDate) {
//							start.value = value;
//						}
//					};
//
//			var end = {
//						elem : ".layui-form-item input[name='endTime']",
//						min : '2017-01-01 23:59:59',
//						max : '2099-06-16 23:59:59',
//						format : 'yyyy-MM-dd HH:mm:ss',
//						type : 'datetime',
//						trigger : 'click',
//						zIndex : 99999999,
//						ready : function(data) {
//							end.max = data; // 结束日选好后，重置开始日的最大日期
//						},
//						change : function(value, date, endDate) {
//							end.value = value;
//						}
//				};
//
//			laydate.render(start);
//			laydate.render(end);	
			
		});
		 var businessStauts;
			$.ajax({
	  			url:WEB_ROOT+'/merchant/businessStauts',
	  			type:'GET',
	  			dataType:'json'
	  		}).done(function(res){
	  			console.log("res.data123456"+res.data)
	  			businessStauts=res.data;
	  			if(businessStauts==0){
	  				$("#shopStutas").html("正在营业");
					$("#shopStutas").css("color","#5FB878");
	  				}else{
	  				$("#shopStutas").html("已经打烊");
					$("#shopStutas").css("color","#FF5722");
	  				}
	  		});
		 $("#open").click(function(){
			 //var startTime = $("input[ name='startTime']").val();
		    // var endTime = $("input[ name='endTime']").val();
			 var businessStauts;
			 console.log("res.data789789")
				$.ajax({
		  			url:WEB_ROOT+'/merchant/businessStauts',
		  			type:'GET',
		  			dataType:'json'
		  		}).done(function(res){
		  			console.log("res.data789789"+res.data)
		  			businessStauts=res.data;
		  	
		     $.ajax({
		    	 url:WEB_ROOT+'/merchant/shopOpened',
		  			type:'PUT',
		  			dataType:'json',
					data:{
						businessStauts:businessStauts,
					}
				}).done(function(res){
					$("#shopStutas").html("正在营业");
					$("#shopStutas").css("color","#5FB878");
					console.log(res);
					var msg = res.data;
					if(res.code == 0){
					common_ops.alert(msg);
					
					}else{
					common_ops.alert(res.msg);
					}
					
				});
		  	  });
		 });
		 
		 $("#close").click(function(){
			 //console.log("进来了");
			 //var startTime = $("input[ name='startTime']").val();
		   // var endTime = $("input[ name='endTime']").val();
			 var businessStauts;
				$.ajax({
		  			url:WEB_ROOT+'/merchant/businessStauts',
		  			type:'GET',
		  			dataType:'json'
		  		}).done(function(res){
		  			console.log("res.data"+res.data)
		  			businessStauts=res.data;
		  	
		     $.ajax({
		    	 url:WEB_ROOT+'/merchant/shopClosed',
		  			type:'PUT',
		  			dataType:'json',
					data:{
						businessStauts:businessStauts,
					}
				}).done(function(res){
					$("#shopStutas").html("已经打烊");
					$("#shopStutas").css("color","#FF5722");
					console.log(res);
					var msg = res.data;
					if(res.code == 0){
					common_ops.alert(msg);
					
					}else{
					common_ops.alert(res.msg);
					}
					
				});
		  	  });
		 });
		
		
		// 初始化流量统计图
		$.ajax({
			url : WEB_ROOT + '/shop/dailyShopFlow',
			type : 'GET',
			dataType : 'json'
		}).then(
				function(res) {
					console.log(res.data);
					if (res.code == 0) {
						echarts_ops.four_line('流量趋势', [ 'pageView', 'visitorNumber', 'goodsView', 'goodsAceessNumber' ], [
								'浏览量', '访客数', '商品浏览量', '商品访问数' ], res.data,
								'board-diagram');
					}
				}).fail(function(res) {

		});
		
		// 初始化dashboard
		$.ajax({
			url : WEB_ROOT + '/shop/dashboard',
			type : 'GET',
			dataType : 'json'
		}).then(
				function(res) {
					if (res.code == 0) {
						if(res.data!=null){
							$(".header-name").html(res.data.merchantName);
							$(".yesterday-pageView").html(res.data.pageView);
							$(".yesterday-visitorNumber").html(res.data.visitorNumber);
							$(".yesterday-goodsView").html(res.data.goodsView);
							$(".yesterday-goodsAceessNumber").html(res.data.goodsAceessNumber);						
							$(".goodsCount").html(res.data.goodsCount);						
						}else{
							$(".yesterday-pageView").html(0);
							$(".yesterday-visitorNumber").html(0);
							$(".yesterday-goodsView").html(0);
							$(".yesterday-goodsAceessNumber").html(0);						
							$(".goodsCount").html(0);
						}
					};	
				}).fail(function(res) {
                      console.log(res.msg);
		             });
		
		
		
		// 获取当前订单状态数据，放到待办事项
		$.ajax({
			url:WEB_ROOT+'/shop/statistics',
			type:'GET',
			dataType:'json'
		}).then(
			function(res){
				if(res.code==0){
				$("#orderUnDelivered").html(res.data.orderUnDeliveredCount);//待发货
				$("#orderUnReceived").html(res.data.orderUnReceivedCount);//待收货
				$("#orderRefund").html(res.data.orderRefundCount);//退款中
				$("#orderUnpaid").html(res.data.orderUnpaidCount);//待支付订单
				};
			}).fail(function(res){
			});

	},
	eventBind : function() {
		// 发布新产品
		$(".shop-content .realse-goods").click(function(e) {
			window.location.href = WEB_ROOT+'/goods/set?title=goodsIndex&ops=add';
		});

		

	}
};
$(function() {
	shop_index_ops.init();
});


