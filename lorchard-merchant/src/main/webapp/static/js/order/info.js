var info_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var id = common_ops.g_getQueryString('id');
		$(".layui-tab-title .ops-title").html("订单详情");
		$.ajax({
			url:WEB_ROOT+'/order/'+id,
			type:'get',
			dataType:'json'
		}).done(function(res){
			if(res.code == 0){
				$('.layui-tab-content input[name="orderNo"]').val(res.data.orderNo);
				console.log("orderNo----->"+res.data.orderNo);
				$('.layui-tab-content input[name="orderMemberId"]').val(res.data.orderMemberId);
				$('.layui-tab-content input[name="orderMemberName"]').val(res.data.orderMemberName);
				var sendMethod =  res.data.sendMethod == 0 ? '配送':'自取';
				$('.layui-tab-content input[name="sendMethod"]').val(sendMethod);
				$('.layui-tab-content input[name="province"]').val(res.data.province);
				$('.layui-tab-content input[name="city"]').val(res.data.city);
				$('.layui-tab-content input[name="diatrict"]').val(res.data.diatrict);
				$('.layui-tab-content input[name="address"]').val(res.data.address);
				$('.layui-tab-content input[name="mobile"]').val(res.data.mobile);
				$('.layui-tab-content input[name="expressNo"]').val(res.data.expressNo);
				$('.layui-tab-content input[name="orderTotalPrice"]').val(res.data.orderTotalPrice);
				$('.layui-tab-content input[name="orderDiscount"]').val(res.data.orderDiscount);
				$('.layui-tab-content input[name="orderPayPrice"]').val(res.data.orderPayPrice);
				var payTime=new Date(res.data.payTime).format("yyyy年MM月dd日 hh:mm");
				$('.layui-tab-content input[name="payTime"]').val(payTime);
				var confirmTime=new Date(res.data.confirmTime).format("yyyy年MM月dd日 hh:mm");
				$('.layui-tab-content input[name="confirmTime"]').val(confirmTime);
				var orderStatus=res.data.orderStatus;
				switch(orderStatus){
					case 0: orderStatus="待付款";break;
					case 1: orderStatus="待发货";break;
					case 2: orderStatus="待收货";break;
					case 3: orderStatus="退款中";break;
					case 5: orderStatus="已完成";break;
				}
				$('.layui-tab-content input[name="orderStatus"]').val(orderStatus);
				$('.layui-tab-content textarea[name="remark"]').val(res.data.remark);
				$('.layui-tab-content input[name="freightPrice"]').val(res.data.freightPrice);
				$('.layui-tab-content input[name="orderPendingBalance"]').val(res.data.orderPendingBalance);
				$('.layui-tab-content input[name="count"]').val(res.data.count);
				console.log(res.data);
				var orderNo=res.data.orderNo;
				console.log("orderNo"+orderNo);
				var goodsId;
				layui.use([ 'table', 'layer'],function(){
					layer = layui.layer;// 弹出层
					table = layui.table;
					var tableIns = table.render({
					 // 设置table组件控制的元素
						elem: '#orderTable',
						cols: [[                  //标题栏
						 	{field: 'goodsName',title: '商品名',align: 'center',width:'22%'},
						 	{field: 'goodsPrice', title: '商品价格',align: 'center',width:'16%'},
						    {field: 'itemNum', title: '数量',align: 'center', width:'16%'},
						    {field: 'itemPrice', title: '小计',align: 'center', width:'16%'},
						    {field: 'goodsType', title: '商品规格',align: 'center', width:'16%'},
						    {fixed: 'right', title: '查看详情', width:'14%',height: 40, align: 'center', templet: '#barOption'},
						]],
						url: WEB_ROOT + "/order/items",
						method: 'get',
						dataType:'json',
						where:{orderNo:orderNo},
						done:function(res){
							console.log(res.data);
							$(res.data).each(function(){
								console.log(this.goodsId);
								goodsId=this.goodsId;
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
		                	window.location.href = WEB_ROOT+'/goods/info?id='+goodsId+'&ops=info&title=goodsIndex';
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