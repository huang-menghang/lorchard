<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<jsp:include page="/context/css-tags.jsp" />
<style type="text/css">
     
	.uploader-list {
		margin-left: -15px;
	}

.uploader-list .handle {
	background-color: black;
	color: white;
	filter: alpha(Opacity = 80);
	-moz-opacity: 0.5;
	opacity: 0.5;
	width: 100px;
	text-align: right;
	height: 18px;
	margin-bottom: -18px;
	display: none;
}

.uploader-list .handle span {
	position: relative margin-right: 5%;
}

.uploader-list .handle span:hover {
	position: relative cursor: pointer;
}

.uploader-list .file-iteme {
	margin: 12px 0 0 15px;
	padding: 1px;
	float: left;
}

.x-red {
	color: red
}

.error {
	color: red
}

.layui-form-item {
	margin-left: 5%;
	padding-bottom: 4%
}

.layui-col-md9 {
	background-color: #e2e2e2;
	height: 40px;
	padding-top: 1%;
	padding-left: 2%;
	margin-top: 2px;
	margin-bottom: 10px;
}
.layui-form-label{
width: 12%; 
margin-right: 2%;
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
						<ul class="layui-tab-title">
							<li class="layui-this ops-title"></li>
						</ul>
						<!--基本信息  -->
						<div class="layui-col-md9">
							<div class="layui-col-md4">基本信息</div>
						</div>
						<div class="layui-form" style="margin-top: 10px;">
							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>活动名称：</label>
								<div class="layui-input-inline input-custom-width">
									<input type="text" name="activityName" required="true" value="" diyRule="^[\u4E00-\u9FA5A-Za-z0-9_]+$" diyCheck="不能有特殊字符"
										autocomplete="off" placeholder="请输入活动名称" class="layui-input"
										lay-verType="alert">
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label"><span class="x-red">*</span>活动描述：</label>
								<div class="layui-input-inline input-custom-width">
									<textarea name="description" lay-verify="" required="true"
										diyCheck="活动描述不能为空" autocomplete="off" placeholder="请输入活动描述"
										class="layui-textarea"></textarea>
								</div>
							</div>

							<div class="layui-form-item">
								<label class="layui-form-label"><span
									class="x-red">*</span>活动类型：</label>
								<div class="layui-input-block"
									style="width: 190px; text-align: center; margin-left: 14%">
									<select name="activityType" lay-filter="reportType">
										<option></option>
										<option value="0">拼团活动</option>
									</select>
								</div>
							</div>

							<div class="layui-form-item">
							<label class="layui-form-label " style="width: 10%">活动时间</label>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="活动开始日" name="startTime">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="活动截止日" name="endTime">
							</div>	
							</div>				
						</div>
						
						<!--商品基本信息  -->
						
						<div class="layui-form" style="margin-top: 10px;">
						<div class="layui-col-md9">
							<div class="layui-col-md4">选择商品</div>
						</div>
						<!-- 表格 -->
						<div class="layui-form-item" style="margin-right:22%;margin-left:7%">
				         <div id="activityTable" lay-filter="table-activity"></div>
													
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
    var barOption = "<a class='layui-btn layui-btn-small layui-btn-warm look_btn' title='查看' lay-event='info'><i class='layui-icon'>&#xe615;</i></a>";
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
		src="<%=basePath%>/static/js/activity/info.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/localization/messages_zh.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery-validation/additional-methods.min.js"></script>

</body>
</html>