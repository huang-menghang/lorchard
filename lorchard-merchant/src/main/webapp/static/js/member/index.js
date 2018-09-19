;
var member_index_ops ={
		init:function(){
			this.inintComponent();
		},
		// 初始化控件,一数据的异步获取
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
						 {field: 'id', title: 'ID',align: 'center', width:'10%'},
						    {field: 'nicknameStr', title: '昵称', align: 'center',width:'15%'},
						    {field: 'gender', title: '性别',templet: '#gender' ,align: 'center', width:'10%'},
							{field: 'mobile',title: '手机号',align: 'center',width:'15%'},
							{field: 'province',title: '省',templet: '#province_ch',align: 'center',width:'12%'},
						    {field: 'city',title: '市',templet: '#city_ch',align: 'center',width:'12%'},
						    {field: 'createTime',title: '创建时间',templet: '#date_formate',align: 'center',width:'15%'},
							{fixed: 'right', title: '操作', width: '20%',height: 40, align: 'center', templet: '#barOption'}
						   ]],
					  url: WEB_ROOT + "/member/pagination",
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
				 
				 
				 $(".btn-serach").click(function(){
					 
					 var startTime = $("input[ name='startTime']").val();
				     var endTime = $("input[ name='endTime']").val();
					 
				     var nicknameStr = $("input[ name='nicknameStr']").val();
				     var mobile = $("input[ name='mobile']").val();
				     
				     tableIns.reload({
							where: { //设定异步数据接口的额外参数，任意设
								nicknameStr: nicknameStr,
								mobile: mobile,
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
				  
				  
				  table.on('tool(table-data)', function(obj) {
						var data = obj.data;
						var id = data.id;
						if(obj.event == 'detail'){
							//查看用户详情
							window.location.href = WEB_ROOT + '/member/info?id='+id+'&title=member';
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
		}
}


$(function(){
	member_index_ops.init();
})