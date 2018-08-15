$(function () {
	
	var title = common_ops.g_getQueryString("title");
	console.log(title);
	// 直接根据选择的菜单进行激活
	$(".app-first-sidebar a").siblings().removeClass('active');
	$(".left-sidebar-second a[name='"+title+"']").addClass("active");
	$(".left-sidebar-second a[name='"+title+"']").parent().parent().addClass("block");
	var a = $(".left-sidebar-second a[name='"+title+"']").parent().parent();
	var index = $(".left-sidebar-second").index(a);
	if(index == null || index == -1 ){
		index = 0;
	}
	$(".sidebar-nav .sidebar-nav-link").eq(index).find("a").addClass("active");
	var $sidebar_item = $(".left-sidebar-second a[name='"+title+"']");
	if($sidebar_item.html() == '' ||  $sidebar_item.html()==null){
		 $sidebar_item.parent().parent().hide();
		$(".tpl-content-wrapper").addClass("no-sidebar-second");
	}
	// 刷新按钮
    $('.refresh-button').click(function () {
        window.location.reload();
    });
    
    /**
     * 点击侧边开关 (一级)
     */
    $('.switch-button').on('click', function () {
        var header = $('.tpl-header'), wrapper = $('.tpl-content-wrapper'), leftSidebar = $('.left-sidebar');
        if (leftSidebar.css('left') !== "0px") {
            header.removeClass('active') && wrapper.removeClass('active') && leftSidebar.css('left', 0);
        } else {
            header.addClass('active') && wrapper.addClass('active') && leftSidebar.css('left', -280);
        }
    });
    
    /**
     * 侧边栏开关 (二级)
     */
    $('.sidebar-nav-sub-title').click(function () {
        $(this).toggleClass('active');
    });


	
	
})