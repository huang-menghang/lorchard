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
		
		
		
		// echarts 初始化
//		var option = {
//			title : {
//				text : '流量趋势'
//			},
//			tooltip : {
//				trigger : 'axis'
//			},
//			legend : {
//				data : [ '浏览量', '访客数', '商品浏览量', '商品访问数' ]
//			},
//			grid : {
//				left : '3%',
//				right : '4%',
//				bottom : '3%',
//				containLabel : true
//			},
//			toolbox : {
//				feature : {
//					saveAsImage : {}
//				}
//			},
//			xAxis : {
//				type : 'category',
//				boundaryGap : false,
//				data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
//			},
//			yAxis : {
//				type : 'value'
//			},
//			series : [ {
//				name : '浏览量',
//				type : 'line',
//				stack : '总量',
//				data : [ 11, 132, 101, 134, 90, 230, 210 ]
//			}, {
//				name : '访客数',
//				type : 'line',
//				stack : '总量',
//				data : [ 11, 182, 191, 234, 290, 330, 310 ]
//			}, {
//				name : '商品浏览量',
//				type : 'line',
//				stack : '总量',
//				data : [ 150, 232, 201, 154, 190, 330, 410 ]
//			}, {
//				name : '商品访问数',
//				type : 'line',
//				stack : '总量',
//				data : [ 320, 332, 301, 334, 390, 330, 320 ]
//			},
//
//			]
//		};
//
//		var charts = echarts.init(document.getElementById('board-diagram'));
//		charts.setOption(option);
	},
	eventBind : function() {
		$(".shop-content .realse-goods").click(function(e) {
			common_ops.alert('发布商品');
		});

		$(".shop-content .access-shop").click(function(e) {
			common_ops.alert('访问商家');
		});

	}
};
$(function() {
	shop_index_ops.init();
});
