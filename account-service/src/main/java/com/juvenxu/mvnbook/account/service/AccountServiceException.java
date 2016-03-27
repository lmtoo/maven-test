package com.juvenxu.mvnbook.account.service;

public class AccountServiceException extends Exception {
	public AccountServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountServiceException(String message) {
		super(message);
	}
}
