<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style>
.layui-form-label{
width: 12%; 
margin-right: 2%;
}

.layui-form-item {
	margin-left: 5%;
	padding-bottom: 4%
}
</style>
<jsp:include page="/context/css-tags.jsp" />
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />
	<div class="tpl-content-wrapper">
	 <div class="layui-tab layui-tab-brief main-tab-container">
		<div class="layui-tab-content">
		<div class="layui-tab layui-tab-card">
		    <ul class="layui-tab-title">
				<li class="layui-this">会员详情</li>
			</ul>
		    <div class="layui-form" style="margin-top: 10px;">
		         <div class="layui-form-item">
					<label class="layui-form-label" style="margin-top:45px">头像</label>
					<div >
						<img id="meber-avatar" alt="">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">昵称</label>
					<div class="layui-input-inline input-custom-width">
						<input type="text" name="nicknameStr" 
							value="" autocomplete="off" readonly="readonly"
							class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">性别</label>
					<div class="layui-input-inline input-custom-width">
						<input type="text" name="gender" 
							value="" autocomplete="off" readonly="readonly"
							class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">手机号</label>
					<div class="layui-input-inline input-custom-width">
						<input type="text" name="mobile" 
							value="" autocomplete="off" readonly="readonly"
							class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">省</label>
					<div class="layui-input-inline input-custom-width">
						<input type="text" name="province" 
							value="" autocomplete="off" readonly="readonly"
							class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">市</label>
					<div class="layui-input-inline input-custom-width">
						<input type="text" name="city" 
							value="" autocomplete="off" readonly="readonly"
							class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">创建时间</label>
					<div class="layui-input-inline input-custom-width">
						<input type="text" name="createTime" 
							value="" autocomplete="off" readonly="readonly"
							class="layui-input">
					</div>
				</div>
				
			</div>
		</div>
		</div>
	</div>
	</div>
	
	<jsp:include page="/context/js-tags.jsp" />
    <script type="text/javascript" src="<%=basePath%>/static/plugin/city/city-ch.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/member/info.js"></script>

</body>
</html>