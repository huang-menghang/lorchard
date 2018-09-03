var shop_index_ops = {
	// 初始化参数
	init : function() {
		this.inintComponent();
	},
	// 初始化组件
	inintComponent : function() {
		// echarts 初始化
		var option = {
			title : {
				text : '流量趋势'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '浏览量', '访客数', '商品浏览量', '商品访问数' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '浏览量',
				type : 'line',
				stack : '总量',
				data : [ 120, 132, 101, 134, 90, 230, 210 ]
			}, {
				name : '访客数',
				type : 'line',
				stack : '总量',
				data : [ 220, 182, 191, 234, 290, 330, 310 ]
			}, {
				name : '商品浏览量',
				type : 'line',
				stack : '总量',
				data : [ 150, 232, 201, 154, 190, 330, 410 ]
			}, {
				name : '商品访问数',
				type : 'line',
				stack : '总量',
				data : [ 320, 332, 301, 334, 390, 330, 320 ]
			},

			]
		};

		var charts = echarts.init(document.getElementById('board-diagram'));
		charts.setOption(option);

	}

};
$(function() {
	shop_index_ops.init();
});
