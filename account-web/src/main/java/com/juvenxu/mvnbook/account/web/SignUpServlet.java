package com.juvenxu.mvnbook.account.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.juvenxu.mvnbook.account.service.AccountService;
import com.juvenxu.mvnbook.account.service.AccountServiceException;
import com.juvenxu.mvnbook.account.service.SignUpRequest;

public class SignUpServlet extends HttpServlet {
	private ApplicationContext context;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("id");
		String email=req.getParameter("email");
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String confirmPassword=req.getParameter("confirm_password");
		String captchaKey=req.getParameter("captcha_key");
		String captchaValue=req.getParameter("captcha_value");
		
		if(StringUtils.isEmpty(id)
				||StringUtils.isEmpty(email)
				||StringUtils.isEmpty(name)
				||StringUtils.isEmpty(password)
				||StringUtils.isEmpty(confirmPassword)
				||StringUtils.isEmpty(captchaKey)
				||StringUtils.isEmpty(captchaValue)){
			resp.sendError(400,"Parameter Incomplete.");
			return;
		}
		
		AccountService accountService=(AccountService)context.getBean("accountService");
		
		SignUpRequest request=new SignUpRequest();
		request.setId(id);
		request.setEmail(email);
		request.setPassword(password);
		request.setConfirmPassword(confirmPassword);
		request.setCaptchaKey(captchaKey);
		request.setCaptchaValue(captchaValue);
		
		request.setActivateServiceUrl(getServletContext().getRealPath("/")+"activate");

		try {
			accountService.signUp(request);
			resp.getWriter().print("Account is created, please check your mail box for activation link.");
		} catch (AccountServiceException e) {
			resp.sendError(400,e.getMessage());
			return;
		}
	}
}
