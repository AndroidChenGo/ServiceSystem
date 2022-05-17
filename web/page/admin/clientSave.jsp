<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function checkForm(){
		var id=document.getElementById("id").value;
		var phone=document.getElementById("phone").value;
		var email=document.getElementById("email").value;
		var wechat=document.getElementById("wechat").value;
		var name=document.getElementById("name").value;

		if(id=="" || id==null){
			 	document.getElementById("error").innerHTML="请填写客户编号！";
			 	return false;
		} else if(!isNumber(id)){
			// var i = /[1-9][0-9]{4,}/;
			document.getElementById("error").innerHTML="客户编号必须为数字！";
			return false;
		}
		if(name=="" || name==null){
			document.getElementById("error").innerHTML="请填写客户姓名！";
			return false;
		}else if(!isChinese(name)){
			document.getElementById("error").innerHTML="请输入中文名！";
			return false;
		}
		if(phone==="" && email==="" && wechat===""){
			document.getElementById("error").innerHTML="请至少输入一个联系方式！";
			return false;
		}else if(phone!=="" && phone!=null){
			var p = /^0\d{2,3}-?\d{7,8}$/;
			if(!isNumber(phone))	{
				document.getElementById("error").innerHTML="请检查电话是否正确！";
				return false;
			}
		}else if(email!=="" && email!=null){
			var re = /^([\w\.\-]+)\@(\w+)(\.([\w^\_]+)){1,2}$/;
			const regEmail = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
			if(!re.test(email))	{
				document.getElementById("error").innerHTML="请检查邮箱是否正确！";
				return false;
			}
		}

		return true;
	}
	function myIsNaN(value) {
		return typeof value === 'number' && !isNaN(value);
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
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${user.username!=null }">
				修改客户信息
			</c:when>
			<c:otherwise>
				添加客户
			</c:otherwise>
		</c:choose>
		</div>
		<form action="client?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<div align="center">
					<font id="error" color="red">${error }</font>
				</div>
				<input type="hidden" id="username" name="username" value="${user.username }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>客户编号：</td>
							<td><input type="text" id="id"  name="id" value="${user.id }"  style="margin-top:5px;height:30px;" /></td>
						</tr>

						<tr>
							<td><font color="red">*</font>客户姓名：</td>
							<td><input type="text" id="name"  name="name" value="${user.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
<%--							<td><font color="red">*</font>寝室：</td>--%>
							<td><font color="red"> </font>客户类型：</td>
							<td><input type="text" id="type"  name="type" value="${user.type }"  style="margin-top:5px;height:30px;" /></td>
<%--					<td><input type="text" id="dormName"  name="dormName" value="${student.dormName }"  style="margin-top:5px;height:30px;" /></td>--%>
						</tr>

						<tr>
							<td><font color="red">*</font>电话号码：</td>
							<td><input type="text" id="phone"  name="phone" value="${user.phone }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>邮箱号码：</td>
							<td><input type="text" id="email"  name="email" value="${user.email }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>微信号码：</td>
							<td><input type="text" id="wechat"  name="wechat" value="${user.wechat }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red"></font>客户介绍：</td>
							<td><input type="text" id="introduction"  name="introduction" value="${user.introduction }"  style="margin-top:5px;height:80px;" /></td>
						</tr>

					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='client?action=list'">返回</button>
					</div>
<%--					<div align="center">--%>
<%--						<font id="error" color="red">${error }</font>--%>
<%--					</div>--%>
			</div>
		</form>
</div>