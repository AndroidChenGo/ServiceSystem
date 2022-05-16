<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function checkForm(){
		var name=document.getElementById("name").value;
		var freelancerId=document.getElementById("freelancerId").value;
		var email=document.getElementById("email").value;
		var wechat=document.getElementById("wechat").value;

		if("" == name || name ==null || !isChinese(name)){
			document.getElementById("error").innerHTML="时间表名不能为空或含非中文字符！";
			return false;
		}
		if("" == freelancerId || freelancerId==null){
			 	document.getElementById("error").innerHTML="请填写自由职业者编号！";
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
							<td><font color="red">*</font>名称：</td>
							<td><input type="text" id="name"  name="name" value="${timetable.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>时间：</td>
<%--							<label for="start_time"></label><input type="date" id="start_time" value="${timetable.name }"/>--%>
							<td><input type="date" id="time"  name="time" value="${timetable.time }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>自由职业者编号：</td>
							<td><input type="text" id="freelancerId"  name="freelancerId" value="${timetable.freelancerId }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red"> </font>内容：</td>
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