<%@ page language="java" pageEncoding="UTF-8" import="bean.YH"%>
<%@include file="../../common/header.jsp"%>
<html>
<!-- 勤工助学工资发放表   -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../static/js/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="../../static/js/bootstrap/css/bootstrap-table.min.css"
	rel="stylesheet" type="text/css">
<script src="../../static/js/jQuery.js"></script>
<script src="../../static/js/bootstrap/js/bootstrap.min.js"></script>
<script src="../../static/js/bootstrap/js/bootstrap-table.min.js"></script>
<script src="../../static/js/bootstrap/js/bootstrap-table-zh-CN.js"></script>

<c:if test= "${!empty sessionScope.yh}">   
   <c:if test="${yh.getRYDM() == 'root'}">  
    <link href="../../static/js/bootstrap/css/bootstrap-editable.css"
	rel="stylesheet" type="text/css">
	<link href="../../static/js/bootstrap/css/bootstrap-fileinput.min.css"
	rel="stylesheet" type="text/css">
	<script src="../../static/js/bootstrap/js/bootstrap-editable.js"></script>
	<script src="../../static/js/bootstrap/js/bootstrap-table-editable.js"></script>
	<script src="../../static/js/bootstrap/js/bootstrap-fileinput.min.js"></script>
	<script src="../../static/js/bootstrap/js/bootstarp-fileinout-zh-CN.js"></script>
	<script src="../../static/js/bootstrap/js/bootstrap-table-export.js"></script>
	<script src="../../static/js/tableExport/libs/FileSaver/FileSaver.min.js"></script>
	<script src="../../static/js/tableExport/libs/js-xlsx/xlsx.core.min.js"></script>
	<script src="../../static/js/tableExport/tableExport.js"></script>
   </c:if>
