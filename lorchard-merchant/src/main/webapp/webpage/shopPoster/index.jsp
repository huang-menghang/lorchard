<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#posterCard {
	display: block;
	width: 75%;
	margin: 0 auto;
	line-height: 200px;
	text-align: center;
	background: burlywood;
	box-shadow: darkgrey 0px 0px 30px 5px ;
	margin-top:2%;
}

.grid-demo {
	height:160px;
}

a {
	cursor: pointer;
}

</style>
<jsp:include page="/context/css-tags.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺海报</title>
</head>
<body>
	<jsp:include page="/common/left-side.jsp" />
	<div class="tpl-content-wrapper">
		<div class="shop-content">

			<div class="layui-col-md12">
				<div class="layui-card" style="text-align: center;">
					<div class="layui-card-header">宣传海报模板示例</div>
					<div class="layui-card-body">
						<div class="layui-card" id="posterCard">
							<img src="<%=basePath%>/static/images/poster.png"
								style="width: 100%;" alt="" draggable="false">
							<div class="layui-row"
								style="background-color: #dddddd; margin-bottom: 10%;">
								<div class="layui-col-sm3">
									<div class="grid-demo grid-demo-bg1">25%(二维码)</div>
								</div>
								<div class="layui-col-sm6">
									<div class="grid-demo">50%(店铺信息)</div>
								</div>

								<div class="layui-col-sm3">
									<div class="grid-demo">25%(联系方式)</div>
								</div>
							</div>
						</div>
						<a>·点击量身定制自己的海报·</a>
					</div>
				</div>
				
				<div class="layui-col-md12">
				<div class="layui-card" style="text-align: center;">
					<div class="layui-card-header">活动海报模板示例</div>
					<div class="layui-card-body">
						<div class="layui-card" id="posterCard">
							<img src="<%=basePath%>/static/images/activity.png"
								style="width: 100%;" alt="" draggable="false">
							<div class="layui-row"
								style="background-color: #dddddd; margin-bottom: 10%;">
								<div class="layui-col-sm3">
									<div class="grid-demo grid-demo-bg1">25%(二维码)</div>
								</div>
								<div class="layui-col-sm6">
									<div class="grid-demo">50%(店铺信息)</div>
								</div>

								<div class="layui-col-sm3">
									<div class="grid-demo">25%(联系方式)</div>
								</div>
							</div>
						</div>
						<a>·点击量身定制自己的海报·</a>
					</div>
				</div>

			</div>





		</div>
	</div>

	<jsp:include page="/context/js-tags.jsp" />

</body>
</html>