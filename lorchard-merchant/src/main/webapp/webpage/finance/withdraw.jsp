<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<style>
.layui-input {
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
	width: 60%;
	margin-top: -4%;
	background-color: #f2f2f2;
	/*边框*/
	border: 1px solid #f2f2f2;
	/*去掉边框*/
	border: none;
	text-align:left;
}

#financeInput2 {
	font-size: 31px;
	width: 40%;
	margin-top: -7%;
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
.layui-input{
width:150%;
}
span {
	color: #c2c2c2;
}

.layui-col-sm4 {

	padding-left: 2%;
}

.layui-form-label {
	width: 12%;
	margin-right: 2%;
}

.layui-form-item {
	margin-top: 8%;
}

.layui-table-cell {
	margin-left: 0%;
}

.error {
	color: red
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
					href="<%=basePath%>/finance?title=financeReceived">财务管理</a></li>
				<div class="main-tab-item layui-bg-blue">财务一览</div>
			</ul>
			
			
			
					


			<div class="layui-col-md9">
				<div class="layui-col-md4" style="margin-left: 5%;">提现详情</div>
			</div>
					<div class="layui-form-item" style="margin-top: 6%; margin-right: 6%;margin-bottom:-1%;">
						<div class="layui-col-sm4" style="margin-top: 4%;margin-left: 5%;width:50%;">
						
							<div class="layui-block" style="margin-top:-5%;margin-left:-7%">
								<label id="financelabel" style="">可用余额:  ￥</label>
								<input type="text" name="balance" readonly="readonly" id="financeInput" value="">
							</div>
						</div>

				 		<form class="formarea" >
							<div class="layui-form" >							
								<div class="layui-form-item" >
									<label class="layui-form-label" style="margin-top: 3%">姓名 :</label>
								
								<div class="layui-input-inline input-custom-width" style="margin-top: 3%">
									<input type="text" name="name" required="true"
										value="" autocomplete="off" diyRule="^[\u4e00-\u9fa5]{0,}$" diyCheck="输入的不是汉字" placeholder="请输入姓名"
										class="layui-input">
								</div>			
								</div>
								
								<div class="layui-form-item" style="margin-top: 3%">
									<label class="layui-form-label" >银行卡号 :</label>
								
									<div class="layui-input-inline input-custom-width">
										<input type="text" name="bankNumber" required="true"
											value="" autocomplete="off" placeholder="请输入银行卡号"
											class="layui-input" id="bankNumber" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
											>
									</div>	
								</div>
										
								<div class="layui-form-item" style="margin-top: 3%">
									<label class="layui-form-label" >开户银行 :</label>
								
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="openBank" required="true"
										value="" autocomplete="off" diyRule="^[\u4e00-\u9fa5]{0,}$" diyCheck="输入的不是汉字" placeholder="请输入开户银行"
										class="layui-input" >
								</div>
								</div>
								<div class="layui-form-item" style="margin-top: 3%">
									<label class="layui-form-label" >提现金额 :</label>
								
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="cash" required="true"
										value="" autocomplete="off" placeholder="请输入金额"
										class="layui-input" diyRule="^[0-9]+.?[0-9]*$" diyCheck="输入的不是数字"
										>
								</div>	
								</div>
								<div class="layui-form-item" style="margin-top: 3%">					
									<div class="layui-input-block" style="margin-left: 14%">
										<button class="layui-btn" type="button" lay-submit="" lay-filter="finance_ withdraw">确定</button>
									</div>
								</div>
							</div>
						</form>
						
					
			</div>


			<div class="layui-form-item" style="margin-top: -1%">
				<div class="layui-col-md9">
				<div class="layui-col-md4" style="margin-left: 5%;">提现详情</div>
			   </div>
			</div>

			<!-- 表格 -->
			<div class="layui-form-item" style="margin-right:22%;margin-left:1%;margin-top:-1%">
			<div id="withdrawTable" lay-filter="table-order"  ></div>
			</div>
		</div>
	</div>


	<jsp:include page="/context/js-tags.jsp" />
	<script id="barOption" type="text/html">

    </script>
	<script type="text/html" id="date_formate">
    {{#  
     console.log("dateTime--->"+d.createTime);
     return new Date(d.createTime).format("yyyy-MM-dd hh:mm:ss");
    }} 
    </script>
    	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
    <script type="text/javascript" 
       src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>	
	<script type="text/javascript"
		src="<%=basePath%>/static/js/finance/withdraw.js"></script>

</body>