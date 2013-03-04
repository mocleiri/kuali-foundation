package org.kuali.common.http;

import java.util.List;

public class HttpContext {

	// Url to contact
	String url;

	// HTTP codes signifying success
	List<Integer> successCodes;

	// If Tomcat is fronted by an Apache web server, and Apache is up and running but Tomcat is still starting, http 503 is returned
	// We don't want to fail if we get a 503, just continue waiting
	List<Integer> continueWaitingCodes;

	// Millis to wait before an individual http request times out
	int requestTimeout = 3000;

	// Millis to wait in between http requests
	int sleepInterval = 3000;

	// Total number of seconds to wait before failing
	int overallTimeout = 180;

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

	public List<Integer> getContinueWaitingCodes() {
		return continueWaitingCodes;
	}

	public void setContinueWaitingCodes(List<Integer> continueWaitingCodes) {
		this.continueWaitingCodes = continueWaitingCodes;
	}

}
