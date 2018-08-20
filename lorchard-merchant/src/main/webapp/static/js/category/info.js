var category_info_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		var id = common_ops.g_getQueryString('id');
		$.ajax({
			url:WEB_ROOT+'/goodsCategory/'+id,
			type:'get',
			dataType:'json'
		}).done(function(res){
			if(res.code == 0){
			$('.layui-tab-content input[name="name"]').val(res.data.name);
			$('.layui-tab-content input[name="parentCategoryName"]').val(res.data.parentCategoryName);
			if(res.data.imagePath != null){
			$('.layui-tab-content img[name="image"]').attr('src',res.data.imagePath).show();
			}
			$('.layui-tab-content input[name="index"]').val(res.data.index);
			$('.layui-tab-content textarea[name="description"]').val(res.data.description);
			}else{
				common_ops.alert(res.msg);
			}		
		});
		
		
	}	
		
};

$(function(){
	category_info_ops.init();
});