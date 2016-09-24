<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/commons.jspf"%> 
<!DOCTYPE html>
<html lang="en">
	<head> 
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
	</head>
	
    <%-- <script type="text/javascript"  src="${ctxStatic}/js_system/tbclass/tbclass_add.js"></script>  --%>
	
<script type="text/javascript">

function save(){
	var validForm = FormValid.validbyFormId($("#Form_user_add")); 
	if(validForm.check()){ 
		$("#zhongxin").hide();
		$("#zhongxin2").show(); 
		$.post(ctx+'/goodClass/add',
		  {
			F_ClsName:$("#F_ClsName").val(),
			F_PClsId:$("#F_PClsId").val(),
			F_Order:$("#F_Order").val(),
			F_Status:$("#F_Status").val()
		  },function(data,status){ 
			  if(data.result=="ok"){  
					//$("#userForm").submit(); 
					console.info(document.getElementById("userForm")); 
					top.Dialog.close();   
					return;
			  }   
		});
	}
} 
</script>	
<body> 
	<form action="${ctx}/goodClass/add.do" name="Form" id="Form_user_add" method="post">
  
        <input type="hidden" name="F_PClsId" id="F_PClsId" value="${F_PClsId}">
        <input type="hidden" name="F_Order" id="F_Order" value="1">
        
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品类型名:</td>
				<td><input type="text"  style="height:30px" name="F_ClsName" id="F_ClsName" value="${pd.F_ClsName}" maxlength="32" placeholder="这里输入商品类型" datatype="*"  nullmsg="这里输入商品类型" title="商品类型"  required errormsg="请输入商品类型"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">是否可用:</td>
				<td>
					<select   datatype="*" name="F_Status" id="F_Status" value="${pd.F_Status}" maxlength="32">
					   <option value="0">是</option>
					   <option value="1">否</option>  
					</select>
				</td>
			</tr>
	 
			
		 
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				  
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form> 
</body>


</html>