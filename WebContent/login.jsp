<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/taglib.jsp"%>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>

<link rel="stylesheet" href="static/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="static/css/login.css">
<script src="static/js/jQuery.js"></script>
<script src="static/js/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<!--引入头部JSP-->
	<!--使用模态框的方式模拟一个登陆框-->
	<div class="modal show" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close">&times;</button>
					<div class="hidden text-center" id="errorMsg">
						<span class="glyphicon glyphicon-exclamation-sign"></span>登陆出错
					</div>
					<div class="hidden text-center" id="nullMsg">
						<span class="glyphicon glyphicon-exclamation-sign"></span>账号或密码不能为空
					</div>
					<h3 class="text-center text-primary">陕西师范大学学生资助管理中心自助服务平台</h3>
					<h4 class="text-center text-primary">用户登录</h4>
				</div>
				<div class="modal-body">
					<form class="form col-md-12 center-block" id="loginForm"
						method="post">
						<div class="form-group-lg" id="accountDiv">
							<label class="sr-only" for="inputAccount">账号</label>
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
								</div>
								<input class="form-control" id="inputAccount" name="accountNo"
									type="text" placeholder="账号" required autofocus>
							</div>
							<div class="hidden text-center" id="accountMsg">
								<span class="glyphicon glyphicon-exclamation-sign"></span>账号不存在
							</div>
						</div>
						<br>
						<div class="form-group-lg" id="pwdDiv">
							<label class="sr-only" for="inputPassword">密码</label>
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</div>
								<input class="form-control" id="inputPassword" name="pwd"
									type="password" placeholder="密码" required>
								<!--
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-eye-open"></span>
								</div>
								 -->
							</div>
							<div class="hidden text-center" id="pwdMsg">
								<span class="glyphicon glyphicon-exclamation-sign"></span>密码错误
							</div>
						</div>
						<!-- <div class="checkbox">
							<label> <input type="checkbox" value="remember-me">记住我
							</label>
						</div>
						 -->
						<div class="form-group">
							<button class="btn btn-primary btn-lg col-md-6" id="btn_login"
								type="button">登录</button>
							<button class="btn btn-default btn-lg col-md-6" id="btn_reset"
								type="reset">重置</button>
						</div>
					</form>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	<script src="static/js/login.js"></script>	
</body>
</html>