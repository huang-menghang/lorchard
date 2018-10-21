<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<style>
.laytable-cell-1-imagePath {
	height: auto;
}

td .layui-table-cell {
	height: auto;
}

.laytable-cell-1-8 {
	height: auto;
}
.layui-form-item{
margin-bottom:0%;
width:130%;
margin-left:-1%;
}
dl {
margin-top:3%;
}
</style>
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />

	<div class="tpl-content-wrapper">
		<div style="margin-top: 20px"
			class="layui-tab layui-tab-brief main-tab-container">
			<ul class="layui-tab-title main-tab-title">
				<li><a
					href="<%=basePath%>/order?title=orderAll">订单列表</a></li>
				<li class="layui-this"><a
					href="<%=basePath%>/order/groupOrderAll?title=groupOrderAll">拼团活动订单</a></li>
				<div class="main-tab-item layui-bg-blue">活动订单</div>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-form xbs" align="center">
					<div class="layui-form-pane">
						<div class="layui-form-item">
							<label class="layui-form-label " style="width: 10%">拼团创建时间</label>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="开始时间" name="startTime">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="结束时间" name="endTime">
							</div>
							<div class="layui-input-inline " style="width: 10%">
								<input type="text" name="inviteId" value=""
									placeholder="拼团创建人id" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 10%">
								<input type="text" name="" value=""
									placeholder="商品名称" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 4%">
								<button class="layui-btn btn-search" lay-submit=""
									lay-filter="search" style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe615;</i>搜索
								</button>

							</div>

							<div class="layui-input-inline"
								style="width: 4%; margin-left: 2%">
								<button class="layui-btn layui-btn-warm btn-reset"
									style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe666;</i>重置
								</button>
							</div>
				
						</div>
						
							
						</div>
					</div>
				</div>
				<!-- 表格 -->
				<div id="orderTable" lay-filter="table-order"></div>

			</div>
		</div>



	<jsp:include page="/context/js-tags.jsp" />
	<script id="barOption" type="text/html">
    {{#
    var barOption = "<a class='layui-btn layui-btn-small layui-btn-warm look_btn' title='查看' lay-event='info'><i class='layui-icon'>&#xe615;</i></a>";
	barOption += "<a class='layui-btn layui-btn-danger layui-btn-small' title='拼团取消' lay-event='delete'><i class='layui-icon'>&#xe640;</i></a>";

	return barOption;

    }} 
    </script>
	<script type="text/html" id="date_formate">
    {{#  
     console.log("dateTime--->"+d.createTime);
     return new Date(d.createTime).format("yyyy-MM-dd hh:mm:ss");
     return new Date(d.updateTime).format("yyyy-MM-dd hh:mm:ss");

    }} 
    </script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/order/groupOrder.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery/3.2.1/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/sock/sockjs.min.js"></script>
</body>
</html>