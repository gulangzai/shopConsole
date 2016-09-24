<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<!-- 表单 --> 
		<link rel="stylesheet" href="${ctxStatic}/css/datepicker.css" />
		<link rel="stylesheet" href="${ctxStatic}/plugins/validform/css/form_validate.css" />
		<link rel="stylesheet" href="${ctxStatic}/plugins/validform/css/form_validate_f.css" />
		<!-- 新增jqGrid table 样式 -->
		
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 --> 
		<script type="text/javascript" src="static/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
		<script type="text/javascript" src="${ctxStatic}/plugins/validform/js/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="${ctxStaticB}/js/common/common.js"></script>
		
		<link rel="stylesheet" type="text/css" href="${ctx}/plugins/webuploader/webuploader.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/plugins/webuploader/style.css" />
		
		 
	</head>
	
		<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
	<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
	<script type="text/javascript"  src="static/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript"  src="${ctxStaticB}/js_system/tbuser/tbuser_add.js"></script> 
	
	
<body> 
	<form action="${ctx}/tbUserController/${msg}.do" name="Form" id="Form_user_add" method="post">
 
        <input type="hidden" name="F_Deleted" id="F_Deleted" value="0"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户名:</td>
				<td><input type="text" name="F_UserName" id="F_UserName" value="${pd.F_UserName}" maxlength="32" placeholder="这里输入用户名" datatype="*"  nullmsg="请输入用户名" title="用户名"  required errormsg="请输入用户名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">真实姓名:</td>
				<td><input type="text" datatype="*" name="F_RealName" id="F_RealName" value="${pd.F_RealName}" maxlength="32" placeholder="这里输入真实姓名" nullmsg="请输入真实姓名" title="真实姓名" required errormsg="请填写真实姓名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">性别:</td>
				<td><select type="number" name="F_Sex" id="F_Sex" value="${pd.F_Sex}" maxlength="32" placeholder="这里输入性别" title="性别" required>
				    <option value="0">男</option>
				    <option value="1">女</option>
				</select></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">邮件:</td>
				<td><input type="text" datatype="e" name="F_Email" id="F_Email" value="${pd.F_Email}" maxlength="32" placeholder="这里输入邮件" nullmsg="请输入邮件" title="邮件" required/></td>
			    
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">电话:</td>
				<td><input type="text" datatype="m" name="F_Mobile" id="F_Mobile" value="${pd.F_Mobile}" maxlength="32" placeholder="这里输入电话" nullmsg="请输入电话" title="电话" required/></td>
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