<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>会员列表</title>
<jsp:include page="/context/css-tags.jsp" />
<style>
.laytable-cell-1-imagePath {
	height: auto;
}
td .layui-table-cell{
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
			<li class="layui-this"><a href="<%=basePath%>/member?title=member">会员列表</a></li>
			<div class="main-tab-item layui-bg-blue">会员管理</div>
		</ul>
		<div class="layui-tab-content">
			<div class="layui-form xbs" align="center">
				<div class="layui-form-pane">
					<div class="layui-form-item" style="display: inline-block;">
						<label class="layui-form-label ">时间范围</label>
						<div class="layui-input-inline ">
							<input type="text" readonly="readonly" class="layui-input"
								value="" placeholder="开始日"
								name="startTime">
						</div>
						<div class="layui-input-inline ">
							<input type="text" readonly="readonly" class="layui-input"
								value="" placeholder="截止日" name="endTime">
						</div>
						<div class="layui-input-inline ">
								<input type="text" name="nicknameStr"
									value="" placeholder="会员昵称" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-input-inline ">
								<input type="text" name="mobile"
									value="" placeholder="手机号码" autocomplete="off" class="layui-input">
						</div>
						<div class="layui-input-inline " style="width: 8%">
							<button class="layui-btn btn-serach" lay-submit=""
								lay-filter="sreach">
								<i class="layui-icon">&#xe615;</i>搜索
							</button>
						</div>
						<div class="layui-input-inline " style="width: 7%">
						<button class="layui-btn layui-btn-warm btn-reset" style="width: 80px">
							<i class="layui-icon">&#x1002;</i>重置
						</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 表格 -->
            <div id="dateTable" lay-filter="table-data"></div>
			
		</div>
	</div>
</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript" src="<%=basePath%>/static/plugin/city/city-ch.js"></script>
	<script id="barOption" type="text/html">
   {{#
    var barOption = "<a class='layui-btn layui-btn-small layui-btn-warm look_btn' title='查看' lay-event='detail'><i class='layui-icon'>&#xe615;</i></a>";
    return barOption;
   }} 
    </script>
    <script id="gender" type="text/html">
    {{#  
    console.log(d.gender);
    return (d.gender == 0 ? "男":"女"); 
    }} 
    </script>
    <script type="text/html" id="province_ch">
    {{#  
    console.log(d.province);
    return getCityName_ops.getCityName(d.province.toLowerCase()); 
    }} 
    </script>
    <script type="text/html" id="city_ch">
    {{#  
    return getCityName_ops.getCityName(d.city.toLowerCase()); 
    }} 
    </script>
     <script type="text/html" id="date_formate">
    {{#  
     console.log("dateTime--->"+d.createTime);
     return new Date(d.createTime).format("yyyy-MM-dd hh:mm:ss");
    }} 
    </script>
	<script type="text/javascript"
		src="<%=basePath%>/static/js/member/index.js"></script>

</body>
</html>