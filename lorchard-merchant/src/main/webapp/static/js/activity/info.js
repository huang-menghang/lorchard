var activity_info_ops = {
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
					 {field: 'id',title: '商品id',align: 'center',width:'12%'},
					 {field: 'name', title: '名称',align: 'center',width:'20%'},
					 {field: 'parentCategoryName', title: '类别',align: 'center', width:'20%'},
					 {field: 'description', title: '描述',align: 'center', width:'20%'},
					 {field: 'sales',title: '销量',align: 'center',width:'12%',sort:true},
					 {field: 'createTime',title: '创建时间',templet: '#date_formate',align: 'center',width:'15%'},
					 {fixed: 'right', title: '操作', width:"17%",height:40, align: 'center', templet: '#barOption'}, 
					 ]],
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
			
				$(".layui-input-block button[lay-filter='activity']").html("查看详情");
				var id = common_ops.g_getQueryString("id");
				$.ajax({
		  			url:WEB_ROOT+'/activity/'+id,
		  			type:'GET',
		  			dataType:'json'
		  		}).done(function(res){
		  			if(res.code == 0){
		  				console.log(res.data);
		  				var startTime= new Date(res.data.startTime).format("yyyy-MM-dd hh:mm:ss");
		  				var endTime= new Date(res.data.endTime).format("yyyy-MM-dd hh:mm:ss");
		  				$('.layui-form input[name="activityName"]').val(res.data.activityName);
		  				$('.layui-form textarea[name="description"]').val(res.data.description);
		  				$('.layui-form select[name="activityType"]').val(res.data.activityType);
		  				$('.layui-form input[name="startTime"]').val(startTime);
		  				$('.layui-form input[name="endTime"]').val(endTime);
		  				that.renderFrom();
		  			}
		  		});
			
			
				table.on('tool(table-activity)', function(obj) {
					var data = obj.data;
					var id = data.id;
					var event = obj.event;
					switch (event) {
					case 'info':
						//一级分类查看事件
						window.location.href = WEB_ROOT+'/goods/info?id='+id+'&ops=info&title=goodsIndex';
						break;	
					default:
						break;
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
	activity_info_ops.init();
});