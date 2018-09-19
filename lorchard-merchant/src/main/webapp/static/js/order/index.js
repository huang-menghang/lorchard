var category_index_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var orderMerchantId;
		var theRequest = new Object();
		var orderStatus;
		function GetRequest() {  
			   var url = location.search; //获取url中"?"符后的字串  
			   if (url.indexOf("?") != -1) {  
			      var str = url.substr(1);  
			      strs = str.split("&");  
			      for(var i = 0; i < strs.length; i ++) {  
			         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);  
			      }  
			   }  
			   return theRequest;  
		}  
		GetRequest();
		console.log(theRequest.title);
		switch (theRequest.title) {
        case 'orderAll':
			break;
        case 'orderUnpaid':
        	orderStatus=0;
			break;
        case 'orderUnDelivered':
        	orderStatus=1;
			break;
        case 'orderUnReceived':
        	orderStatus=2;
			break;
        case 'orderRefund':
        	orderStatus=3;
			break;
        case 'orderCompleted':
        	orderStatus=5;
			break;
		}
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
			console.log("orderStatus"+orderStatus);
			console.log("orderMerchantId"+orderMerchantId);
			
			

			
			
			var tableIns = table.render({
				 // 设置table组件控制的元素
				 elem: '#orderTable',
				 cols: [[                  //标题栏
					 	{field: 'orderNo',title: '订单号',align: 'center',width:'12%'},
					 	{field: 'orderMemberName', title: '收货人姓名',align: 'center',width:'12%'},
					    {field: 'sendMethod', title: '取货方式',align: 'center', width:'12%'},
					    {field: 'orderPendingBalance', title: '用户支付金额(元)', width: '14%',align: 'center'},
					    {field: 'mobile', title: '联系方式',align: 'center', width:'15%'},		    
					    {field: 'payTime',title: '支付时间',templet: '#date_formate',align: 'center',width:'14%'},
						{fixed: 'right', title: '操作', width: '24%',height: 40, align: 'center', templet: '#barOption'} 
					   ]],
				  url: WEB_ROOT + "/order/pagination",
				  method: 'get',
				  where:{orderMerchantId:orderMerchantId,orderStatus:orderStatus},
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
			           
			           $("[data-field='sendMethod']").children().each(function(){  
			               if($(this).text()=='0'){  
			                  $(this).text("送货上门")  
			               }else if($(this).text()=='1'){  
			                  $(this).text("到店取货")  
			               }
			           });  
				  }
			 
			 });
		
			
			table.on('tool(table-order)', function(obj) {
				var data = obj.data;
				var id = data.id;
				var event = obj.event;
				var orderStatus=data.orderStatus;
				switch (event) {
                case 'info':
                	window.location.href = WEB_ROOT+'/order/info?id='+id+'&ops=info&title=orderIndex';
					break;
                case 'deliver':
                	var callback = {
                 	   ok:function(){
                 			
        					$.ajax({
        						type:'PUT',
        						url:WEB_ROOT+'/order/'+id,
        						data:{
           							orderStatus:orderStatus,
           							id:id
           						}
        					}).done(function(res){
        						var msg = res.msg;
        						var callback = null;
        						if(res.code == 0){
           							callback = window.location.href = WEB_ROOT+'/order?title='+theRequest.title;
           						}
        						common_ops.alert(msg,callback);
        						
        					});
                 	   },
                     };
                 	common_ops.confirm("是否确认发货", callback)
                 	break;
                case 'cancel':
                	var callback = {
                	   ok:function(){
                			
       					$.ajax({
       						type:'POST',
       						url:WEB_ROOT+'/order/'+id,
       						data:{
       							_method:'delete',
       							orderStatus:orderStatus,
       							id:id
       						}
       					}).done(function(res){
       						var msg = res.msg;
       						var callback = null;
       						if(res.code == 0){
       							callback = window.location.href = WEB_ROOT+'/order?title='+theRequest.title;
       						}
       						common_ops.alert(msg,callback);
       						
       					});
                	   },
                    };
                	common_ops.confirm("是否确认取消订单", callback)
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
			$(".btn-serach").click(function(){
				 var startTime = $("input[ name='startTime']").val();
			     var endTime = $("input[ name='endTime']").val();
			     var orderNo = $("input[ name='orderNo']").val();
			     var orderMemberName = $("input[ name='orderMemberName']").val();
			     var address= $("input[ name='address']").val();
			     var mobile = $("input[ name='mobile']").val();
			     var sendMethod = $("input[ name='sendMethod']").val();
			     tableIns.reload({
						where: { //设定异步数据接口的额外参数，任意设
							startTime:startTime,
							endTime:endTime,
							orderNo:orderNo,
							orderMemberName:orderMemberName,
							address:address,
							mobile:mobile,
							sendMethod:sendMethod,
							orderMerchantId:orderMerchantId,
							orderStatus:orderStatus
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
	category_index_ops.init();
})




