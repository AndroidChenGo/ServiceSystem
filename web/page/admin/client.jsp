<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(1)").addClass("active");
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
	function clientDelete(clientId) {
		if(confirm("您确定要删除这个客户吗？")) {
			window.location="client?action=delete&userId="+clientId;
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
			客户管理
		</div>
	<form name="myForm" class="form-search" method="post" action="client?action=search" style="padding-bottom: 0px">
	<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='client?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
                        <option value="name">姓名</option>
                        <option value="id" ${searchType eq "id"?'selected':'' }>工号</option>
						<option value="type" ${searchType eq "type"?'selected':'' }>类型</option>
                        <option value="introduction" ${searchType eq "introduction"?'selected':'' }>介绍</option>
					</select>
<%--					<select id="clientType" name="clientType" style="width: 80px;">--%>
<%--                        <option value="">类型</option>--%>
<%--                        <option value="初级" ${clientType eq "初级"?'selected':'' }>初级</option>--%>
<%--                        <option value="中级" ${clientType eq "中级"?'selected':'' }>中级</option>--%>
<%--						<option value="高级" ${clientType eq "高级"?'selected':'' }>高级</option>--%>
<%--                        <option value="特级" ${clientType eq "特级"?'selected':'' }>特级</option>--%>
<%--					</select>--%>
					<input id="searchText" name="searchText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${searchText }">
					<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
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
					<th>工号</th>
					<th>姓名</th>
					<th>类型</th>
					<th>电话</th>
					<th>邮箱</th>
					<th>微信</th>
					<th>介绍</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="j" var="user" items="${userList }">
					<tr>
<%--						 <td>${i.count+(page-1)*pageSize }</td>--%>
<%--						<td>${user.username }</td>--%>
<%--						<td>${user.password }</td>--%>
						<td>${user.id }</td>
						<td>${user.name }</td>
						<td>${user.type }</td>
						<td>${user.phone }</td>
						<td>${user.email }</td>
						<td>${user.wechat }</td>
						<td>${user.introduction }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='client?action=preSave&userId=${user.id }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="clientDelete(${user.id })">删除</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
<%--		<div align="center"><font color="red">${error }</font></div>--%>
<%--		 <div class="pagination pagination-centered">--%>
<%--			<ul>--%>
<%--				${pageCode }--%>
<%--			</ul>--%>
<%--		</div>--%>
</div>