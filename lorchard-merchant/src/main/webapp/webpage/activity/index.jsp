<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
</head>
<body>

	<!-- 左侧菜单开始 -->
	<jsp:include page="/common/left-side.jsp" />

	<div class="tpl-content-wrapper">
		<div style="margin-top: 20px"
			class="layui-tab layui-tab-brief main-tab-container">
			<ul class="layui-tab-title main-tab-title">
				<li class="layui-this"><a
					href="<%=basePath%>/order?title=orderAll">活动列表</a></li>
				<div class="main-tab-item layui-bg-blue">活动管理</div>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-form xbs" align="center">
					<div class="layui-form-pane">
						<div class="layui-form-item">
							<label class="layui-form-label " style="width: 10%">活动时间</label>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="开始时间" name="startTime">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="结束时间" name="endTime">
							</div>

							<div class="layui-input-inline " style="width: 15%">
								<input type="text" name="id" value="" placeholder="活动id"
									autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" name="activityName" value=""
									placeholder="活动名称" autocomplete="off" class="layui-input">
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
							
							<div class="layui-form-item" style="margin-left:1%;">
							<label class="layui-form-label" style="width: 9%;margin-left:-1%"; id="tableLable">类型</label>
							<div class="layui-input-inline" style="width: 25%; text-align: center;">
									<select name="activityType" lay-filter="reportType">
										<option></option>
										<option value="0">拼团活动</option>
									</select>
							</div>
							
							</div>
						</div>							
						</div>
					</div>
				</div>
				<!-- 表格 -->
				<div class="layui-form-item" style="margin-left:1%;width:95%;">
				<div id="activityTable" lay-filter="table-activity"></div>
                </div>
			</div>
		</div>



	<jsp:include page="/context/js-tags.jsp" />
	<script id="barOption" type="text/html">
    {{#
	var barOption = "<a class='layui-btn layui-btn-small layui-btn-warm look_btn' title='查看' lay-event='info'><i class='layui-icon'>&#xe615;</i></a>";
    barOption += "<a class='layui-btn layui-btn-danger layui-btn-small' title='删除' lay-event='delete'><i class='layui-icon'>&#xe640;</i></a>";
    return barOption;

    }} 
    </script>
	<script type="text/html" id="date_formate">
    {{#  
     console.log("dateTime--->"+d.startTime);
     return new Date(d.startTime).format("yyyy-MM-dd hh:mm:ss");

    }} 
    </script>
    <script id="activityType" type="text/html">
    {{#  
    console.log(d.activityType);
    return (d.activityType == 0 ? "拼团活动":"整点秒杀"); 
    }} 
    </script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/activity/index.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/jquery/3.2.1/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>/static/plugin/sock/sockjs.min.js"></script>
</body>
</html>