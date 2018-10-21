var groupOrder_index_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var orderMerchantId;
		$.ajax({
  			url:WEB_ROOT+'/order/orderMerchantId',
  			type:'GET',
  			dataType:'json'
  		}).done(function(res){
  			console.log("res.data"+res.data)
  			orderMerchantId=res.data;
		layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
			laydate = layui.laydate;// 日期插件
			laypage = layui.laypage;// 分页
			layer = layui.layer;// 弹出层
			table = layui.table;
			console.log("orderMerchantId"+orderMerchantId);
			
			var tableIns = table.render({
				 // 设置table组件控制的元素
				 elem: '#orderTable',
				 cols: [[                  //标题栏
					 	{field: 'id',title: '订单id',align: 'center',width:'8%'},
					 	{field: 'inviteId', title: '创建者id',align: 'center',width:'10%'},
					    {field: 'memberId', title: '会员id',align: 'center', width:'12%'},
					    {field: 'goodsName', title: '商品名称',align: 'center', width:'14%'},
					    {field: 'createTime',title: '拼团创建时间',templet: '#date_formate',align: 'center',width:'18%'},
					    {field: 'updateTime',title: '会员加入时间',templet: '#date_formate',align: 'center',width:'18%'},
					    {fixed: 'right', title: '操作', width: '20%',height: 40, align: 'center', templet: '#barOption'} 
					   ]],
				  url: WEB_ROOT + "/order/groupOrder",
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
			           $("[data-field='memberId']").children().each(function(){  
			               if($(this).text()==''){  
			                  $(this).text("会员未加入")  
			               }
			           }); 
				  }
			 
			 });
		
			
			table.on('tool(table-order)', function(obj) {
				var data = obj.data;
				var id = data.id;
				var event = obj.event;
				switch (event) {
                case 'info':
                	window.location.href = WEB_ROOT+'/order/groupInfo?id='+id+'&ops=info&title=groupOrderAll';
					break;
                case 'delete':
                	var callback = {
                 	   ok:function(){
                 			
        					$.ajax({
        						type:'PUT',
        						url:WEB_ROOT+'/order/deleteGroupOrder',
        						data:{
        							id:id
        						}
        					}).done(function(res){
        						var msg = res.msg;
        						var callback = null;
        						if(res.code == 0){
        							callback = window.location.href = WEB_ROOT+'/order/groupOrderAll?title=groupOrderAll';
        						}
        						common_ops.alert(msg,callback);
        						
        					});
                 	   },
                     };
                 	common_ops.confirm("是否确认取消拼团", callback);
                	break;
				default:
					break;
				}
			});
			//跳转到用户超时订单
			$(".btn-timeOut").click(function(){
            	window.location.href = WEB_ROOT+'/order/timeOut?orderMerchantId='+orderMerchantId+'&title=orderUnReceived';
			})
			// 搜索按钮
			$(".btn-search").click(function(){
				 var startTime = $("input[ name='startTime']").val();
			     var endTime = $("input[ name='endTime']").val();
			     var inviteId = $("input[ name='inviteId']").val();
			     console.log(orderMerchantId);
			     tableIns.reload({
						where: { //设定异步数据接口的额外参数，任意设
							startTime:startTime,
							endTime:endTime,
							inviteId:inviteId,
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
			 // 日期组件
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
	},
	
	
}
$(function(){
	groupOrder_index_ops.init();
})




