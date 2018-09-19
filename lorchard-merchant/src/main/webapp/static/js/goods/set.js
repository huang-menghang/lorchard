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
				multiple: true,
				before: function(obj){
				      //预读本地文件示例，不支持ie8
				    	  layer.msg('图片上传中...', {
			                    icon: 16,
			                    shade: 0.01,
			                    time: 0
			                })			            		
           },
				done : function(res) {
					layer.close(layer.msg());
					$("input[name='previewImagePath']").val(res.data);
					$('#uploader-list').append('<div id="" class="file-iteme">'+'<div class="handle"><i class="layui-icon">&#xe640;</i></div>'+'<img alt="" border="none" style="width: 100px;height: 100px;" src='+ res.data +'>'+'</div>')
			        for (var i = 0; i < res.data.length; i++) {
				    $("img[name='previewImagePath"+ res.data[i]+"']").attr('src', res.data[i]);
			            }
			        $('img').show();
					 layer.close(layer.msg());//关闭上传提示窗口
		                //上传完毕		                
				}
			});
			        $(document).on("mouseenter mouseleave",".file-iteme",function(event) {
						if (event.type === "mouseenter") {
							// 鼠标悬浮
							$(this).children(".handle")
									.fadeIn("slow");
						} else if (event.type === "mouseleave") {
							// 鼠标离开
							$(this).children(".handle")
									.hide();
						       }
					        });
	                 // 删除图片
	                 $(document).on("click", ".file-iteme .handle",function(event) {
				            $(this).parent().remove();
			     });

		      });
		//添加商品
		if (ops == "add") {
			// 如果是添加则需要拿到所有的一级分类
			$(".layui-input-block button[lay-filter='goods_edit']").html("立即添加");
			$(".layui-tab-title .ops-title").html("商品添加");
			$.ajax({
				url : WEB_ROOT + "/goods/category",
				type : 'GET',
				dataType : 'json',
				success : function(res) {
					console.log(res);
					$.each(res.data, function(i, v) {
						$("select[name='parentId']").append(
								"<option value=" + v.id + ">" + v.parentCategoryName
										+ "</option>");
					});
					
					that.renderFrom();
				}
			});
			

		}
		//修改商品
		if (ops == "edit") {
			$(".layui-input-block button[lay-filter='goods_edit']").html("立即修改");
			$(".layui-tab-title .ops-title").html("商品修改");
	  		var id = common_ops.g_getQueryString("id");
	  		var categoryId = null;
	  		var recommend=null;
	  		$.ajax({
	  			url:WEB_ROOT+'/goods/'+id,
	  			type:'GET',
	  			dataType:'json'
	  		}).done(function(res){
	  			if(res.code == 0){
	  				//将从后台获取的值赋给jsp
	  				console.log(res.data.previewImages);
	  				$('.layui-tab-content input[name="name"]').val(res.data.name);
	  				$('.layui-tab-content textarea[name="description"]').val(res.data.description);
	  				$.each(res.data.previewImages, function(i,v) {
	  					$("#uploader-list").append('<div id="" class="file-iteme">'+'<div class="handle"><i class="layui-icon">&#xe640;</i></div>'+'<img alt="" border="none" style="width: 100px;height: 100px;" src='+ v.previewImagePath +'>'+'</div>');

	  				});
	  				that.renderFrom();
	  				$('.layui-tab-content select[name="type"]').val(res.data.type);
	  				$('.layui-tab-content input[name="originalPrice"]').val(res.data.originalPrice);
	  				$('.layui-tab-content input[name="minPrice"]').val(res.data.minPrice);
	  				$('.layui-tab-content textarea[name="specificationsDescription"]').val(res.data.specificationsDescription);
	  				$('.layui-tab-content input[name="stock"]').val(res.data.stock);
	  				$('.layui-tab-content select[name="recommend"]').val(res.data.recommend);
	  				parentId=res.data.parentId;
	  				recommend=res.data.recommend;
	  			}else{
	  				common_ops.alert(res.msg);
	  			}		

	  			
	  			//获取顶层分类
	  			$.ajax({
	  				url:WEB_ROOT+'/goods/category',
		  			type:'GET',
		  			dataType:'json'
	  			}).done(function(res){
	  				$.each(res.data, function(i, v) {
	  					console.log(i,v);
						$("select[name='parentId']").append(
								"<option value=" + v.id + ">" + v.parentCategoryName
										+ "</option>");
	  											
					});
	  				$("select[name='parentId']").val(parentId);
	  				that.renderFrom();
	  				
	  			});
	  		});
						
		}
		
		
		
		$(".layui-input-block .layui-btn").click(function(event) {
			$that = $(this);
			// 在点击事件之间需要讲按钮置灰
			$that.addClass('layui-btn-disabled');
			$that.attr("disabled","true");
			if (that.validateForm().form()) {
				//从jsp获取值
			   var id =  common_ops.g_getQueryString("id");
			   var methodType = (ops == "add" ? "POST":"PUT");
               var name = $('.layui-form input[name="name"]').val();
               var description = $('.layui-form textarea[name="description"]').val();
               var type = $('.layui-form select[name="type"]').val();
			   var originalPrice = $('.layui-form input[name="originalPrice"]').val();
			   var minPrice = $('.layui-form input[name="minPrice"]').val();
			   var specificationsDescription = $('.layui-form textarea[name="specificationsDescription"]').val();
			   var stock = $('.layui-form input[name="stock"]').val();
			   var parentId = $('.layui-form select[name="parentId"]').val();
			   var recommend=$('.layui-form select[name="recommend"]').val();
			   
			   //向controller的update传商品数据
			   $.ajax({
				   url:WEB_ROOT+'/goods',
				   type:methodType,
				   traditional: true,
				   data:{
					   id:id,
					   name:name,
					   description:description,
					   type:type,
					   originalPrice:originalPrice,
					   minPrice:minPrice,
					   specificationsDescription:specificationsDescription,
					   stock:stock,
					   parentId:parentId,
					   recommend:recommend
				   },
				   dataType:'json'
	  			}).done(function(res){
	  				   var callback=function(){
						   $that.removeClass('layui-btn-disabled');
						   $that.removeAttr("disabled");
					   };
					   var msg = res.msg;
					   if(res.code == 0){
						   var previewImages=[];
						   var i=0;
						   //从jsp获取轮播图
						   $(".file-iteme img").each(function(){
							   previewImages[i]=new Object();
							   previewImages[i].previewImagePath=$(this).attr("src");
							   previewImages[i].previewImageIndex=i;
							   previewImages[i].goodsId=id;
							   i+=1;
						   });
						   i=0;
						   console.log(previewImages);
						   //向controller的previewImages方法传轮播图数据
						   $.ajax({
							   url:WEB_ROOT+'/goods/previewImages',
							   data:"list="+JSON.stringify(previewImages),
							   dataType: "json",
							   type: "POST"   
						   }).done(function(res){
							   //成功则跳转到index.jsp
							   if(res.code == 0){
								   window.location.href = WEB_ROOT+'/goods?title=goodsIndex';
								   common_ops.alert(msg, callback);
							   }else{
								   common_ops.alert(msg, callback);
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

	}	

};


$(function() {
	category_set_ops.init();
});


