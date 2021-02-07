package com.revature.exceptions;

public class NotEnoughBalanceException extends Exception{
		public NotEnoughBalanceException() {
			super("Your balance is not enough for this transaction.");
		}
		public NotEnoughBalanceException(String message, Throwable cause, boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
		public NotEnoughBalanceException(String message, Throwable cause) {
			super(message, cause);
		}

		public NotEnoughBalanceException(String message) {
			super(message);
		}

		public NotEnoughBalanceException(Throwable cause) {
			super(cause);
		}

}
