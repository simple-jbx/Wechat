function $(elementID){
	return document.getElementById(elementID);
}

window.onload = function(){
	var userID = $('userID');
	var password = $('password');
	var p = document.getElementsByTagName('p');
	var userID_msg = p[0];
	var psd_msg = p[1];
	
	//账号
	userID.onfocus = function(){
		userID_msg.style.display = "inline";
		userID_msg.innerHTML = "请输入8位学号";
	}
	
	userID.onblur = function(){
		var reg = /^\d{8}$/g;
		if(this.value == "")
		{
			userID_msg.innerHTML = "账号不能为空！";
		}
		else if(!reg.test(this.value))
		{
			userID_msg.innerHTML = "请按照格式输入！";
		}
		else
		{
			userID_msg.innerHTML = "";
		}
	}
	
	//密码
	password.onfocus = function(){
		psd_msg.style.display = "inline";
		psd_msg.innerHTML = "密码不能为空！";
	}
	
	password.onblur = function(){
		if(this.value == "")
		{
			psd_msg.innerHTML = "密码不能为空！";
		}
		else
		{
			psd_msg.innerHTML = "";
		}
	}
}