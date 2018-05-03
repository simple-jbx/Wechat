<%@ page language="java" import="java.util.*,bean.YH, bean.GJZXJ,bean.MFSFSSHBZ, bean.QGZX,
	service.commonService" 
pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../../common/header.jsp"%>
<html>
 <head>
  <title>奖助信息</title>
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
		GJZXJ gjzxj = null;
		QGZX qgzx = null;
		MFSFSSHBZ mfsfsshbz = new MFSFSSHBZ();
	    if(yh != null) {
	    	gjzxj = commonService.getDataByRydm(GJZXJ.class, yh.getRYDM());
			qgzx = commonService.getDataByRydm(QGZX.class, yh.getRYDM());	
			mfsfsshbz = commonService.getDataByRydm(MFSFSSHBZ.class, yh.getRYDM());
	    }
		//System.out.println(gjzxj.toJson());
		if(gjzxj == null)
			gjzxj = new GJZXJ();
		if(qgzx == null)
			qgzx = new QGZX();
		if(mfsfsshbz == null)
			mfsfsshbz = new MFSFSSHBZ();
		request.setAttribute("gjzxj", gjzxj);	
		request.setAttribute("qgzx", qgzx);
		request.setAttribute("mfsfsshbz", mfsfsshbz);
	%>	
	</script>
  <div class="container">
    <div class="detail-page">
      <h2>国家助学金</h2>
      <div class="detail-section">  
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
            <label>金额：</label><span class="detail-text">${gjzxj.getJE()}</span>
          </div>
          <div class="span8">
            <label>等级：</label><span class="detail-text">${gjzxj.getDJ()}</span>
          </div>
        </div>
        <div class="row detail-row">
          <div class="span8">
            <label>银行卡号：</label><span class="detail-text">${gjzxj.getYHKH()}</span>
          </div>
        </div>
      </div>
 	 </div>
 	 <hr>
 	 <div class="detail-page">
 	  <h2>勤工助学</h2>
      <div class="detail-section">  
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
            <label>用人单位：</label><span class="detail-text">${qgzx.getYRDW()}</span>
          </div>
          <div class="span8">
            <label>岗位名称：</label><span class="detail-text">${qgzx.getGWMC()}</span>
          </div>
        </div>
        <div class="row detail-row">
          <div class="span8">
            <label>金额：</label><span class="detail-text">${qgzx.getJE()}</span>
          </div>
          <div class="span8">
            <label>银行卡号：</label><span class="detail-text">${qgzx.getYHKH()}</span>
          </div>
        </div>
        <div class="row detail-row">
          <div class="span8">
            <label>发放年月：</label><span class="detail-text">${qgzx.getFFNY()}</span>
          </div>
        </div>
      </div>
      </div>
      
      <hr>
 	 <div class="detail-page">
 	  <h2>免费师范生生活补助</h2>
      <div class="detail-section">  
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
            <label>金额：</label><span class="detail-text">${mfsfsshbz.getJE()}</span>
          </div>
          <div class="span8">
            <label>银行卡号：</label><span class="detail-text">${mfsfsshbz.getYHKH()}</span>
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
