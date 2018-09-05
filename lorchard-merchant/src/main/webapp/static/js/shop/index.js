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
					    $(".header-name").html(res.data.merchantName);
					    $(".yesterday-pageView").html(res.data.pageView);
					    $(".yesterday-visitorNumber").html(res.data.visitorNumber);
					    $(".yesterday-goodsView").html(res.data.goodsView);
					    $(".yesterday-goodsAceessNumber").html(res.data.goodsAceessNumber);						
					    $(".goodsCount").html(res.data.goodsCount);						
					};	
				}).fail(function(res) {
                      console.log(res.msg);
		             });
		

	},
	eventBind : function() {
		// 发布新产品
		$(".shop-content .realse-goods").click(function(e) {
			window.location.href = WEB_ROOT+'/goods/set?title=goods&ops=add';
		});

		

	}
};
$(function() {
	shop_index_ops.init();
});
