var shop_index_ops = {
	// 初始化参数
	init : function() {
		this.inintComponent();
		this.eventBind();
	},
	// 初始化组件
	inintComponent : function() {
		
		
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


