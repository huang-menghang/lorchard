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

td .layui-table-cell {
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
		<div style="margin-top: 20px"
			class="layui-tab layui-tab-brief main-tab-container">
			<ul class="layui-tab-title main-tab-title">
				<li class="layui-this"><a
					href="<%=basePath%>/goods?title=goods">商品列表</a></li>
				<div class="main-tab-item layui-bg-blue">商品管理</div>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-form xbs" align="center">
					<div class="layui-form-pane">
						<div class="layui-form-item" style="margin-bottom:0%" >
							<label class="layui-form-label " style="width: 10%">时间范围</label>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="开始日" name="startTime">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" readonly="readonly" class="layui-input"
									value="" placeholder="截止日" name="endTime">
							</div>

							<div class="layui-input-inline " style="width: 15%">
								<input type="text" name="name" value="" placeholder="商品名称"
									autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" name="description" value=""
									placeholder="商品描述" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-input-inline " style="width: 5%">
								<button class="layui-btn btn-serach" lay-submit=""
									lay-filter="sreach" style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe615;</i>搜索
								</button>

							</div>
							<div class="layui-input-inline"
								style="width: 5%; margin-left: 3%">
								<button class="layui-btn layui-btn-normal btn-add"
									style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe61f;</i>添加
								</button>
							</div>
							<div class="layui-input-inline"
								style="width: 5%; margin-left: 3%">
								<button class="layui-btn layui-btn-warm btn-reset"
									style="width: 150%; padding: 2%;">
									<i class="layui-icon">&#xe666;</i>重置
								</button>
							</div>
			
						</div>
						<div class="layui-form-item">
						<label class="layui-form-label " style="width:10%;">价格范围</label>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text" class="layui-input" name="minPriceMin"
									value="" placeholder="最低折后价" autocomplete="off">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text"  class="layui-input" autocomplete="off"
									value="" placeholder="最高折后价" name="minPriceMax">
							</div>
							<label class="layui-form-label " style="width: 10%">销量范围</label>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text"  class="layui-input" autocomplete="off"
									value="" placeholder="最低销量" name="salesMin">
							</div>
							<div class="layui-input-inline " style="width: 15%">
								<input type="text"  class="layui-input" autocomplete="off"
									value="" placeholder="最高销量" name="salesMax">
							</div>
						</div>
					</div>
				</div>
				<!-- 表格 -->
				<div id="goodsTable" lay-filter="table-goods"></div>

			</div>
		</div>



	</div>
	<jsp:include page="/context/js-tags.jsp" />
	<script id="barOption" type="text/html">
    {{#
    var barOption = "<a class='layui-btn layui-btn-small layui-btn-warm look_btn' title='查看' lay-event='info'><i class='layui-icon'>&#xe615;</i></a>";
    barOption += "<a class='layui-btn layui-btn-small' title='编辑' lay-event='edit'><i class='layui-icon'>&#xe60a;</i></a>";
    barOption += "<a class='layui-btn layui-btn-danger layui-btn-small' title='删除' lay-event='delete'><i class='layui-icon'>&#xe640;</i></a>";

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
		src="<%=basePath%>/static/js/goods/index.js"></script>

</body>
</html>