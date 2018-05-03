<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>绑定页面</title>
<meta http-equiv="Content-Type" name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<center>
	<body>
		<br />
		<br />
		<br />
		<br />
		<br />

		<%
	String openid = ""; openid += request.getParameter("openid");
	String timestamp = "0"; timestamp += request.getParameter("timestamp");
	long oldTime = 0, gapTime = 300005;
	
	if(timestamp != null)
    {
    	oldTime = Long.parseLong(timestamp);
		gapTime = System.currentTimeMillis() - oldTime;
    }
    //如果当前链接超时
	if(gapTime >= 300000)
	{
		out.print("<script>alert('当前链接已失效，请重试')</script>");		
		out.print("<script>document.addEventListener('WeixinJSBridgeReady', function(){ WeixinJSBridge.call('closeWindow'); }, false);</script>");		
	}
	%>

		<div class="panel panel-info">
			<div class="panel-body">
				<h2>陕西师范大学</h2>
				<form class="form-horizontal" role="form" action="../Binding.do"
					method="post">
					<div class="form-group">
						<label for="userid" class="col-sm-2 control-label"> 账号： </label>
						<div class="col-sm-10">
							<input type="text" name="userID" class="form-control"
								placeholder="账号" id="userID" />
							<p></p>
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label"> 密码：
						</label>
						<div class="col-sm-10">
							<input type="password" name="password" class="form-control"
								placeholder="密码" id="password" />
							<p></p>
						</div>
					</div>

					<input type="hidden" name="openid" value="<%=openid%>" /> <input
						type="hidden" name="timestamp" value="<%=timestamp%>" />

					<button type="submit" class="btn btn-info">绑定</button>
					<button type="reset" class="btn btn-info"
						onclick="WeixinJSBridge.call('closeWindow')">取消</button>
				</form>
				<div id="footer"><%@include file="footer.jsp"%></div>
			</div>
		</div>
		<script type="text/javascript" src="js/verify.js"></script>
	</body>
</center>
</html>