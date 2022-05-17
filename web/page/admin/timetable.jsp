<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(4)").addClass("active");
	$('.datatable').dataTable( {
		 "oLanguage": {
				"sUrl": "/ServiceManage/media/zh_CN.json"
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
	function timetableDelete(timetableId) {
		if(confirm("您确定要删除这个时间表吗？")) {
			window.location="timetable?action=delete&timetableId="+timetableId;
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
			时间表管理
		</div>
	<form name="myForm" class="form-search" method="post" action="timetable?action=search" style="padding-bottom: 0px">
	<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='timetable?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
                        <option value="name">名称</option>
                        <option value="id" ${searchType eq "id"?'selected':'' }>编号</option>
                        <option value="time" ${searchType eq "time"?'selected':'' }>时间</option>
<%--						<option value="state" ${searchType eq "state"?'selected':'' }>状态</option>--%>
						<option value="content" ${searchType eq "content"?'selected':'' }>内容</option>
						<option value="freelancerId" ${searchType eq "freelancerId"?'selected':'' }>自由职业者编号</option>
					</select>
					<input id="searchTime" name="searchTime" type="datetime-local"  style="width:120px;height: 30px;" class="input-medium search-query" value="${searchTime }">
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
					<th>时间表编号</th>
					<th>时间表名称</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>工作内容</th>
					<th>自由职业者编号</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="j" var="timetable" items="${timetableList }">
					<tr>
						<td>${timetable.id }</td>
						<td>${timetable.name }</td>
						<td>${timetable.startTime }</td>
						<td>${timetable.endTime }</td>
						<td>${timetable.content }</td>
						<td>${timetable.freelancerId }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='timetable?action=preSave&timetableId=${timetable.id }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="timetableDelete(${timetable.id })">删除</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
</div>