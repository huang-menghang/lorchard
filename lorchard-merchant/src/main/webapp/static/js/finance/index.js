var category_index_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var orderMerchantId;
		$.ajax({
  			url:WEB_ROOT+'/finance/financeMerchantId',
  			type:'GET',
  			dataType:'json'
  		}).done(function(res){
  			orderMerchantId=res.data;
		$.ajax({
			url:WEB_ROOT+'/finance/info',
			type:'get',
			dataType:'json'
		}).done(function(res){
			console.log(res.data);
			if(res.data!=null){
				if(res.data.balance!=null){
					$('.layui-tab-content input[name="balance"]').val(res.data.balance);
					$('.layui-tab-content input[name="totalCommission"]').val(res.data.totalCommission);
				}
				if(res.data.totalCash!=null){
					$('.layui-tab-content input[name="totalCash"]').val(res.data.totalCash);
				}
			}
			layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
				laydate = layui.laydate;// 日期插件
				laypage = layui.laypage;// 分页
				layer = layui.layer;// 弹出层
			    table = layui.table;
				var tableIns = table.render({				
					 // 设置table组件控制的元素
					 elem: '#financeTable',
					 cols: [[                  //标题栏
						 	{field: 'id',title: '流水号',align: 'center',width:'12%'},
						 	{field: 'status', title: '流水类型',align: 'center',width:'19%'},
						    {field: 'account', title: '流水金额(元)',align: 'center', width:'18%'},
						    {field: 'balance', title: '余额(元)',align: 'center', width:'15%'},
						    {field: 'createTime', title: '时间', width: '27%',templet: '#date_formate',align: 'center'},
							{fixed: 'right', title: '查看', width: '12%',height: 40, align: 'center', templet: '#barOption'} 
						   ]],
							  url: WEB_ROOT + "/finance/pagination",
							  method: 'get',
							  where:{orderMerchantId:orderMerchantId},
							  page: true,
							  limit: 10,
							  limits :[10],
							  loading: false,
							  done:function(res, curr, count){
								   console.log(res);
						           //得到当前页码
						           console.log(curr);
						           //得到数据总量
						           console.log(count);
						           $("[data-field='status']").children().each(function(){  
						               if($(this).text()=='0'){  
						                  $(this).text("订单收益+")  
						               }else if($(this).text()=='1'){  
						                  $(this).text("资金提现-")  
						               }
						           });
							  }
				    	});
				
				//查看表单详情
				table.on('tool(finance-data)', function(obj) {
					var data = obj.data;
					var event = obj.event;
					switch (event) {
	                case 'orderInfo':
	                	window.location.href = WEB_ROOT+'/order/info?id='+data.orderId+'&ops=info&title=orderIndex';						break;
					}
				});
				
				//搜索按钮
				$(".btn-serach").click(function(){
					 var startTime = $("input[ name='startTime']").val();
				     var endTime = $("input[ name='endTime']").val();
				     var status = $("select[ name='status']").val();
				     tableIns.reload({
							where: { //设定异步数据接口的额外参数，任意设
								startTime:startTime,
								endTime:endTime,
								status:status,
								orderMerchantId:orderMerchantId
							}
							,page: {
							  curr: 1 //重新从第 1 页开始
							}
					});
				     
				 });
				// 重置表单按钮.使用时class为layui-btn layui-btn-warm btn-reset到的重置按钮
				 $(".btn-reset").click(function() {
						$('input').val('');
						$(".layui-form select option[value='']").attr("selected", true);
				  });
				 var start = {
							elem : ".layui-form-item input[name='startTime']",
							min : '2017-01-01 23:59:59',
							max : '2099-06-16 23:59:59',
							format : 'yyyy-MM-dd HH:mm:ss',
							type : 'datetime',
							trigger : 'click',
							zIndex : 99999999,
							ready : function(data) {
								start.min = data; // 开始日选好后，重置结束日的最小日期
							},
							change : function(value, date, endDate) {
								start.value = value;
							}
						};

				var end = {
							elem : ".layui-form-item input[name='endTime']",
							min : '2017-01-01 23:59:59',
							max : '2099-06-16 23:59:59',
							format : 'yyyy-MM-dd HH:mm:ss',
							type : 'datetime',
							trigger : 'click',
							zIndex : 99999999,
							ready : function(data) {
								end.max = data; // 结束日选好后，重置开始日的最大日期
							},
							change : function(value, date, endDate) {
								end.value = value;
							}
					};

				laydate.render(start);
				laydate.render(end);
				});
			
			
			});
  			});
	     },
       }
$(function(){
	category_index_ops.init();
})

