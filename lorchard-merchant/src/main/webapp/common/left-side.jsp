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
                    <a href="#"class="logout">
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
			href="<%=basePath%>/order?title=orderAll" class=""> <i
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
			href="<%=basePath%>/finance?title=financeIndex" class="">
				<i class="iconfont sidebar-nav-link-logo icon-yunliankeji-" style=""></i>
				财务管理
		</a></li>
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
			<a name="orderAll" href="<%=basePath%>/order?title=orderAll" class=""> 全部订单</a>
			<a name="orderUnpaid" href="<%=basePath%>/order?title=orderUnpaid" class="">待付款订单</a> 		
			<a name="orderUnDelivered" href="<%=basePath%>/order?title=orderUnDelivered" class="">待发货订单</a>
			<a name="orderUnReceived" href="<%=basePath%>/order?title=orderUnReceived" class="">待收货订单</a>  
			<a name="orderRefund" href="<%=basePath%>/order?title=orderRefund" class="">退款中订单</a> 		
			<a name="orderCompleted" href="<%=basePath%>/order?title=orderCompleted" class="">已完成订单</a> 		
		</li>
	</ul>
	
	<ul class="left-sidebar-second">
		<li class="sidebar-second-title">资产管理</li>
		<li class="sidebar-second-item">
			<!-- 二级菜单--> 
			<a name="financeIndex" href="<%=basePath%>/finance?title=financeIndex" class=""> 资产中心</a> 
			<a name="financeFlow" href="<%=basePath%>/finance?title=financeFlow" class="">对账单</a>
			<a name="financetWithdrawal" href="<%=basePath%>/finance?title=financeReceived" class="">提现</a> 
		</li>
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