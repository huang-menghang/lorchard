var info_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var id = common_ops.g_getQueryString('id');
		console.log("id"+id);
		$.ajax({
			url:WEB_ROOT+'/order/groupInfoById',
			type:'get',
			data:{id:id},
			dataType:'json'
		}).done(function(res){
			if(res.code == 0){
				console.log(res.data)
				$('.layui-tab-content input[name="id"]').val(res.data.id);
				$('.layui-tab-content input[name="inviteId"]').val(res.data.inviteId);
				var memberId;
		        if(res.data.memberId==null){  
		            memberId='会员未加入';  
		        }
		        $('.layui-tab-content input[name="memberId"]').val(res.data.memberId);
				var createTime=new Date(res.data.createTime).format("yyyy年MM月dd日 hh:mm");
				$('.layui-tab-content input[name="createTime"]').val(createTime);
				layui.use([ 'table', 'layer'],function(){
					layer = layui.layer;// 弹出层
					table = layui.table;
					var tableIns = table.render({
					 // 设置table组件控制的元素
						elem: '#orderTable',
						 cols: [[                  //标题栏
							 	{field: 'orderNo',title: '订单号',align: 'center',width:'26%'},
							 	{field: 'orderMemberName', title: '收货人姓名',align: 'center',width:'20%'},
							    {field: 'sendMethod', title: '取货方式',align: 'center', width:'15%'},
							    {field: 'orderPendingBalance', title: '用户支付金额(元)', width: '24%',align: 'center'},
								{fixed: 'right', title: '操作', width: '15%',height: 40, align: 'center', templet: '#barOption'} 
							   ]],
						url: WEB_ROOT + "/order/groupPagination",
						method: 'get',
						where:{id:id},
						page: true,
						limit: 10,
						limits :[10],
						loading: false,
						done:function(res,curr,count){
								console.log(res);
					           //得到当前页码
					           console.log(curr);
					           //得到数据总量
					           console.log(count);
					           
					           $("[data-field='sendMethod']").children().each(function(){  
					               if($(this).text()=='0'){  
					                  $(this).text("送货上门")  
					               }else if($(this).text()=='1'){  
					                  $(this).text("到店取货")  
					               }
					           });  
						}
					});
					//tableIns结束
					table.on('tool(order-data)', function(obj) {
						var data = obj.data;
						var id = data.id;
						var event = obj.event;
						console.log("goodsId"+goodsId);
						switch (event) {
		                case 'info':
		                	window.location.href = WEB_ROOT+'/order/info?id='+id+'&ops=info&title=orderAll';
							break;
						}
					});
				});
				//layui.use结束
			}
			//if结束
		});
		//ajax.done结束	
	}
	//inintComponent结束
};
//info_ops结束
$(function(){
	info_ops.init();
});