;
var member_info_ops ={
		init:function(){
			this.inintComponent();
		},
		// 初始化控件,一数据的异步获取
		inintComponent:function(){
			var id = common_ops.g_getQueryString("id");
			$.ajax({
				url:WEB_ROOT +'/member/'+id,
				type:'GET',
				dataType:'json',
				success:function(res){
					//完善数据
					var member = res.data;
					$('#meber-avatar').attr('src',member.avatar);
					$('.layui-form input[name="nicknameStr"]').val(member.nicknameStr);
					var gender =  member.gender == 0 ? '男':'女';
					$('.layui-form input[name="gender"]').val(gender);
					$('.layui-form input[name="mobile"]').val(member.mobile);
					var province = getCityName_ops.getCityName(member.province.toLowerCase());
					$('.layui-form input[name="province"]').val(province);
					var city = getCityName_ops.getCityName(member.city.toLowerCase());
					$('.layui-form input[name="city"]').val(city);
					var createTime = new Date(member.createTime).format("yyyy-MM-dd hh:mm:ss");
					$('.layui-form input[name="createTime"]').val(createTime);
				}
			});
			
			
		}	
		
}; 

$(function(){
	member_info_ops.init();
})