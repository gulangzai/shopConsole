<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%> 
 
<!DOCTYPE html>
<html lang="en">
<head> 
<%@ include file="/WEB-INF/jsp/commons/commons.jspf"%>  
 <style type="text/css"> 
		table.imagetable {
		font-family: verdana, arial, sans-serif;
		font-size: 14px;
	}
	
	table.imagetable th {
		padding: 8px;
	}
	
	table.imagetable td {
		padding:  8px;
	}
</style>
</head>
<script>
var page_currentPage = '${page.currentPage}';
</script>	

<body>

 
<script type="text/javascript" src="${ctxStaticB}/js_system/tbuser/tbuser_list.js"> </script>

<div class="container-fluid" id="main-container">

<div id="page-content" class="clearfix">
						
  <div class="row-fluid"> 
  
		<div class="col-sm-12" id="inside">
	
			<!-- 检索  -->
			<form class="form-inline" action="${ctx}/tbUserController/list.do"     method="post" name="Form" id="Form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
					 
				     <div class="form-group"  style="border:1px solid #F2F2F2;">
				      
				            用户名：  <input class="form-control" name="KEYWORD" id="KEYWORD"  type="text"   style="width:120px;border-radius:8px 8px 8px 8px;border:1px solid #d1d2d9;" placeholder="用户名"/>
				                           日期：
						   	<input class="form-control date-picker" name="lastLoginStart" id="lastLoginStart" value="${pd.lastLoginStart}" type="text" data-date-format="yyyy-mm-dd"  style="width:120px;border-radius:8px 8px 8px 8px;border:1px solid #d1d2d9;" placeholder="开始日期"/>
						     	~
				            <input class="form-control date-picker" type="text" data-date-format="yyyy-mm-dd"  style="width:120px;border-radius:8px 8px 8px 8px;border:1px solid #d1d2d9;" placeholder="结束日期" name="lastLoginEnd" id="lastLoginEnd" value="${pd.lastLoginEnd}" />
					     	
					  	<button class="btn  btn-link btn-sm" type="button"   onclick="search();">
					  	   <i class="colorshow  ace-icon glyphicon glyphicon-search"></i>
					  	   <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
					  	</button>
					  	<button class="btn btn-link btn-sm" type="button"   onclick="toExcel();"> 
					      	 <i  class="colorshow  ace-icon glyphicon glyphicon-download-alt"></i>
					     	 <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">下载</span> 
			            </button> 
				   </div>  
		 
			<!-- 检索  --> 
		
			<table id="table_report" class="table table-striped table-bordered table-hover  imagetable">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">序号</th>
						<th class="center">用户名</th>	
						<th class="center">真实姓名</th>
						<th class="center"    formatter="showSex()">性别</th>
						<th class="center">邮件</th>
						<th class="center">电话</th>
						<th class="center">操作</th>
					</tr>
				</thead> 				
				<tbody> 
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}"> 
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.F_USER_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${(page.currentPage-1)*page.showCount+vs.index+1}</td>
										<td  style="text-align:center">${var.F_UserName}</td>
										<td>${var.F_RealName}</td>
										<td  style="text-align:center"><c:if test="${var.F_Sex == '0'}">男</c:if><c:if test="${var.F_Sex == '1'}">女</c:if></td>
										<td  style="text-align:center">${var.F_Email}</td>
										<td  style="text-align:center">${var.F_Mobile}</td>
										
							   <td  class="center" style="width:60px;">   
							       <div class='hidden-phone visible-desktop btn-group'>
							          <a class='btn btn-mini btn-info' href="javascript:void(0)"  title="授权" onclick="toRole('${var.F_UserName}');">
									    <i class="icon-key"></i>  
								     </a>
									<a  class='btn btn-mini btn-info'  href="javascript:void(0)" title="编辑" onclick="edit('${var.F_USER_ID}');">
									   <i class='icon-edit'></i> 
									</a>
									<a class='btn btn-mini btn-danger'   title="删除" onclick="del('${var.F_USER_ID}');">
										<i class='icon-trash'></i>
									</a>
			                      </div>
								</td>  
							</tr>
						
						</c:forEach> 
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose> 
				</tbody>
				
			</table>
			
	 
			<div class="page-header position-relative"> 
				<table style="width:100%;">
					<tr>
						<td style="vertical-align:top;">
							<c:if test="${QX.add == 1 }">
							<a class="btn btn-small btn-success" onclick="add();">新增</a>
							</c:if>
							<c:if test="${QX.del == 1 }">
							<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
							</c:if>
						</td>
						<td style="vertical-align:top;">
						<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div>
						</td>
					</tr>
				</table>
			</div> 
		 </form>
	</div> <!-- /row --> 
	
	
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
 <div id="XXXM"></div> 
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>   
<script>
var ctx = "${ctx}";
$(top.hangge());
 

function toRole(F_USER_ID){
 
  	$("#XXXM").attr("class","modal");
	$("#XXXM").removeData("bs.modal");
	$("#XXXM").empty();
	$("#XXXM").modal({
		backdrop: 'static'
	}); 
	 
	$.get("${ctx}/tbRoleController/toUserRole.do?F_USER_ID="+F_USER_ID,function(data){  
		$("#XXXM").empty();
		$("#XXXM").html("");
		$("#XXXM").html(data); 
	});
}

//批量操作
function makeAll(msg){
	bootbox.confirm(msg, function(result) {
		if(result) {
			var str = '';
			var emstr = '';
			var phones = '';
			for(var i=0;i < document.getElementsByName('ids').length;i++)
			{
				  if(document.getElementsByName('ids')[i].checked){
				  	if(str=='') str += document.getElementsByName('ids')[i].value;
				  	else str += ',' + document.getElementsByName('ids')[i].value;
				  	
				  	if(emstr=='') emstr += document.getElementsByName('ids')[i].id;
				  	else emstr += ';' + document.getElementsByName('ids')[i].id;
				  	
				  	if(phones=='') phones += document.getElementsByName('ids')[i].alt;
				  	else phones += ';' + document.getElementsByName('ids')[i].alt;
				  }
			}
			if(str==''){
				bootbox.dialog("您没有选择任何内容!", 
					[
					  {
						"label" : "关闭",
						"class" : "btn-small btn-success",
						"callback": function() {
							//Example.show("great success");
							}
						}
					 ]
				);
				
				$("#zcheckbox").tips({
					side:3,
		            msg:'点这里全选',
		            bg:'#AE81FF',
		            time:8
		        });
				
				return;
			}else{
				if(msg == '确定要删除选中的数据吗?'){
					top.jzts();
					$.ajax({
						type: "POST",
						url: '${ctx}/tbUserController/deleteAll.do?tm='+new Date().getTime(),
				    	data: {USER_IDS:str},
						dataType:'json',
						//beforeSend: validateData,
						cache: false,
						success: function(data){
							 $.each(data.list, function(i, list){
									nextPage(${page.currentPage});
							 });
						}
					});
				}else if(msg == '确定要给选中的用户发送邮件吗?'){
					sendEmail(emstr);
				}else if(msg == '确定要给选中的用户发送短信吗?'){
					sendSms(phones);
				}
				
				
			}
		}
	});
}

</script>

</body>
</html>

