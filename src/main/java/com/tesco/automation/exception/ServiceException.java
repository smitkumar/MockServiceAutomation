package com.tesco.automation.exception;

import com.tesco.automation.util.ErrorCode;

public class ServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String message;

	public ServiceException(ErrorCode errorCode, String message, Object... args) {
		super(errorCode.formatErrorMessage(String.format(message, args)));
		this.message = errorCode.formatErrorMessage(String.format(message, args));
	}

	public ServiceException(ErrorCode errorCode, String message) {
		super(errorCode.formatErrorMessage(String.format(message)));
		this.message = errorCode.formatErrorMessage(String.format(message));
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public String toString() {
		return "ServiceException [message=" + message + "]";
	}

}
