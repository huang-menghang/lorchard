var category_index_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		layui.use([ 'table', 'layer', 'laydate', 'laypage' ],function() {
			laydate = layui.laydate;// 日期插件
			laypage = layui.laypage;// 分页
			layer = layui.layer;// 弹出层
			table = layui.table;
			
			var tableIns = table.render({
				 // 设置table组件控制的元素
				 elem: '#dateTable',
				 cols: [[                  //标题栏
					    {field: 'id', title: 'ID',align: 'center', width:100},
					    {field: 'name', title: '名称', align: 'center',width:100},
					    {field: 'description', title: '描述',align: 'center', width:170},
						{field: 'level',title: '等级',align: 'center',width:100},
						{field: 'index',title: '优先级',align: 'center',width:100},
					    {field: 'createTime',title: '创建时间',templet: '#date_formate',align: 'center',width:140},
						{fixed: 'right', title: '操作', width: 257,height: 40, align: 'center', templet: '#barOption'} 
					   ]],
				  url: WEB_ROOT + "/goodsCategory/pagination",
				  method: 'get',
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
			
			table.on('tool(table-data)', function(obj) {
				var data = obj.data;
				var id = data.id;
				var event = obj.event;
				switch (event) {
				case 'edit':
					//一级分类编辑事件
					window.location.href = WEB_ROOT+'/goodsCategory/set?id='+id+'&ops=edit&title=goodsCategory';
					break;
                case 'info':
                	window.location.href = WEB_ROOT+'/goodsCategory/info?id='+id+'&ops=info&title=goodsCategory';
					break;
				break;	
                case 'delete':
                	var callback = {
                	   ok:function(){
                			
       					$.ajax({
       						type:'POST',
       						url:WEB_ROOT+'/goodsCategory/'+id,
       						data:{
       							_method:'delete'
       						}
       					}).done(function(res){
       						var msg = res.msg;
       						var callback = null;
       						if(res.code == 0){
       							callback = window.location.href = WEB_ROOT+'/goodsCategory';
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
				 
			     var name = $("input[ name='name']").val();
			     var description = $("input[ name='description']").val();
			     
			     tableIns.reload({
						where: { //设定异步数据接口的额外参数，任意设
							description: description,
							name: name,
							startTime : startTime,
							endTime : endTime
						}
						,page: {
						  curr: 1 //重新从第 1 页开始
						}
				});
			     
			 });
			
			 $(".btn-add").click(function(){
				 window.location.href = WEB_ROOT+'/goodsCategory/set?title=goodsCategory&ops=add';
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
	},
	
	
}
$(function(){
	category_index_ops.init();
})

