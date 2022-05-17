<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function checkForm(){
		var freelancerId=document.getElementById("freelancerId").value;
		var name=document.getElementById("name").value;
		var start=document.getElementById("start").value;
		var end=document.getElementById("end").value;

		if(name=="" || name==null){
			document.getElementById("error").innerHTML="请填写时间表名称！";
			return false;
		} else if(!isChinese(name)){
			// var i = /[1-9][0-9]{4,}/;
			document.getElementById("error").innerHTML="请输入中文名称！";
			return false;
		}
		if(start=="" || start==null || end=="" || end==null){
			document.getElementById("error").innerHTML="请填写时间表时间！";
			return false;
		}
		if(freelancerId=="" || freelancerId==null){
			document.getElementById("error").innerHTML="自由职业者编号不能为空！";
			return false;
		} else if(!isNumber(freelancerId)){
			// var i = /[1-9][0-9]{4,}/;
			document.getElementById("error").innerHTML="自由职业者编号必须为数字！";
			return false;
		}

		return true;
	}
	function isChinese(str){
		var badChar ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		badChar += "abcdefghijklmnopqrstuvwxyz";
		badChar += "0123456789";
		badChar += " "+"　";//半角与全角空格
		badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//不包含*或.的英文符号
		if(str === ""){
			return false;
		}
		for(var i=0;i<str.length;i++){
			var c = str.charAt(i);//字符串str中的字符
			if(badChar.indexOf(c) > -1){
				return false;
			}
		}
		return true;
	}
	/*
    数字判断函数，返回true表示是全部数字，返回false表示不全部是数字
    */
	function isNumber(str){
		if(str === ""){
			return false;
		}
		var reg = /\D/;
		return str.match(reg)==null;
	}

	$(document).ready(function(){
		$("ul li:eq(4)").addClass("active");

	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${timetable.id!=null && timetable.id!=0}">
				修改时间表信息
			</c:when>
			<c:otherwise>
				添加时间表
			</c:otherwise>
		</c:choose>
		</div>
		<form action="timetable?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<div align="center">
					<font id="error" color="red">${error }</font>
				</div>
				<input type="hidden" id="id" name="id" value="${timetable.id }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>日程名称：</td>
							<td><input type="text" id="name"  name="name" value="${timetable.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>开始时间：</td>
							<td><input type="datetime-local" id="start"  name="start" value="${start }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>结束时间：</td>
							<td><input type="datetime-local" id="end"  name="end" value="${end }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>自由职业者编号：</td>
							<td><input type="text" id="freelancerId"  name="freelancerId" value="${timetable.freelancerId }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red"> </font>工作内容：</td>
							<td><input type="text" id="content"  name="content" value="${timetable.content }"  style="margin-top:5px;height:80px;" /></td>
						</tr>

					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='timetable?action=list'">返回</button>
					</div>
			</div>
		</form>
</div>