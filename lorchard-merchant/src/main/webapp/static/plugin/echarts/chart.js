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
	/**
	 * 多模块折线统计图
	 * 
	 * heading:统计的图的标题
	 * 
	 * legend_data:每一个统计模块的名字
	 * 
	 * xAxis:x轴的数据
	 * 
	 * 
	 * chart_element：统计的挂载点
	 * 
	 * 
	 * 
	 */
	four_line : function(heading, legend_data, xAxis, ySeries,chart_element){

		   var  option = {
		        	title: {
		        		text: heading
		        	},
		        	tooltip: {
		        		trigger: 'axis'
		        	},
		        	legend: {
		        		data:legend_data
		        	},
		        	grid: {
		        		left: '3%',
		        		right: '4%',
		        		bottom: '3%',
		        		containLabel: true
		        	},
		        	// 工具bar
		        	toolbox: {
		        		feature: {
		        			saveAsImage: {}
		        		}
		        	},
		        	// xAxis 为x轴时间数组
		        	xAxis: {
		        		type: 'category',
		        		boundaryGap: false,
		        		data: xAxis
		        	},
		        	yAxis: {
		        		type: 'value'
		        	},
		        	series: [
		        	{
		        		name:'浏览量',
		        		type:'line',
		        		stack: '总量',
		        		data:[120, 132, 101, 134, 90, 230, 210]
		        	},
		        	{
		        		name:'访客数',
		        		type:'line',
		        		stack: '总量',
		        		data:[220, 182, 191, 234, 290, 330, 310]
		        	},
		        	{
		        		name:'商品浏览量',
		        		type:'line',
		        		stack: '总量',
		        		data:[150, 232, 201, 154, 190, 330, 410]
		        	},
		        	{
		        		name:'商品访问数',
		        		type:'line',
		        		stack: '总量',
		        		data:[320, 332, 301, 334, 390, 330, 320]
		        	},
		   
		        	]
		        };
		   var charts = echarts.init(document.getElementById(chart_element));
	       charts.setOption(option);
		   
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