</c:if>
<title>勤工助学工资发放表</title>
</head>
<body>

	<!-- bootstrap table -->
	<table id="table"></table>
	
	<c:if test= "${!empty sessionScope.yh}">        
   	<c:if test="${yh.getRYDM() == 'root'}">  
		<!-- 新增按钮 -->
		<div id="toolbar" class="btn-group">
			<button id="btn_add" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button id="btn_clear" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>清空
			</button>
		</div>
		
		<!-- 上传区域  -->
		<div class="htmleaf-container" id="upLoad">
			<div class="container kv-main" align="right">
				<!-- <div class="page-header">  
	           <h2>单张上传 <small></h2>  
	           </div> -->
				<!-- <form enctype="multipart/form-data" id="uploadForm"  method = "post"> -->
				<input id="fileUpload" class="file" type="file" name="fileUpload"
					multiple data-show-preview="true">
				<!-- </form> -->
			</div>
		</div>
		
		<!-- 新增按钮动作 -->
		<script>
			$("#btn_add").click(function () {
				 var index = $('#table').bootstrapTable().length;
						$('#table').bootstrapTable('insertRow', {
			            index: 0,
			            row: {yrdw:'-',gwmc:'-',rydm:'-',xm:'-',yhkh:'',xymc:'',ffny:'',je:''}
			        })
			})
		</script>
		
		<script>
			$("#btn_clear").click(function () {
				 $.ajax({
	                    type: "post",
	                    url : '<%=session.getAttribute("basePath")%>/QGZXController.do',
	                    data: {
	                    	 "op": "clear"
	                    },
	                    dataType: 'JSON',
	                    success: function (data, status) {
	                        if (status == "success") {
	                            alert('操作成功');  
	                            window.location.reload();
	                        }
	                    },
	                    error: function () {
	                        alert('操作失败');
	                        window.location.reload();
	                    },
	                });				
			})
		</script>
		
		<!-- 设置上传相关参数 -->
   		<script>  
		    $("#fileUpload").fileinput({  
		        language : "zh",//设置语言  
		        uploadUrl: "<%=session.getAttribute("basePath")%>/upLoadServlet.do?tableName=qgzx",//上传地址  
		        textEncoding: 'utf-8',
		        uploadAsync: true,//同步还是异步  
		        showCaption: true,//是否显示标题  
		        showUpload: true,//是否显示上传按钮  
		        showClose: false,//是否显示右上角的x
		        //showPreview: false,//是否显示预览区域
		        dropZoneEnabled: false,
		        browseClass: "btn btn-primary", //按钮样式   
		        allowedFileExtensions : ['xls','xlsx'],//接收的文件后缀  
		        //allowedFileTypes: ['Excel','Microsoft Excel'],//接收的文件类型
		        maxFileCount: 10,//最大上传文件数限制  
		        overwriteInitial: false,  
		        maxFileSize: 10000,   
		        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		        previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',   
		        enctype: 'multipart/form-data',  
		        /* allowedPreviewTypes: null, */  
		        previewFileIconSettings: {  
		               'docx': '<i class="glyphicon glyphicon-file"></i>',  
		               'xlsx': '<i class="glyphicon glyphicon-file"></i>',  
		               'pptx': '<i class="glyphicon glyphicon-file"></i>',  
		               'jpg': '<i class="glyphicon glyphicon-picture"></i>',  
		               'pdf': '<i class="glyphicon glyphicon-file"></i>',  
		               'zip': '<i class="glyphicon glyphicon-file"></i>' ,
		               'xls': '<i class="glyphicon glyphicon-file"></i>',
		           }  
		    });
		</script>	
	</c:if>
	</c:if>
	<c:if test= "${!empty sessionScope.yh}">
    <c:if test="${yh.getRYDM() == 'root'}">  
	<script>
	//操作栏的格式化 
	$(function(){
		$('#table').bootstrapTable({
		ajax : function (request) {
	        $.ajax({
	        	type:"post",//请求方式
	            data:{
	            	"op" : "getAll"
	            },
	            url : '<%=session.getAttribute("basePath")%>/QGZXController.do',
				dataType:"json",
	            success : function (msg) {	
					request.success({
	                    row : msg
	                });
	                $('#table').bootstrapTable('load', msg);
	            },
				error:function(){
					alert("载入出错");
				}
	        });
		},
	
			
			toolbar:'#toolbar',
			singleSelect:true,
			clickToSelect:true,	
			editable: true,
			sortable: true, 
			sortName: 'id',
			sortOrder: "asc",
			pageSize: 10,
			pageNumber: 1,
			pageList: "[10, 15, 25, 50]",
			undefinedText: '-',
			//showToggle: true,
			showRefresh: true,
			//showColumns: true, //是否显示内容下拉框
			//showPaginationSwitch: true,隐藏显示分页
			search: true, //搜索框
			pagination:true, //底部显示分页条
			strictSearch: false, //模糊搜索
			uniqueId: "id", //每一行的唯一标识，一般为主键列
			//striped: true,  
            cache: false,
            showExport: true,  //是否显示导出按钮  
            buttonsAlign:"right",  //按钮位置  
            exportDataType:'all',
            exportTypes:['excel', 'xlsx'],  //导出文件类型
			columns: [{
				field: "state",
				checkbox:true,
			},{
				field: "id",
				visible: false
			},{
				field: 'rowid',
				title: '序号',
				formatter: function(value, row, index) {
					row.rowid = index;
					return index+1;
				}			
			},{
				field: 'yrdw',
				title: '用人单位',
				editable: {
					type: 'text',
					mode: 'inline'			
				}		
			},{
				field: 'gwmc',
				title: '岗位名称',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline',
                    
				}
			},{
				field: 'rydm',
				title: '学号',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline',
                    
				}
			},{
				field: 'xm',
				title: '姓名',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline'
                }  
			},{
				field: 'yhkh',
				title: '银行卡号',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline'
                }  
			},{
				field: 'xymc',
				title: '学院名称',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline'
                }  
			},{
				field: 'ffny',
				title: '发放年月',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline'
                }  
			},{
				field: 'je',
				title: '金额',
				switchable: true,
				editable: {  
                    type: 'text', 
                    mode: 'inline'
                }  
			},{
				field: 'cz',
				title: '操作',
				//formatter:actionFormatter,
				width: 100,
				visible: true,
				formatter: function (value, row, index) {  
		                   var s = '<button class = "save" href="javascript:void(0)">保存</button>&nbsp&nbsp';  
		                   var b = '<button class = "delete" href="javascript:void(0)">删除</button>&nbsp&nbsp';
		                   return s + b;  
                },  
		        events: 'operateEvents' 
			}],
			 onEditableSave: function (field, row, oldValue, $el) {
			 }
		});
			
			window.operateEvents = {
					 'click .save': function (e, value, row, index) {
						 //alert(row);
						 $.ajax({
			                    type: "post",
			                    url : '<%=session.getAttribute("basePath")%>/QGZXController.do',
			                    data: {
			                    	"row": JSON.stringify(row),
			                    	 "op": "update"
			                    },
			                    dataType: 'JSON',
			                    success: function (data, status) {
			                        if (status == "success") {
			                            alert('保存成功');
			                            $('#table').bootstrapTable('updateRow',{
				                            index:row.rowid,
				                            row:row
				                      })	
			                        }
			                    },
			                    error: function () {
			                        alert('保存失败');
			                        window.location.reload();
			                    },
			                });
					 },
			 
			 'click .delete': function (e, value, row, index) {
				 if(window.confirm("确定删除?")) {
				 $.ajax({
	                   type: "post",
	                   url : '<%=session.getAttribute("basePath")%>/QGZXController.do',
	                   data: {
	                	  "ID": row.id,
	                      "op": "delete"
	                   },
	                   dataType: 'json',
	                   success: function (data, status) {
	                       if (status == "success") {
	                           alert('删除成功');
	                           $('table').bootstrapTable('remove', {
	                               field: 'id',
	                               values: [row.id]
	                             });
	                       }
	                   },
	                   error: function () {
	                       alert('删除失败');
	                       window.location.reload();
	                   },
	               });
					 
				 }
			 }
		}
	})
</script>
</c:if>
</c:if>
</body>
</html>