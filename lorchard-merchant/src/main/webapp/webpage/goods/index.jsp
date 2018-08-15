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

	<p>商品列表</p>
	
	
	<jsp:include page="/context/js-tags.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>/static/js/user/index.js"></script>

</body>