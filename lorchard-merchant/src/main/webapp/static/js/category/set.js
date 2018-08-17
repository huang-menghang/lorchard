var category_set_ops = {
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
        
		// layui组件
		layui.use('upload', function() {
			var upload = layui.upload;
			upload.render({
				elem : '#uploadImage',
				url : WEB_ROOT + '/image/upload',
				done : function(res) {
					 $("input[name='imagePath']").val(res.data);
					 $('img').show();
					 $('img').attr('src', res.data);
					console.log(res);
					console.log('上传完毕'); // 上传成功返回值，必须为json格式
				}
			});
		});
		
		if (ops == "add") {
			// 如果是添加则需要拿到所有的一级分类
			$(".layui-input-block button[lay-filter='category_add']").html("立即添加");
			$(".layui-tab-title .ops-title").html("商品添加");
			$.ajax({
				url : WEB_ROOT + "/goodsCategory/parent",
				type : 'GET',
				dataType : 'json',
				success : function(res) {
					console.log(res);
					$.each(res.data, function(i, v) {
						$("select[name='parentId']").append(
								"<option value=" + v.id + ">" + v.name
										+ "</option>");
					});
					that.renderFrom();
				}
			});

		}
		if (ops == "edit") {
			$(".layui-input-block button[lay-filter='category_add']").html("立即修改");
			$(".layui-tab-title .ops-title").html("商品修改");
	  		var id = common_ops.g_getQueryString("id");
	  		var parentId = null;
	  		$.ajax({
	  			url:WEB_ROOT+'/goodsCategory/'+id,
	  			type:'GET',
	  			dataType:'json'
	  		}).done(function(res){
	  			if(res.code == 0){
	  				var category = res.data;
	  				$(".layui-tab-content input[name='name']").val(category.name);
	  				if(category.imagePath != null){
	  				$(".layui-tab-content img[name='image']").attr('src',category.imagePath).show();
	  				}
	  				$(".layui-tab-content input[name='imagePath']").val(category.imagePath);
	  				$(".layui-tab-content select[name='index']").val(category.index);
	  				$(".layui-tab-content textarea[name='description']").val(category.description);
	  				parentId = category.parentId;
	  			}else{
	  				common_ops.alert(res.msg);
	  			}
	  			
	  			
	  			$.ajax({
	  				url:WEB_ROOT+'/goodsCategory/parent',
		  			type:'GET',
		  			dataType:'json'
	  			}).done(function(res){
	  				$.each(res.data, function(i, v) {
	  					console.log(i,v);
						$("select[name='parentId']").append(
								"<option value=" + v.id + ">" + v.name
										+ "</option>");
					});
	  				$("select[name='parentId']").val(parentId);
	  				that.renderFrom();
	  			});
	  		});
						
		}

		$(".layui-input-block .layui-btn").click(function() {
			console.log("分类添加");
			$that = $(this);
			// 在点击事件之间需要讲按钮置灰
			$that.addClass('layui-btn-disabled');
			if (that.validateForm().form()) {
			   var id =  common_ops.g_getQueryString("id");
			   var type = (ops == "add" ? "POST":"PUT");
               var name = $('.layui-form input[name="name"]').val();
               var index = $('.layui-form select[name="index"]').val();
               var parntId = $('.layui-form select[name="parentId"]').val();
			   var imagePath = $('.layui-form input[name="imagePath"]').val();
			   var description = $('.layui-form textarea[name="description"]').val();
			   
			   $.ajax({
				   url:WEB_ROOT+'/goodsCategory',
				   type:type,
				   data:{
					   id:id,
					   name:name,
					   parentId:parntId,
					   index:index,
					   imagePath:imagePath,
					   description:description
				   },
				   dataType:'json',
				   success:function(res){
					   // code == 0 表示操作成功,回调到分类列表界面
					   var callback = null;
					   var msg = res.msg;
					   if(res.code == 0){
						   callback = function(){
						      window.location.href = WEB_ROOT+'/goodsCategory?title=goodsCategory';
						   };
					   }else{
						   callback=function(){
							   $that.removeClass('layui-btn-disabled');
						   };
					   }
					  common_ops.alert(msg, callback);
				   }
			   });
			   
			}else{
				$that.removeClass('layui-btn-disabled');
			}
		});

	}

};
$(function() {
	category_set_ops.init();
})