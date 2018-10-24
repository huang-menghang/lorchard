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
								<div class="layui-col-md4">订单信息:</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">订单编号:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="orderNo" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">取货方式:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="sendMethod" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							
							<div class="layui-form-item">
								<label class="layui-form-label">快递单号:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="expressNo" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
 	                        <div class="layui-form-item">
								<label class="layui-form-label">订单状态:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="orderStatus" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">支付时间:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="payTime" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">确认收货时间:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="confirmTime" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">订单备注:</label>
								<div class="layui-input-inline input-custom-width">
									<textarea name="remark" 
										class="layui-textarea"></textarea>
								</div>
							</div>
							
							<!--商品信息表格-->
							<div class="layui-col-md9">
								<div class="layui-col-md4" style="width: 12%; margin-right: 2%">商品信息:</div>
							</div>
							<div class="layui-form-item" style="margin-right:30%;">
				            <div id="orderTable" lay-filter="order-data"></div>
								<div class="layui-form-item" style="margin-left:26%;margin-right:-83%;margin-bottom:-2%;">								
								<label id="goodslabel">订单共:</label>
								<div id="goodsdiv">
								<input type="text" name="count" readonly="readonly"
									 id="goods" style="">
							   </div >
								<label id="goodslabel" style="">件商品，折扣为：</label>
								<div id="goodsdiv">
								<input type="text" name="orderDiscount" readonly="readonly"
									 id="goods" style="">
							   </div>
							   <label id="goodslabel" style="">共计：￥</label>
								<div id="goodsdiv">
								<input type="text" name="orderPendingBalance" readonly="readonly"
									 id="goods" style="">
							   </div>
							   
							   <label id="goodslabel" style="">（含运费￥</label>
								<div id="goodsdiv">
								<input type="text" name="freightPrice" readonly="readonly"
									 id="goods" style="">
							   </div>
							   <label id="goodslabel" style="">）</label>
								</div>
							</div>
							<!--客户信息  -->
							<div class="layui-col-md9">
								<div class="layui-col-md4" style="width: 12%; margin-right: 2%">客户信息:</div>
							</div>
						
							<div class="layui-form-item">
								<label class="layui-form-label">客户编号:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="orderMemberId" readonly="readonly"
										class="layui-input">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label">客户名称:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="orderMemberName" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">详细地址:</label>
								<div class="layui-input-inline input-custom-width" style="margin-right:0%">
									<input type="text" name="province"
										readonly="readonly" class="layui-input">
								</div>
								<div class="layui-input-inline input-custom-width" style="margin-right:0%">
									<input type="text" name="city"
										readonly="readonly" class="layui-input">
								</div>
								<div class="layui-input-inline input-custom-width" style="margin-right:28%">
									<input type="text" name="diatrict"
										readonly="readonly" class="layui-input">
								</div>
								<div class="layui-input-inline input-custom-width" style="width:56%">
									<input type="text" name="address"
										readonly="readonly" class="layui-input">
								</div>
							</div>
							
							
							<div class="layui-form-item">
								<label class="layui-form-label">电话号码:</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="mobile" readonly="readonly"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<div class="layui-input-block" style="margin-left: 8%">
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
		src="<%=basePath%>/static/js/order/info.js"></script>
	<script type="text/html" id="date_formate">
    {{#  
     return new Date(d.payTime).format("yyyy-MM-dd hh:mm:ss");
 	return new Date(d.confirmTime).format("yyyy-MM-dd hh:mm:ss");
    }} 
    </script>
</body>
</html>