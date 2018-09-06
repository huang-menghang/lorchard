<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/context/begin-tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/context/css-tags.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=basePath%>/static/css/decoration/index.css">
<title>店铺概述</title>
</head>
<body>
	<jsp:include page="/common/left-side.jsp" />
<div class="tpl-content-wrapper">
	<div class="shop-content">
			
		<h2 ondragleave="return false">小程序预览</h2>	
		<div id="dest" class="show">
		    
		</div>
		<div id="gb" draggable="false" class="deleteUtils">
			<div class="utils">
			    <div class="" draggable="true" ondragstart="dsHandler(event);">
			    	<div class="search">
			    		<div class="serarch-content">
			    			
			    			<input class="search-input" value="请输入搜索内容">
			    			<div class="search-btn">搜索</div>
			    		</div>
			    	</div>
			    </div>
			    <div class="" draggable="true" ondragstart="dsHandler(event);">
			    	
					<div class="scroll-first">
				        <div class="scroll-second">
				            <img src="<%=basePath %>/static/images/my.png" alt="" draggable="false">
				            <img src="<%=basePath %>/static/images/my.png" alt="" draggable="false">
				            <img src="<%=basePath %>/static/images/my.png" alt="" draggable="false">
				            <img src="<%=basePath %>/static/images/my.png" alt="" draggable="false">
				        </div>
				    </div>


			    </div>
			    <div class="" draggable="true" ondragstart="dsHandler(event);">项目3</div>
			</div>

		</div>
		
		
		
		<div class="clear"></div>


			
	</div>
</div>

	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/js/decoration/index.js"></script>
</body>
</html>