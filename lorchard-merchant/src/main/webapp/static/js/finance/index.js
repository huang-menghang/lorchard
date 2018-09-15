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
				$('.layui-tab-content input[name="balance"]').val(res.data.balance);
				$('.layui-tab-content input[name="totalCommission"]').val(res.data.totalCommission);
				$('.layui-tab-content input[name="totalCash"]').val(res.data.totalCash);
			}else{
				$('.layui-tab-content input[name="balance"]').val("0.00");
				$('.layui-tab-content input[name="totalCommission"]').val("0.00");
				$('.layui-tab-content input[name="totalCash"]').val("0.00");
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
						 	{field: 'id',title: '流水号',align: 'center',width:'15%'},
						 	{field: 'status', title: '流水类型',align: 'center',width:'14%'},
						    {field: 'account', title: '流水金额(元)',align: 'center', width:'18%'},
						    {field: 'balance', title: '余额(元)',align: 'center', width:'15%'},
						    {field: 'createTime', title: '时间', width: '23%',templet: '#date_formate',align: 'center'},
							{fixed: 'right', title: '查看详情', width: '15%',height: 40, align: 'center', templet: '#barOption'} 
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
						                  $(this).text("订单收益")  
						               }else if($(this).text()=='1'){  
						                  $(this).text("资金提现")  
						               }
						           });
							  }
				    	});
				table.on('tool(finance-data)', function(obj) {
					var data = obj.data;
					var event = obj.event;
					switch (event) {
	                case 'info':
	                	window.location.href = WEB_ROOT+'/order/info?id='+data.orderId+'&ops=info&title=orderIndex';						break;
					}
				});
				});
			});
  			});
	     }
       }
$(function(){
	category_index_ops.init();
})

