var finance_index_ops = {
	init:function(){
		this.inintComponent();
	},
	
	// 校验表单
	validateForm : function() {
		return $(".formarea").validate();
	},
	renderFrom:function(){
		 layui.use('form', function(){
			   var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
			   form.render();
		 });
	},
	
	inintComponent:function(){
		var that=this;
		
		var orderMerchantId;
		$.ajax({
  			url:WEB_ROOT+'/finance/financeMerchantId',
  			type:'GET',
  			dataType:'json'
  		}).done(function(res){
  			console.log("res.data"+res.data)
  			orderMerchantId=res.data;
  			
  			$.ajax({
				url:WEB_ROOT+'/finance/queryBalance?merchantId='+orderMerchantId,
				type:'get',
				dataType:'json'
			}).done(function(res){
					$('.layui-block input[name="balance"]').val(res.data);		
			});	
  			
  			layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
  				laydate = layui.laydate;// 日期插件
  				laypage = layui.laypage;// 分页
  				layer = layui.layer;// 弹出层
  				table = layui.table;
  				var tableIns = table.render({
  					 // 设置table组件控制的元素
  					 elem: '#withdrawTable',
  					 cols: [[    //标题栏
  						    {field: 'createTime', title: '时间', templet: '#date_formate',width: '20%',align: 'center'},
  						 	{field: 'name', title: '姓名',align: 'center',width:'13%'},
  						 	{field: 'openBank',title:'开户银行',align:'center',width:'13%'},
  						    {field: 'bankNumber',title:'银行卡号',align:'center',width:'25%'},
  						 	{field: 'cash', title: '提现金额',align: 'center',width:'13%'},
  						    {field: 'balance',title:'余额',align:'center',width:'13%'},
  						   ]],
  					  url: WEB_ROOT + "/finance/cashPagination",
  					  method: 'get',
  					  where:{merchantId:orderMerchantId},
  					  page: true,
  					  limit: 6,
  					  limits :[6],
  					  loading: false,
  					  done:function(res, curr, count){
  						   console.log(res);
  				           //得到当前页码
  				           console.log(curr);
  				           //得到数据总量
  				           console.log(count);   
  	 
  					  }
  				 
  				 });				
  			});
  			
  			$(".layui-input-block .layui-btn").click(function() {
  				$that = $(this);
  				
  				if (that.validateForm().form()) {
  	               var name = $('.layui-form input[name="name"]').val();
  	               var bankNumber = $('.layui-form input[name="bankNumber"]').val();
  	               var openBank = $('.layui-form input[name="openBank"]').val();
  	               var cash = $('.layui-form input[name="cash"]').val();
  	               
  				   $.ajax({
  					   url:WEB_ROOT+'/finance/withdraw',
  					   type:'POST',
  					   data:{
  						   name:name,
  						   bankNumber:bankNumber,
  						   openBank:openBank,
  						   cash:cash,
  						   merchantId:orderMerchantId
  					   },
  					   dataType:'json',
  					   success:function(res){
  						   // code == 0 表示操作成功,回调到提现界面
  						   var callback = null;
  						   var msg = res.msg;
  						   if(res.code == 0){
  							   callback = function(){
  							      window.location.href = WEB_ROOT+'/finance?title=financeReceived';
  							   };
  						   }
  						  common_ops.alert(msg, callback);
  					   }
  				   });			   
  				}
  				
  			});
  		});

	
	}

	
}
$(function(){
	finance_index_ops.init();
})

