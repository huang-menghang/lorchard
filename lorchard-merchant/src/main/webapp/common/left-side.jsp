<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>



<header class="tpl-header">
    <!-- 右侧内容 -->
    <div class="tpl-header-fluid">
        <!-- 侧边切换 -->
        <div class="am-fl tpl-header-button switch-button">
            <i class="iconfont">&#xe6a8;</i>
        </div>
        <!-- 刷新页面 -->
        <div class="am-fl tpl-header-button refresh-button">
            <i class="iconfont">&#xe638;</i>
        </div>
        <!-- 其它功能-->
        <div class="am-fr tpl-header-navbar">
            <ul>
                <!-- 欢迎语 -->
                <li class="am-text-sm tpl-header-navbar-welcome">
                    <a href="<?= url('store.user/renew') ?>">欢迎你，<span>老黄</span>
                    </a>
                </li>
                <!-- 退出 -->
                <li class="am-text-sm">
                    <a href="<?= url('passport/logout') ?>">
                        <i class="iconfont">&#xe60b;</i> 退出
                    </a>
                </li>
            </ul>
        </div>
    </div>
</header>

<!--左侧菜单切换-->

<div class="left-sidebar">
	<!-- 一级菜单 -->
	<ul class="sidebar-nav">
		<li class="sidebar-nav-heading">小果源</li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath %>/index" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-home" style=""></i> 首页
		</a></li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath%>/shop?title=shopIndex" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-shop" style=""></i> 店铺管理
		</a></li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath%>/goods?title=goodsIndex" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-goods" style=""></i> 商品管理
		</a></li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath%>/main/order?title=orderAll" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-order" style=""></i> 订单管理
		</a></li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath %>/member?title=member" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-account" style=""></i> 会员管理
		</a></li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath%>/main/goods?title=goodsIndex" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-wxapp"
				style="color: #36b313;"></i> 小程序
		</a></li>
		<li class="sidebar-nav-link"><a href="javascript:void(0);"
			class=""> <svg class="icon sidebar-nav-link-logo"
					aria-hidden="true">
                                <use xlink:href="#icon-application"></use>
                            </svg> 应用中心
		</a></li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath%>/main/goods?title=goodsIndex" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-yunliankeji-" style=""></i> 财务管理
		</a>
		</li>
		<li class="sidebar-nav-link"><a
			href="<%=basePath%>/main/goods?title=goodsIndex" class=""> <i
				class="iconfont sidebar-nav-link-logo icon-setting" style=""></i> 设置
		</a></li>
	</ul>
	
	
	<!-- 子级菜单-->
	<ul class="left-sidebar-second"></ul>
	
	<ul class="left-sidebar-second">
	  <li class="sidebar-second-title">店铺管理</li>
	  <li class="sidebar-second-item">
			<!-- 二级菜单--> 
			<a name="shopIndex" href="<%=basePath%>/shop?title=shopIndex" class="">店铺概述</a> 		
			<a name="shopDecoration" href="<%=basePath%>/shopDecoration?title=shopDecoration" class=""> 店铺装修</a> 		
	  </li>
	</ul>
	
	
	<ul class="left-sidebar-second">
		<li class="sidebar-second-title">商品管理</li>
		<li class="sidebar-second-item">
			<!-- 二级菜单--> 
			<a name="goodsIndex" href="<%=basePath%>/goods?title=goodsIndex" class=""> 商品列表 </a> 		
			<a name="goodsCategory" href="<%=basePath%>/goodsCategory?title=goodsCategory" class=""> 商品分类 </a> 		
		</li>
	</ul>
	
	<ul class="left-sidebar-second">
		<li class="sidebar-second-title">订单管理</li>
		<li class="sidebar-second-item">
			<!-- 二级菜单--> 
			<a name="orderAll" href="<%=basePath%>/main/order?title=orderAll" class=""> 待发货 </a>
			<a name="orderUnpaymenyt" href="<%=basePath%>/main/order?title='orderUnpaymenyt'" class=""> 待发货 </a> 		
			<a name="orderUndelivery" href="<%=basePath%>/main/order?title='orderUndelivery'" class=""> 待收货 </a> 
			<a name="orderUnwaitingorder" href="<%=basePath%>/main/order?title='orderUnwaitingorder'" class=""> 待付款 </a> 		
			<a name="orderWaitingorder" href="<%=basePath%>/main/order?title='orderWaitingorder'" class=""> 已完成  </a> 
			<a name="orderFinished" href="<%=basePath%>/main/order?title='orderFinished'" class=""> 已取消 </a> 		
			<a name="orderClosed" href="<%=basePath%>/main/order?title='orderClosed'" class=""> 全部订单 </a> 		
		</li>
	</ul>
	
	<ul class="left-sidebar-second" >
	  <li><a name="member"></a></li>
	</ul>
	
	<ul class="left-sidebar-second">
		<li class="sidebar-second-title">小程序</li>
		<li class="sidebar-second-item">
			<!-- 二级菜单--> 
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 小程序设置 </a> 		
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 首页设置 </a> 
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 导航设置 </a>
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 帮助中心 </a>		
		</li>
	</ul>
	
	<ul class="left-sidebar-second">
		<li class="sidebar-second-title">设置</li>
		<li class="sidebar-second-item">
			<!-- 二级菜单--> 
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 商城设置 </a> 		
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 交易设置 </a>
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 配送设置 </a> 		
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 短信设置 </a>
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 上传设置 </a> 		
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 清理缓存 </a> 	
			<a href="<%=basePath%>/goods?titile='goodsIndex'" class=""> 环境检测 </a> 	
		</li>
	</ul>
	
	
</div>