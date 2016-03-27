<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.juvenxu.mvnbook.account.service.AccountService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	AccountService accountService=(AccountService)context.getBean("accountService");
	String captchaKey=accountService.generateCaptchaKey();
%>
<div class="text-field">
	<h2>注册新用户</h2>
	
	<form action="signup" name="signup" method="post">
		<label>账号ID：</label><input type="text" name="id"/><br>
		<label>Email：</label><input type="text" name="email"/><br>
		<label>显示名称：</label><input type="text" name="name"/><br>
		<label>密码：</label><input type="text" name="password"/><br>
		<label>确认密码：</label><input type="text" name="confirm_password"/><br>
		<label>验证码：</label><input type="text" name="captcha_value"/><br>
		<input type="hidden" name="captcha_key" value="<%=captchaKey%>">
		<img  src="<%=request.getContextPath()%>/captcha_image?key=<%=captchaKey%>">
		<button>确认并提交</button>
	</form>
</div>


</body>
</html>