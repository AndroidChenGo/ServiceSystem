<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="cn.domain.model.User" %>
<%
	if(request.getAttribute("user")==null){
		String userName=null;
		String password=null;
		String userType=null;
		String remember=null;
		
		Cookie[] cookies=request.getCookies();
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("serviceuser")){
				userName=cookies[i].getValue().split("-")[0];
				password=cookies[i].getValue().split("-")[1];
				userType=cookies[i].getValue().split("-")[2];
				remember=cookies[i].getValue().split("-")[3];
			}
		}
		
		if(userName==null){
			userName="";
		}
		
		if(password==null){
			password="";
		}
		
		if(userType==null){
			userType="";
		} else{
			pageContext.setAttribute("user", new User(userName,password));
			pageContext.setAttribute("userType", userType);
		}
		
		if("yes".equals(remember)) {
			pageContext.setAttribute("remember", 1);
		}
		
	}
%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>服务出租系统登录</title>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	function checkForm() {
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		var userTypes = document.getElementsByName("userType");
		var userType = null;
		for(var i=0;i<userTypes.length;i++) {
			if(userTypes[i].checked) {
				userType=userTypes[i].value;
				break;
			}
		}
		if (userName == null || "" == userName) {
			document.getElementById("error").innerHTML = "用户名不能为空";
			return false;
		}
		if (password == null || "" == password || !hasNotChinese(password)) {
			document.getElementById("error").innerHTML = "密码不能为空或含中文";
			return false;
		}
		if (userType == null || "" == userType) {
			document.getElementById("error").innerHTML = "请选择用户类型";
			return false;
		}
		return true;
	}
	$('[id=register]').click(function(){

		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		var userTypes = document.getElementsByName("userType");
		var userType = null;
		for(var i=0;i<userTypes.length;i++) {
			if(userTypes[i].checked) {
				userType=userTypes[i].value;
				break;
			}
		}
		if (userName == null || "" == userName) {
			document.getElementById("error").innerHTML = "用户名不能为空";
			return ;
		}
		if (password == null || "" == password || !hasNotChinese(password)) {
			document.getElementById("error").innerHTML = "密码不能为空或含中文";
			return ;
		}
		if (userType == null || "" == userType) {
			document.getElementById("error").innerHTML = "请选择用户类型";
			return ;
		}
		window.location="register";

	});
	function register(){
		<%--document.myForm.action="${pageContext.request.contextPath}/register";--%>
		<%--document.myForm.onsubmit();--%>
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		var userTypes = document.getElementsByName("userType");
		var userType = null;
		for(var i=0;i<userTypes.length;i++) {
			if(userTypes[i].checked) {
				userType=userTypes[i].value;
				break;
			}
		}
		if (userName == null || userName == "") {
			document.getElementById("error").innerHTML = "用户名不能为空";
			return ;
		}
		if (password == null || password == "" || !hasNotChinese(password)) {
			document.getElementById("error").innerHTML = "密码不能为空或含中文";
			return ;
		}
		if (userType == null || userType == "") {
			document.getElementById("error").innerHTML = "请选择用户类型";
			return ;
		}
		window.location="register";
	}
	function hasNotChinese(str){
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
			if(!(badChar.indexOf(c) > -1)){
				return false;
			}
		}
		return true;
	}
</script>

<style type="text/css">
	  body {
        padding-top: 200px;
        padding-bottom: 40px;
        /*background-image: url('images/bg.jpg');*/
        background-position: center;
		background-repeat: no-repeat;
		background-attachment: fixed;
      }
      
      .radio {
      	padding-top: 10px;
       	padding-bottom:10px;
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 0px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }
</style>

</head>
<body>
<div class="container">
      <form name="myForm" class="form-signin" action="login" method="post" onsubmit="return checkForm()">
        <h2 class="form-signin-heading"><font color="gray">服务出租系统</font></h2>
        <input id="username" name="username" value="${user.username }" type="text" class="input-block-level" placeholder="用户名...">
        <input id="password" name="password" value="${user.password }" type="password" class="input-block-level" placeholder="密码..." >
        <label class="radio inline">
      	  	<input id="admin" type="radio" name="userType" value="admin"  checked/> 系统管理员
		</label>
		<label class="radio inline">
			<input id="freelancer" type="radio" name="userType" value="freelancer" ${userType=="freelancer"?'checked':''} /> 自由职业者
		</label>
		<label class="radio inline">
			<input id="client" type="radio" name="userType" value="client"  ${userType=="client"?'checked':''}/> 客户
		</label>
        <label class="checkbox">
          <input id="remember" name="remember" type="checkbox" value="remember-me" ${remember==1?'checked':''}>记住我 &nbsp;&nbsp;&nbsp;&nbsp; <font id="error" color="red">${error }</font>  
        </label>
<%--		<button class="btn btn-large btn-primary" type="button" onclick="register();">注册</button>--%>

        <button class="btn btn-large btn-primary" type="submit">登录</button>

        <button class="btn btn-large btn-primary" type="button" >重置</button>

		  <p align="center" style="padding-top: 15px;">  <a> </a></p>

	  </form>
</div>
</body>
</html>