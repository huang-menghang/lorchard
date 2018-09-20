<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = null;
	Integer serverPort = request.getServerPort();
	if (serverPort != 80 && serverPort != 443) {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	} else {
		if (path.equals("/")) {
			basePath = request.getScheme() + "://" + request.getServerName() ;
		} else {
			basePath = request.getScheme() + "://" + request.getServerName() + path;
		}
	}
%>
<meta charset="UTF-8">
<title>小果源</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta name="renderer" content="webkit"/>
<meta http-equiv="Cache-Control" content="no-siteapp"/>
<meta name="apple-mobile-web-app-title" content="源盛科技"/>
<link rel="stylesheet" href="<%=basePath%>/static/css/common/global.css">
<link rel="icon" type="image/png" href="<%=basePath%>/static/store/i/favicon.ico"/> 
<link rel="stylesheet" href="<%=basePath%>/static/plugin/amazeui/amazeui.min.css"/>
<link rel="stylesheet" href="<%=basePath%>/static/plugin/layui/css/layui.css">
<link rel="stylesheet" href="<%=basePath%>/static/css/common/app.css"/>
<link rel="stylesheet" href="https://at.alicdn.com/t/font_664399_1z02i08jjid.css">
<link rel="stylesheet" href="https://at.alicdn.com/t/font_827207_ld86l1pca8s.css">
<script src="<%=basePath%>/static/plugin/jquery/1.12.1/jquery-1.12.1.min.js"></script>
<script src="https://at.alicdn.com/t/font_664399_1z02i08jjid.js"></script>