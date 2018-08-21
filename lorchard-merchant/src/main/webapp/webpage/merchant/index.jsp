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

.laytable-cell-1-8 {
	height: auto;
}
</style>
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />
 <div class="tpl-content-wrapper">
	<div style="margin-top: 20px" class="layui-tab layui-tab-brief main-tab-container">
		<ul class="layui-tab-title main-tab-title">
			<li class="layui-this"><a href="<%=basePath%>/user">用户列表</a></li>
					<li><a href="<%=basePath%>/user/set">添加用户</a></li>
			<div class="main-tab-item layui-bg-blue">用户管理</div>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-form xbs" align="center">
				<div class="layui-form-pane">
					<div class="layui-form-item" style="display: inline-block;">
						<label class="layui-form-label xbs768">时间范围</label>
						<div class="layui-input-inline xbs768">
							<input type="text" readonly="readonly" class="layui-input"
								value="" placeholder="开始日"
								name="startTime">
						</div>
						<div class="layui-input-inline xbs768">
							<input type="text" readonly="readonly" class="layui-input"
								value="" placeholder="截止日" name="endTime">
						</div>
						<div class="layui-input-inline xbs768">
								<input type="text" name="nicknameStr"
									value="" placeholder="用户昵称" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-input-inline xbs768">
								<input type="text" name="loginNameStr"
									value="" placeholder="登录名" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-input-inline xbs768" style="width: 80px">
							<button class="layui-btn btn-serach" lay-submit=""
								lay-filter="sreach">
								<i class="layui-icon">&#xe615;</i>
							</button>
						</div>
						<xblock>
						<button class="layui-btn layui-btn-warm btn-reset">
							<i class="layui-icon">&#x1002;</i>重置
						</button>
						</xblock>
					</div>
				</div>
			</div>
			<!-- 表格 -->
            <div id="dateTable" lay-filter="table-data"></div>
			
		</div>
	</div>
</div>
	<script>
		$(function() {

		});
	</script>

	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/js/user/index.js"></script>

</body>
</html>