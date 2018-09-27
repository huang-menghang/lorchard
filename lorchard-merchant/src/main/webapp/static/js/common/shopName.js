var shop_name_ops = {
	init:function(){
		this.inintComponent();
	},
	inintComponent:function(){
		//取得运营人信息
		$.ajax({
			url:WEB_ROOT+'/shop/operatorName',
			type:'GET',
			dataType:'json'
			}).then(
					function(res) {
						if (res.code == 0) {
							$(".shopName").html(res.data.operatorName);
							$(".header-name").html(res.data.operatorName);
						};
						
						}).fail(function(res) {
                console.log(res.msg);
            });
		
	}
}
$(function(){
	shop_name_ops.init();
});
			