package com.juvenxu.mvnbook.account.persist;

public class AccountPersistException extends Exception {

	public AccountPersistException(String message, Exception e) {
		super(message, e);
	}

}
