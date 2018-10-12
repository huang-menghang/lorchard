var category_index_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var merchantId;
		$.ajax({
  			url:WEB_ROOT+'/activity/merchantId',
  			type:'GET',
  			dataType:'json'
  		}).done(function(res){
  			console.log("res.data"+res.data)
  			merchantId=res.data;
  			
		layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
			laydate = layui.laydate;// 日期插件
			laypage = layui.laypage;// 分页
			layer = layui.layer;// 弹出层
			table = layui.table;
			
			var tableIns = table.render({
				 // 设置table组件控制的元素
				 elem: '#activityTable',
				 size:'lg',
				 cols: [[                  //标题栏
					    {field: 'id', title: 'ID',align: 'center', width:"8%"},
					    {field: 'activityName', title: '名称', align: 'center',width:"11%"},
					    {field: 'activityType', title: '类型',templet: '#activityType',align: 'center', width:"11%"},
						{field: 'startTime',title: '开始时间',templet: '#date_formate',align: 'center',width:"18%"},
						{field: 'endTime',title: '结束时间',templet: '#date_formate',align: 'center',width:"18%"},
					    {field: 'createTime',title: '创建时间',templet: '#date_formate',align: 'center',width:"18%"},
						{fixed: 'right', title: '操作', width:"17%",height:40, align: 'center', templet: '#barOption'}, 
					    ]],
				  url: WEB_ROOT + "/activity/pagination",
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
			
			table.on('tool(table-activity)', function(obj) {
				var data = obj.data;
				var id = data.id;
				var event = obj.event;
				switch (event) {
				case 'info':
					//一级分类查看事件
					window.location.href = WEB_ROOT+'/activity/info?id='+id+'&ops=info&title=activity';
					break;	
                case 'delete':
                	var callback = {
                	   ok:function(){
                			
       					$.ajax({
       						type:'POST',
       						url:WEB_ROOT+'/activity/'+id,
       						data:{
       							_method:'delete'
       						}
       					}).done(function(res){
       						var msg = res.msg;
       						var callback = null;
       						if(res.code == 0){
       							callback = window.location.href = WEB_ROOT+'/activity';
       						}
       						common_ops.alert(msg,callback);
       						
       					});
                	   },
                	   cancel:function(){
                		  
                	   }
                    
                
                    };
                	
                	common_ops.confirm("是否确认删除", callback)
                
                	
                	
    			break;

				default:
					break;
				}
				
				
				
				
			});
			
			
			
			// 搜索按钮
			$(".btn-serach").click(function(){
				 
				 var startTime = $("input[ name='startTime']").val();
			     var endTime = $("input[ name='endTime']").val();
				 
			     var activityName = $("input[ name='activityName']").val();
			     var activityType = $("select[ name='activityType']").val();
			     
			     tableIns.reload({
						where: { //设定异步数据接口的额外参数，任意设
							activityType: activityType,
							activityName: activityName,
							startTime : startTime,
							endTime : endTime
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

