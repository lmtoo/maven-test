package com.juvenxu.mvnbook.account.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.juvenxu.mvnbook.account.service.AccountService;
import com.juvenxu.mvnbook.account.service.AccountServiceException;

public class LoginServlet extends HttpServlet {

	private ApplicationContext context;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("id");
		String password=req.getParameter("password");
		AccountService service=(AccountService)context.getBean("accountService");
		
		try {
			service.login(id, password);
		} catch (AccountServiceException e) {
			e.printStackTrace();
		}
		
		
	}
}