<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = null;
	Integer serverPort = request.getServerPort();
	if (serverPort != 80 && serverPort != 443) {
		basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path;
	} else {
		if(path.equals("/")){
			basePath = request.getScheme() + "://"
					+ request.getServerName();
		}else{
		basePath = request.getScheme() + "://"
				+ request.getServerName() + "/" + path;
		}
	}
%>
<input type="hidden" name="basePath" value="<%=basePath%>" />
<script type="text/javascript"
	src="<%=basePath%>/static/plugin/jquery/3.2.1/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/js/common/common.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/js/common/app.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/plugin/layui/layui.js"></script>
<script>WEB_ROOT="<%=basePath%>"</script>

</html>