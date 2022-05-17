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

        if(id=="" || id==null){
            document.getElementById("error").innerHTML="请填写自由职业者编号！";
            return false;
        } else if(!isNumber(id)){
            // var i = /[1-9][0-9]{4,}/;
            document.getElementById("error").innerHTML="自由职业者编号必须为数字！";
            return false;
        }
        if(name=="" || name==null){
            document.getElementById("error").innerHTML="请填写自由职业者姓名！";
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
	
	$(document).ready(function(){
		$("ul li:eq(5)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${user.username!=null }">
				修改自由职业者信息
			</c:when>
			<c:otherwise>
				添加自由职业者
			</c:otherwise>
		</c:choose>
		</div>
		<form action="freelancer?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<div align="center">
					<font id="error" color="red">${error }</font>
				</div>
				<input type="hidden" id="username" name="username" value="${user.username }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>工号：</td>
							<td><input type="text" id="id"  name="id" value="${user.id }"  style="margin-top:5px;height:30px;" /></td>
						</tr>

						<tr>
							<td><font color="red">*</font>姓名：</td>
							<td><input type="text" id="name"  name="name" value="${user.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
<%--							<td><font color="red">*</font>寝室：</td>--%>
							<td><font color="red"> </font>类型：</td>
							<td><input type="text" id="type"  name="type" value="${user.type }"  style="margin-top:5px;height:30px;" /></td>
<%--					<td><input type="text" id="dormName"  name="dormName" value="${student.dormName }"  style="margin-top:5px;height:30px;" /></td>--%>
						</tr>

						<tr>
							<td><font color="red">*</font>电话：</td>
							<td><input type="text" id="phone"  name="phone" value="${user.phone }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>邮箱：</td>
							<td><input type="text" id="email"  name="email" value="${user.email }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>微信：</td>
							<td><input type="text" id="wechat"  name="wechat" value="${user.wechat }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red"></font>介绍：</td>
							<td><input type="text" id="introduction"  name="introduction" value="${user.introduction }"  style="margin-top:5px;height:80px;" /></td>
						</tr>

					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='freelancer?action=list'">返回</button>
					</div>
<%--					<div align="center">--%>
<%--						<font id="error" color="red">${error }</font>--%>
<%--					</div>--%>
			</div>
		</form>
</div>