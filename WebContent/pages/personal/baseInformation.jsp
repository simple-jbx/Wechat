<%@ page language="java" import="java.util.*,bean.YH" 
pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../../common/header.jsp"%>
<html>
 <head>
  <title>基本信息</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
       <link href="../../static/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
    <link href="../../static/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
    <link href="../../static/assets/css/page-min.css" rel="stylesheet" type="text/css" />   <!-- 下面的样式，仅是为了显示代码，而不应该在项目中使用-->
   <link href="../../static/assets/css/prettify.css" rel="stylesheet" type="text/css" />
   <style type="text/css">
    code {
      padding: 0px 4px;
      color: #d14;
      background-color: #f7f7f9;
      border: 1px solid #e1e1e8;
    }
   </style>
 </head>
 <body>
    <script>
	<%
		YH yh = (YH)request.getSession().getAttribute("yh");
	%>	
	</script>
  <div class="container">
    <div class="detail-page">
      <h2>学生信息</h2>
      <div class="detail-section">  
        <h3>基本信息</h3>
        <div class="row detail-row">
          <div class="span8">
            <label>姓名：</label><span class="detail-text">${yh.getXM()}</span>
          </div>
          <div class="span8">
            <label>学号：</label><span class="detail-text">${yh.getRYDM()}</span>
          </div>
        </div>
        <div class="row detail-row">
          <div class="span8">
            <label>学院：</label><span class="detail-text">${yh.getXYMC()}</span>
          </div>
          <div class="span8">
            <label>专业：</label><span class="detail-text">${yh.getZYMC()}</span>
          </div>
        </div>
        <div class="row detail-row">
          <div class="span8">
            <label>年级：</label><span class="detail-text">${yh.getNJ()}</span>
          </div>
          <div class="span8">
            <label>银行卡号：</label><span class="detail-text">${yh.getYHKH()}</span>
          </div>
        </div>
      </div>
 	 </div>
  </div>
  <script type="text/javascript" src="../../static/assets/js/jquery-1.8.1.min.js"></script>
  <script type="text/javascript" src="../../static/assets/js/bui-min.js"></script>

  <script type="text/javascript" src="../../static/assets/js/config-min.js"></script>
  <script type="text/javascript">
    BUI.use('common/page');
  </script>
<body>
</html>  
