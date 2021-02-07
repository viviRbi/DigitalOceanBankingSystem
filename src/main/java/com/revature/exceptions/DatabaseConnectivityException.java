package com.revature.exceptions;

public class DatabaseConnectivityException extends Exception{
	public DatabaseConnectivityException() {
		super("There is some problems connecting to the database.");
	}
	public DatabaseConnectivityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public DatabaseConnectivityException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseConnectivityException(String message) {
		super(message);
	}

	public DatabaseConnectivityException(Throwable cause) {
		super(cause);
	}
}
