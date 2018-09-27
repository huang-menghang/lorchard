<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = null;
	Integer serverPort = request.getServerPort();
	if (serverPort != 80 && serverPort != 443) {
		basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	} else {
		if (path.equals("/")) {
			basePath = request.getScheme() + "://" + request.getServerName();
		} else {
			basePath = request.getScheme() + "://" + request.getServerName() + path;
		}
	}
	Enumeration<String> names =  request.getSession().getAttributeNames();
	while(names.hasMoreElements()){
		String name = names.nextElement();
		System.out.println("name----->"+name);
		Object obj = request.getSession().getAttribute(name);
		System.out.println("obj----->"+obj);
	}
%>
<c:set var="webRoot" value="<%=basePath%>" />