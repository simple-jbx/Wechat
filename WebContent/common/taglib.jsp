<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.yh}">	
<%
	//request.getServerName()	服务器名称
	String path = request.getContextPath();//项目路径
	int port = request.getServerPort();
	String basePath  = null;
	if(port==80){
		basePath = request.getScheme()+"://"+request.getServerName()+path;
	}else{
		basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	}
	//basePath 项目的绝对路径
	session.setAttribute("basePath", basePath);
%>
</c:if>