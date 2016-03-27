package com.juvenxu.mvnbook.account.mail;

import javax.mail.MessagingException;

public class AccountEmailException extends RuntimeException {

	public AccountEmailException(String string, MessagingException e) {
	}

}
