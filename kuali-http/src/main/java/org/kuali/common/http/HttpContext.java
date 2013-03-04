package org.kuali.common.http;

import java.util.Arrays;
import java.util.List;

public class HttpContext {

	// Url to contact
	String url;

	// HTTP codes signifying success
	List<Integer> successCodes = Arrays.asList(200);

	// If Tomcat is fronted by an Apache web server, and Apache is up and running but Tomcat is still starting, http 503 is returned by Apache
	// We don't want to fail if we get a 503, just continue waiting
	List<Integer> continueWaitingCodes = Arrays.asList(503);

	// Millis to wait before an individual http request times out
	int requestTimeoutMillis = 3000;

	// Millis to wait in between http requests
	int sleepIntervalMillis = 3000;

	// Total number of millis to wait before timing out (3 minutes)
	int overallTimeoutMillis = 1000 * 60 * 3;

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

	public int getRequestTimeoutMillis() {
		return requestTimeoutMillis;
	}

	public void setRequestTimeoutMillis(int requestTimeout) {
		this.requestTimeoutMillis = requestTimeout;
	}

	public int getSleepIntervalMillis() {
		return sleepIntervalMillis;
	}

	public void setSleepIntervalMillis(int sleepInterval) {
		this.sleepIntervalMillis = sleepInterval;
	}

	public int getOverallTimeoutMillis() {
		return overallTimeoutMillis;
	}

	public void setOverallTimeoutMillis(int overallTimeout) {
		this.overallTimeoutMillis = overallTimeout;
	}

	public List<Integer> getContinueWaitingCodes() {
		return continueWaitingCodes;
	}

	public void setContinueWaitingCodes(List<Integer> continueWaitingCodes) {
		this.continueWaitingCodes = continueWaitingCodes;
	}

}
