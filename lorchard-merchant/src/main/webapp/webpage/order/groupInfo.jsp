<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<style type="text/css">
.x-red {
	color: red
}

.error {
	color: red
}

.layui-form-item {
	margin-left: 5%;
	padding-bottom: 1%
}

.layui-col-md9 {
	background-color: #e2e2e2;
	height: 40px;
	padding-top: 1%;
	padding-left: 2%;
	margin-top: 2px;
	margin-bottom: 10px;
}
.layui-form-label {
	width: 12%;
	margin-right: 2%;
}
dl{
  margin-top:-3%;
}
#goods{
    /*背景色*/
  background-color:#f2f2f2;
  /*边框*/
  border:1px solid #f2f2f2;
  /*去掉边框*/
  border:none;
  border-widht:3px;
  text-align:center;
  width:95%;
  margin-top:-14%;
  color:#FF5722;
}
#goodslabel{
 float:left;
}
#goodsdiv{
float:left;
width:5%;
}
</style>
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />
	<div class="tpl-content-wrapper">
		<div class="layui-tab layui-tab-brief main-tab-container">
			<form class="formarea">
				<div class="layui-tab-content">
					<div class="layui-tab layui-tab-card">
						<ul class="layui-tab-title main-tab-title">
						<div class="main-tab-item layui-bg-blue">订单信息</div>
					</ul>
						<div class="layui-form" style="margin-top: 10px;">
							<!--订单信息  -->
							<div class="layui-col-md9">
								<div class="layui-col-md4">拼团信息:</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">拼团id:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="id" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							
							<div class="layui-form-item">
								<label class="layui-form-label">创建者id</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="inviteId" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">会员id:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="memberId" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单创建时间:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="createTime" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							<div class="layui-col-md9">
								<div class="layui-col-md4" style="width: 12%; margin-right: 2%">订单信息:</div>
							</div>
							<div class="layui-form-item" style="margin-right:30%;">
				            <div id="orderTable" lay-filter="order-data"></div>
				            </div>

							<div class="layui-form-item">
								<div class="layui-input-block" style="margin-left: 30%">
									<input onclick="history.go(-1)" type="button" value="返回"
										class="layui-btn layui-btn-lg"
										style="margin-left: 6%; margin-top: 2%">
								</div>
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
	<script type="text/javascript"
		src="<%=basePath%>/static/js/order/groupInfo.js"></script>
	<script type="text/html" id="date_formate">
    {{#  
 	return new Date(d.createTime).format("yyyy-MM-dd hh:mm:ss");
    }} 
    </script>
</body>
</html>