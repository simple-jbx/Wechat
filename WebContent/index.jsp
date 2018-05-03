<%@ page language="java" import="java.util.*,bean.YH" 
pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="common/header.jsp"%>
<html>
 <head>
  <title>陕西师范大学学生资助管理中心自助服务平台 V1.0</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link href="static/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
   <link href="static/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
   <link href="static/assets/css/main-min.css" rel="stylesheet" type="text/css" />
   <script type="text/javascript" src="static/assets/js/jquery-1.8.1.min.js"></script>
   <script type="text/javascript" src="static/assets/js/bui-min.js"></script>
   <script type="text/javascript" src="static/assets/js/config.js"></script>
 </head>
    
<body>
	<script>
		<%
			YH yh = (YH)request.getSession().getAttribute("yh");
		%>	
	</script>
  <div class="header">  
      <div class="dl-title">
        <a href="">陕西师范大学学生资助管理中心自助服务平台  V1.0</a>
      </div>

    <div class="dl-log">当前用户:${yh.getRYDM()}(${yh.getXM()})<span class="dl-log-user"></span>
    <a href="javascript:changePassword()" title="修改密码" class="dl-log-quit">[修改密码]</a>
    <span class="dl-log-user"></span>
    <a href="javascript:logout()" title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
  </div>
   <div class="content">
    <div class="dl-main-nav">
      <div class="dl-inform"><div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div></div>
      <ul id="J_Nav"  class="nav-list ks-clear">
        <!--  <li class="nav-item dl-selected"><div class="nav-item-inner nav-home">我需留意</div></li>-->
        <li class="nav-item"><div class="nav-item-inner nav-supplier">个人信息查询</div></li>
        <c:if test="${yh.getRYDM() == 'root'}">
        	<li class="nav-item"><div class="nav-item-inner nav-order">后台管理</div></li>
      	</c:if>
      </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
   </div>

  <script>
  
    BUI.use('common/main',function(){
      var config = [{
          //id:'main', 
          //homePage : 'gl',
          //menu:[{
              //text:'我需留意',
              //items:[
                //{id:'gl',text:'概览',href:'main/code.html',closeable : false},
                //{id:'main-menu',text:'查看消息',href:'backstage/BGHZ.jsp'},
              //]
            //}]
          //},{
            id:'form',
            homePage : 'baseInformation',
            menu:[{
                text:'信息管理',
                items:[
                  {id:'baseInformation',text:'基本信息',href:'pages/personal/baseInformation.jsp',closeable:false},
                  ]
              },{
                text:'奖助信息',
                collapsed:true,
                items:[
                    {id:'grantInformation',text:'奖助信息查询',href:'pages/personal/grantInformation.jsp'}
                ]
              }
      		]
          },
          {
          <c:if test= "${!empty sessionScope.yh}">        
          <c:if test="${yh.getRYDM() == 'root'}">  
            id:'htgl',
            homePage : 'information',
            menu:[{
                text:'通知管理',
                items:[
                	{id:'information',text:'通知信息',href:'',closeable : false},
                ]
              },{
                  text:'用户管理',
                  collapsed:true,
                  items:[
                	   	{id:"yhgl",text:"用户管理",href:'pages/admin/YHGL.jsp'},          	
                  ]
                },{
                text:'奖助数据管理',
                collapsed:true,
                items:[
                   	{id:"gjzxj",text:"国家助学金发放表",href:'pages/admin/GJZXJAdmin.jsp'},
                   	{id:"qgzx",text:"勤工助学工资发放表",href:'pages/admin/QGZXAdmin.jsp'},
                	{id:"mfsfsshbz",text:"免费师范生生活补助发放表",href:'pages/admin/MFSFSSHBZAdmin.jsp'}
                ]
              }  
            ]
          </c:if>
          </c:if>
          }
          ];
      new PageUtil.MainPage({
        modulesConfig : config
      });
    });
  </script>

   <script>
  	function logout() {
	  if(window.confirm("是否确认退出?")) {
		  $.ajax({
				type:"post",//请求方式
				url:"./logoutController.do",//请求地址
				error:function(){//如果出错了，将事件重新绑定
					alert("Error!");
				},
		 		success:function(){ //返回成功执行回调函数。
		 			window.location.href = "login.jsp";
		 		}
		  })
  	}
  }
  </script>
  
   <script>
  	function changePassword() {
  		window.open("pages/common/changePassword.jsp","","width=400px,height=200,top=200,left=300,status=no,help=no");
  }
  </script>
 </body>

</html>
