var echarts_ops = {
	// 折线图
	/*
	 * heading:标题(参数中带有"") 
	 * divId:div的id(参数中带有"") 
	 * amountName:图中数据所代表的意义(参数中带有"") 
	 * xAxis:x轴的数据(数组参数) 
	 * counts:y轴的数据(数组参数)
	 */
	line : function(heading, divId, amountName, xAxis, counts) {
		var dayChart = echarts.init(document.getElementById(divId));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : heading
			},
			tooltip : {},
			legend : {
				data : [ amountName ]
			},
			xAxis : {
				data : xAxis
			},
			yAxis : {},
			toolbox : {
				show : true,
				itemSize : 30,
				feature : {
					mark : {
						show : true
					},
					saveAsImage : {
						show : true,
						pixelRatio : 1,
						title : '保存图片',
						type : 'png',
						lang : [ '点击保存' ]
					}
				},
				iconStyle : {
					normal : {
						borderColor : 'rgb(0, 0, 0)'
					}
				}
			},
			series : [ {
				name : amountName,
				type : 'line',
				data : counts
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		dayChart.setOption(option);
	},

	// 柱状图
	/*
	 * heading:标题(参数中带有"") 
	 * divId:div的id(参数中带有"") 
	 * amountName:图中数据所代表的意义(参数中带有"") 
	 * xAxis:x轴的数据(数组参数) 
	 * counts:y轴的数据(数组参数)
	 */
	bar : function(heading, divId, amountName, xAxis, counts) {
		var dayChart = echarts.init(document.getElementById(divId));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : heading
			},
			tooltip : {},
			legend : {
				data : [ amountName ]
			},
			xAxis : {
				data : xAxis
			},
			yAxis : {},
			toolbox : {
				show : true,
				itemSize : 30,
				feature : {
					mark : {
						show : true
					},
					saveAsImage : {
						show : true,
						pixelRatio : 1,
						title : '保存图片',
						type : 'png',
						lang : [ '点击保存' ]
					}
				},
				iconStyle : {
					normal : {
						borderColor : 'rgb(0, 0, 0)'
					}
				}
			},
			series : [ {
				name : amountName,
				type : 'bar',
				data : counts
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		dayChart.setOption(option);
	},
	// 饼图
	/*
	 * heading:标题(参数中带有"") 
	 * divId:div的id(参数中带有"") 
	 * amountName:图中数据所代表的意义(参数中带有"") 
	 * names:统计的名称(数组参数) 
	 * counts:统计的数据(数组参数)
	 */
	pie : function(heading, divId, amountName, names, counts) {

		var data = genData();
		var dayChart = echarts.init(document.getElementById(divId));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : heading
			},
			tooltip : {},
			legend : {
				left: 'left',
				type : 'scroll',
				orient : 'vertical',
				right : 10,
				top : 20,
				bottom : 20,
				data : names
			},
			toolbox : {
				show : true,
				itemSize : 30,
				feature : {
					mark : {
						show : true
					},
					saveAsImage : {
						show : true,
						pixelRatio : 1,
						title : '保存图片',
						type : 'png',
						lang : [ '点击保存' ]
					}
				},
				iconStyle : {
					normal : {
						borderColor : 'rgb(0, 0, 0)'
					}
				}
			},
			series : [ {
				name : amountName,
				type : 'pie',
				data : data.seriesData,
				itemStyle:{
					normal : {
						label : {
							show : false,
							formatter : '{b} : {c} ({d}%)'
						},
						labelLine : {
							show : false
						}
					}
				}
			} ]
		};
		// 使用刚指定的配置项和数据显示图表。
		dayChart.setOption(option);
		// 提取数据
		function genData() {
			var seriesData = [];
			for (var i = 0; i < names.length; i++) {
				seriesData.push({
					name : names[i],
					value : counts[i]
				});
			}
			return {
				seriesData : seriesData
			};
		}
	}
}