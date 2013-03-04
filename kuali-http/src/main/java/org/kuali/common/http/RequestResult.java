package org.kuali.common.http;

import java.io.IOException;

public class RequestResult {

	int statusCode;
	String statusText;
	IOException exception;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public IOException getException() {
		return exception;
	}

	public void setException(IOException exception) {
		this.exception = exception;
	}

}
