package com.juvenxu.mvnbook.account.mail;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

public class AccountMailServiceTest {

	private GreenMail greenMail;

	@Before
	public void setUp() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("test@juvenxu.com", "123456");
		greenMail.start();
	}

	@After
	public void tearDown() throws Exception {
		greenMail.stop();
	}

	@Test
	@Ignore
	public void testSendMail() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("account-mail.xml");
		AccountEmailService accountEmailService = (AccountEmailService) ctx.getBean("accountEmailService");

		String subject = "Test Subject";
		String htmlText = "<h3>Test</h3>";

		accountEmailService.sendMail("test2@juvenxu.com", subject, htmlText);

		greenMail.waitForIncomingEmail(2000, 1);

		Message[] msgs = greenMail.getReceivedMessages();

		Assert.assertEquals(1, msgs.length);
		Assert.assertEquals(subject, msgs[0].getSubject());
		Assert.assertEquals(htmlText, GreenMailUtil.getBody(msgs[0]).trim());
	}
}