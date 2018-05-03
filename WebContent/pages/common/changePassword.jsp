<%@ page language="java" pageEncoding="utf-8"
	import="utils.SHA1Util, utils.StringUtils"
%>
<%@include file="../../common/header.jsp"%>
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>陕西师范大学学生资助管理中心自助服务平台 　V1.0</title>
	<meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<link href="../../static/skin_1/style.css" type="text/css" rel="stylesheet"/>
	</head>
	<script src="../../static/js/jQuery.js"></script>
	<body class="htmlbody">
	<p align=center><b>修改密码</b></p>
	<form action="" method="post" target=_self onsubmit="return check()">
	<table width="98%" border="0" align="center" cellpadding="3" cellspacing="1" class="tableborder">
		
		<tr>
			<td width="30%" class="tablerow1">原密码：</td>
			<td class="tablerow1">
				<input type=password value="" name="oldPassword" id="inputOldPass" 
				onfocus="inputOldPassOnfocus()" onblur="inputOldPassOnblur()" size="30"/>
			</td>
		</tr>
		<tr>
			<td width="30%" class="tablerow1">新密码：</td>
			<td class="tablerow1">
				<input type=password value="" name="newPassword" id="inputNewPass" 
				onfocus="inputNewPassOnfocus()" onblur="inputNewPassOnblur()" size="30"/>
			</td>
		</tr>
		<tr>
			<td width="30%" class="tablerow1">确认密码：</td>
			<td class="tablerow1">
				<input type=password value="" name="confirmPassword" id="inputConfPass" 
				onfocus="inputConfPassOnfocus()" onblur="inputConfPassOnblur()" size="30"/>
			</td>
		</tr>
		
	</table>
	<p id="hint" align=center></p>
	<p align=center>
		<input type=submit class="button" value=" 确 定 "/>&nbsp;&nbsp;
		<input type=button class="button" onclick="window.close()" value=" 取 消 "/>
	</p>
			
	</form>
	
	<script>		
		function inputOldPassOnblur() {
			var inputOldPass = $('#inputOldPass').val();
			if(!inputOldPass) {
				document.getElementById("hint").innerHTML = "原密码不能为空！";
			}		
		}
		
		function inputNewPassOnblur() {
			var myReg = /^[a-zA-Z0-9_%&.*-+@#^()]{6,20}$/;
			var inputNewPass = $('#inputNewPass').val();
			if(!inputNewPass) {
				document.getElementById("hint").innerHTML = "新密码不能为空！";
			}else if(!myReg.test(inputNewPass)){
				document.getElementById("hint").innerHTML = "密码中只能有数字或字母！";
			}else if(inputNewPass.length < 6 || inputNewPass.length > 20) {
				document.getElementById("hint").innerHTML = "密码长度应为6-20位！";
			}
		}
		
		function inputConfPassOnblur() {
			var inputNewPass = $('#inputNewPass').val();
			var inputConfPass = $('#inputConfPass').val();
			if(!inputConfPass) {
				document.getElementById("hint").innerHTML = "确认密码不能为空！";
			}else if(inputNewPass != inputConfPass) {
				document.getElementById("hint").innerHTML = "两次输入密码不一致！";
			}
		}
		
		function inputOldPassOnfocus() {
		}
		
		function inputNewPassOnfocus() {
		}
		
		function inputConfPassOnfocus() {
		}
		
		function check() {
			var inputOldPass = $('#inputOldPass').val();
			var inputNewPass = $('#inputNewPass').val();
			var inputConfPass = $('#inputConfPass').val();
			var myReg = /^[a-zA-Z0-9_%&.*-+@#^()]{6,20}$/;
			if(!inputOldPass) {
				document.getElementById("hint").innerHTML = "原密码不能为空！";
				return false;
			}
			if(!inputNewPass) {
				document.getElementById("hint").innerHTML = "请输入新密码！";
				return false;
			}
			if(!inputConfPass) {
				document.getElementById("hint").innerHTML = "请输入确认密码！";
				return false;
			}
			if(!myReg.test(inputNewPass)){
				document.getElementById("hint").innerHTML = "密码中不能含有中文或特殊字符！";
				return false;
			}
			if(inputNewPass.length < 6 || inputNewPass.length > 20) {
				document.getElementById("hint").innerHTML = "密码长度应为6-20位！";
				return false;
			}
			if(inputNewPass != inputConfPass) {
				document.getElementById("hint").innerHTML = "两次输入密码不一致！";
				return false;
			}
			
			$.ajax({
				type:"post",//请求方式
				data:{
					"inputOldPass" : inputOldPass,
					"inputNewPass" : inputNewPass
				},
				url:"<%=session.getAttribute("basePath")%>/changePassController.do",//请求地址
				error:function(){//如果出错了，将事件重新绑定
				},
				success:function(data){ //返回成功执行回调函数。
					if(data == -1 ) {
						alert("原密码错误");
					}else {
						alert("密码修改成功，请重新登录！");
						window.close();		
					}
				}
			});	
		}
	</script>
</body>
</html>