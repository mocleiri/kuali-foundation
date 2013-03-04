package org.kuali.common.http;

import java.util.List;

public class HttpContext {

	String url;
	List<Integer> successCodes;
	int requestTimeout;
	int sleepInterval;
	int overallTimeout;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Integer> getSuccessCodes() {
		return successCodes;
	}

	public void setSuccessCodes(List<Integer> successCodes) {
		this.successCodes = successCodes;
	}

	public int getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(int requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	public int getSleepInterval() {
		return sleepInterval;
	}

	public void setSleepInterval(int sleepInterval) {
		this.sleepInterval = sleepInterval;
	}

	public int getOverallTimeout() {
		return overallTimeout;
	}

	public void setOverallTimeout(int overallTimeout) {
		this.overallTimeout = overallTimeout;
	}

}
