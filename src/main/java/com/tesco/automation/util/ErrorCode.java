package com.tesco.automation.util;

import static java.lang.String.format;

public enum ErrorCode {

	ERROR_INVALID_WIREMOCK_URL("ERR0001", "Invalid Wiremock URL : %s"),
	ERROR_INVALID_SERVER_ERROR("ERR0002", "Internal Server error: %s"),
	ERROR_UPLOAD_FAIL("ERR0003", "Unable to upload mock data from the file: %s"),
	ERROR_MOCKSERVICE_CREATION_FAILED("ERR0004", "Unable to create mock service on wiremock server: %s");

	private String code;
	private String metaDescription;

	ErrorCode(String code, String metaDescription) {
		this.code = code;
		this.metaDescription = metaDescription;
	}

	public String formatErrorMessage(String message) {
		return code.concat(": ").concat(format(metaDescription, message)).trim();
	}
}
