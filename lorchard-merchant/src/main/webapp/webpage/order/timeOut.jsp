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
width:111%;
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
				<li class="layui-this"><a
					href="<%=basePath%>/order?title=orderAll">订单列表</a></li>
				<div class="main-tab-item layui-bg-blue">订单管理</div>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-form xbs" align="center">
					<div class="layui-form-pane">
						<div class="layui-form-item">
							<label class="layui-form-label " style="width: 12%">取货方式</label>
							<div class="layui-input-inline" style="width: 202px; text-align: center;">
									<select name="sendMethod" lay-filter="reportType">
										<option></option>
										<option value="0">送货上门</option>
										<option value="1">到店取货</option>
									</select>
							</div>
							<div class="layui-input-inline " style="width: 20%">
								<input type="text" name="orderNo" value="" placeholder="订单号"
									autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 20%">
								<input type="text" name="orderMemberName" value=""
									placeholder="收货人姓名" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 5%">
								<button class="layui-btn btn-serach" lay-submit=""
									lay-filter="sreach" style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe615;</i>搜索
								</button>

							</div>

							<div class="layui-input-inline"
								style="width: 5%; margin-left: 3%">
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
    barOption += "<a class='layui-btn layui-btn-normal layui-btn-small' title='确认完成订单' lay-event='complete'><i class='layui-icon'>&#xe605;</i></a>";
	return barOption;

    }} 
    </script>
	<script type="text/html" id="date_formate">
    {{#  
     console.log("dateTime--->"+d.payTime);
     return new Date(d.payTime).format("yyyy-MM-dd hh:mm:ss");
    }} 
    </script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/order/timeOut.js"></script>

</body>
</html>