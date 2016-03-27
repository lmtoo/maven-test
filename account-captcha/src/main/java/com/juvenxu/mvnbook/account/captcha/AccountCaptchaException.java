package com.juvenxu.mvnbook.account.captcha;

import java.io.IOException;

public class AccountCaptchaException extends Exception {
	public AccountCaptchaException(String message) {
		super(message);
	}

	public AccountCaptchaException(String string, IOException e) {
		super(string, e);
	}
}
