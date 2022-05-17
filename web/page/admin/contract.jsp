<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(3)").addClass("active");
	$('.datatable').dataTable( {
		 "oLanguage": {
				"sUrl": "/DormManage/media/zh_CN.json"
		 },
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"aoColumns": [
			null,
			null,
			null,
			null,
			null,
			{ "asSorting": [ ] },
			{ "asSorting": [ ] }
		]
	});
});


window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
	function contractDelete(contractId) {
		if(confirm("您确定要删除这个合同吗？")) {
			window.location="contract?action=delete&contractId="+contractId;
		}
	}
</script>
<style type="text/css">
	.span6 {
		width:0px;
		height: 0px;
		padding-top: 0px;
		padding-bottom: 0px;
		margin-top: 0px;
		margin-bottom: 0px;
	}

</style>
<div class="data_list">
		<div class="data_list_title">
			合同管理
		</div>
	<form name="myForm" class="form-search" method="post" action="contract?action=search" style="padding-bottom: 0px">
	<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='contract?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
                        <option value="name">名称</option>
                        <option value="id" ${searchType eq "id"?'selected':'' }>合同编号</option>
                        <option value="state" ${searchType eq "state"?'selected':'' }>状态</option>
						<option value="clientId" ${searchType eq "clientId"?'selected':'' }>客户编号</option>
						<option value="projectId" ${searchType eq "projectId"?'selected':'' }>项目编号</option>
						<option value="freelancerId" ${searchType eq "freelancerId"?'selected':'' }>自由职业者编号</option>
					</select>
					&nbsp;<input id="searchText" name="searchText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${searchText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
			<div align="center">
				<font id="error" color="red">${error }</font>
			</div>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
<%--					<th>用户名</th>--%>
<%--					<th>密码</th>--%>
					<th>合同编号</th>
					<th>合同名称</th>
					<th>合同状态</th>
					<th>客户编号</th>
					<th>项目编号</th>
					<th>自由职业者编号</th>
					<th>合同内容</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="j" var="contract" items="${contractList }">
					<tr>
<%--						 <td>${i.count+(page-1)*pageSize }</td>--%>
<%--						<td>${user.username }</td>--%>
<%--						<td>${user.password }</td>--%>
						<td>${contract.id }</td>
						<td>${contract.name }</td>
						<td>${contract.state }</td>
						<td>${contract.clientId }</td>
						<td>${contract.projectId }</td>
						<td>${contract.freelancerId }</td>
						<td>${contract.content }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='contract?action=preSave&contractId=${contract.id }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="contractDelete(${contract.id })">删除</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</div>