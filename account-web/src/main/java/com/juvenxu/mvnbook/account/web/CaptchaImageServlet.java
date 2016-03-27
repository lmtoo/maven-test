package com.juvenxu.mvnbook.account.web;

import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.juvenxu.mvnbook.account.service.AccountService;
import com.juvenxu.mvnbook.account.service.AccountServiceException;

public class CaptchaImageServlet extends HttpServlet {
	private ApplicationContext context;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, java.io.IOException {
		String key = req.getParameter("key");
		if (key == null || key.length() == 0) {
			resp.sendError(400, "No Captcha Key Found");
		} else {
			AccountService service = (AccountService) context.getBean("accountService");
			try {
				resp.setContentType("image/jpeg");
				OutputStream out = resp.getOutputStream();
				out.write(service.generateCaptchaImage(key));
				out.close();
			} catch (AccountServiceException e) {
				resp.sendError(404, e.getMessage());
			}
		}
	};
}
