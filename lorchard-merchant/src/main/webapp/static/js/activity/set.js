var activity_set_ops = {
	init : function() {
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
	
	inintComponent : function() {
		var that = this;
		var ops = common_ops.g_getQueryString("ops");
		console.log("ops--->" + ops);
		var merchantId;
		$.ajax({
  			url:WEB_ROOT+'/goods/merchantId',
  			type:'GET',
  			dataType:'json'
  		}).done(function(res){
  			merchantId=res.data;
		
		layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
			laydate = layui.laydate;// 日期插件
			laypage = layui.laypage;// 分页
			layer = layui.layer;// 弹出层
			table = layui.table;
			var tableIns = table.render({
				 // 设置table组件控制的元素
				 elem: '#activityTable',
				 cols: [[                  //标题栏
					 {type: 'checkbox', fixed: 'left'},
					 {field: 'id',title: '商品id',align: 'center',width:'12%'},
					 {field: 'name', title: '名称',align: 'center',width:'20%'},
					 {field: 'parentCategoryName', title: '类别',align: 'center', width:'20%'},
					 {field: 'description', title: '描述',align: 'center', width:'20%'},
					 {field: 'sales',title: '销量',align: 'center',width:'12%',sort:true},
					 {field: 'createTime',title: '创建时间',templet: '#date_formate',align: 'center',width:'15%'},
					 ]],
					 id:"dataCheck",
				  url: WEB_ROOT + "/goods/pagination",
				  method: 'get',
				  where:{merchantId:merchantId},
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
				  }
			 
				  
			 });
			
				$(".layui-input-block button[lay-filter='activity']").html("立即添加");
				$(".layui-tab-title .ops-title").html("新建活动");			
			$(".layui-input-block .layui-btn").click(function(event) {
				$that = $(this);
				// 在点击事件之间需要讲按钮置灰
				$that.addClass('layui-btn-disabled');
				$that.attr("disabled","true");
				if (that.validateForm().form()) {
					var activityName=$('.layui-form input[name="activityName"]').val();
					var description=$('.layui-form textarea[name="description"]').val();
					var activityType=$('.layui-form select[name="activityType"]').val();
					var totalNumber=$('.layui-form input[name="totalNumber"]').val();
					var spellingGroupPrice=$('.layui-form input[name="spellingGroupPrice"]').val();
					var startTime = $("input[ name='startTime']").val();
					var endTime = $("input[ name='endTime']").val();
					console.log("activityName"+activityName);
					console.log("description"+description);
					console.log("activityType"+activityType);
					console.log("totalNumber"+totalNumber);
					console.log("startTime"+startTime);
					console.log("endTime"+endTime);
					var goodsId=[];
					var checkData=table.checkStatus('dataCheck').data;
					$.each(checkData,function(i,v){
						goodsId.push(v.id);
					});
					if(goodsId.length==0){
						common_ops.alert("请选择要拼团的商品");
						return;
					}
					console.log(goodsId);
					
					
			  			$.ajax({
							   url:WEB_ROOT+'/activity/add',
							   type:'POST',
							   traditional: true,
							   data:{
								   activityName:activityName,
								   description:description,
								   activityType:activityType,
								   totalNumber:totalNumber,
								   startTime:startTime,
								   endTime:endTime,
								   goodsId:goodsId
							   },
							   dataType:'json'
				  			}).done(function(res){
				  				   var callback=function(){
									   $that.removeClass('layui-btn-disabled');
									   $that.removeAttr("disabled");
								   };
								   var msg = res.msg;
								   if(res.code == 0){
									   $.ajax({
										   url:WEB_ROOT+'/activity/update',
										   type:'PUT',
										   data:{
											   totalNumber:totalNumber,
											   spellingGroupPrice:spellingGroupPrice,
											   goodsId:goodsId
										   },
										   dataType:'json'
							  			}).done(function(res){
							  				if(res.code==0){
							  					
							  					window.location.href = WEB_ROOT+'/activity?title=activityIndex';
							  				}
							  			})
								   }else{
									   common_ops.alert(msg, callback);
								   }
				  			});
				}else{
					$that.removeClass('layui-btn-disabled');
					$that.removeAttr("disabled");
				}
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
		
		
	}
}
		
$(function() {
	activity_set_ops.init();
});