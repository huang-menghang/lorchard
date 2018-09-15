<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<style>
.layui-input {
	/*背景色*/
	background-color: #f2f2f2;
	/*边框*/
	border: 1px solid #f2f2f2;
	/*去掉边框*/
	border: none;
	text-align: center;
}

#financelabel {
	font-size: 30px;
}

#financeInput {
	font-size: 31px;
	width: 40%;
	margin-top: -7%;
	background-color: #f2f2f2;
	/*边框*/
	border: 1px solid #f2f2f2;
	/*去掉边框*/
	border: none;
	text-align: center;
}

p {
	margin-bottom: 5%;
}

.layui-col-md9 {
	background-color: #e2e2e2;
	height: 40px;
	padding-top: 1%;
	margin-top: 2px;
	margin-bottom: 10px;
	width: 80%;
}

span {
	color: #c2c2c2;
}

.layui-col-sm4 {
	border-right: 1px solid #000;
	padding-left: 2%;
}

.layui-form-label {
	width: 14%;
	margin-right: 2%;
}

</style>
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
									总计收入： <span>（截止到今天0点）</span>
								</p>
							</div>
							<div class="layui-block">
								<label id="financelabel" style="">￥</label> <input type="text"
									name="balance" readonly="readonly" id="financeInput">
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
									name="totalCommission" readonly="readonly" id="financeInput">
							</div>
						</div>
						<div class="layui-col-sm4">
							<div class="layui-block">
								<p class="layui-title">提现金额：</p>
							</div>
							<div class="layui-block">
								<label id="financelabel" style="">￥</label> <input type="text"
									name="totalCash" readonly="readonly" id="financeInput" value="">
							</div>
						</div>
					</div>

				</div>
			</div>

			<!--交易记录-->
		
				<div class="layui-col-md9">
					<div class="layui-col-md4" style="margin-left: 5%;">流水信息</div>
				</div>
				<div class="layui-form-item" style="margin-right:25%;margin-left:7%">
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
     var barOption = "<a class='layui-btn layui-btn-xs layui-btn-warm look_btn' title='查看' lay-event='info'><i class='layui-icon'>&#xe615;</i></a>";
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

</body>