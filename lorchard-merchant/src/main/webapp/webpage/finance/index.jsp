<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<link rel="stylesheet" href="<%=basePath%>/static/css/finance/index.css">
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />

	<div class="tpl-content-wrapper">
		<div style="margin-top: 20px" class="layui-tab layui-tab-brief main-tab-container">
			<form class="formarea">
			<div class="layui-tab-content">
				<div class="layui-tab layui-tab-card">
			     	<ul class="layui-tab-title main-tab-title">
						<div class="main-tab-item layui-bg-blue">财务一览</div>
					</ul>
				<div class="layui-form">
				<!--店铺信息及资产信息-->
				<div class="layui-col-md9">
					<div class="layui-col-md4" style="margin-left: 5%;">店铺资产信息</div>
				</div>
				<div class="layui-form-item" style="margin: 4%;">
					<div class="layui-col-md10" style="padding-top: 3%;">
						<div class="layui-form-item" style="margin-top: 6%; margin-right: 6%;margin-bottom:-1%;">
							<div class="layui-col-sm4">
								<div class="layui-block">
									<p class="layui-title">
										总计收入：
									</p>
								</div>
								<div class="layui-block">
									<label id="financelabel" style="">￥</label> <input type="text"
									name="balance" readonly="readonly" id="financeInput" value="0.00">
								</div>
							</div>
							<div class="layui-col-sm4">
								<div class="layui-block">
								<p class="layui-title">
									平台维护费： 
								</p>
								</div>
								<div class="layui-block">
								<label id="financelabel" style="">￥</label> <input type="text"
									name="totalCommission" readonly="readonly" id="financeInput" value="0.00">
								</div>
							</div>
							<div class="layui-col-sm4">
								<div class="layui-block">
								<p class="layui-title">提现金额：</p>
								</div>
								<div class="layui-block">
								<label id="financelabel" style="">￥</label> <input type="text"
									name="totalCash" readonly="readonly" id="financeInput" value="0.00">
								</div>
							</div>
						</div>
					</div>
				</div>

			<!--交易记录-->
				<div class="layui-col-md9" style="margin-bottom:4%;">
					<div class="layui-col-md4" style="margin-left: 5%;">流水信息</div>
				</div>
				<div class="layui-form-item"  style="margin-left: 7%;width:77%;">
							<label class="layui-form-label" style="width: 10%" id="tableLable">时间</label>
							<div class="layui-input-inline " style="width: 20%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="开始时间" name="startTime">
							</div>
							<div class="layui-input-inline " style="width: 20%;margin-left:-1%;">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="结束时间" name="endTime">
							</div>
				            <label class="layui-form-label" style="width: 9%;margin-left:-1%"; id="tableLable">类型</label>
							<div class="layui-input-inline" style="width: 25%; text-align: center;">
									<select name="status" lay-filter="reportType">
										<option></option>
										<option value="0">订单新增</option>
										<option value="1">资金提现</option>
									</select>
							</div>
							<div class="layui-input-inline " style="width: 5%;margin-left:-11%">
								<button class="layui-btn btn-serach" lay-submit=""
									lay-filter="sreach" style="width: 168%; padding: 2%;">
									<i class="layui-icon">&#xe615;</i>搜索
								</button>
							</div>
							<div class="layui-input-inline"
								style="width: 5%; margin-left: -2%">
								<button class="layui-btn layui-btn-warm btn-reset"
									style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe666;</i>重置
								</button>
							</div>
				</div>
				<div class="layui-form-item" style="margin-right:22%;margin-left:7%">
				<div id="financeTable" lay-filter="finance-data"></div>
                </div>
           </div>
		</div>
	</div>
</form>
</div>
</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script id="barOption" type="text/html">
    {{# 
     var barOption = "<a class='layui-btn layui-btn-xs layui-btn-warm look_btn' title='查看' lay-event='orderInfo'><i class='layui-icon'>&#xe615;</i></a>";
     return barOption;
    }}  
</script>
	<script type="text/html" id="date_formate">
    {{#  
     console.log("dateTime--->"+d.createTime);
     return new Date(d.createTime).format("yyyy-MM-dd hh:mm:ss");
    }} 
    </script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/finance/index.js"></script>
		<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery/3.2.1/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/sock/sockjs.min.js"></script>

</body>