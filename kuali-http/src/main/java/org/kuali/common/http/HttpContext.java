/**
 * Copyright 2013-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.http;

import java.util.Arrays;
import java.util.List;

/**
 * @deprecated
 */
@Deprecated
public class HttpContext {

	// Gets printed as a prefix to any log messages emitted by the HttpService
	String logMsgPrefix = "Waiting for";

	// Url to contact
	String url;

	// HTTP codes signifying success
	List<Integer> successCodes = Arrays.asList(200);

	// If Tomcat is fronted by an Apache web server, and Apache is up and running but Tomcat is still starting, http 503 is returned by Apache
	// We don't want to fail if we get a 503, just continue waiting
	List<Integer> continueWaitingCodes = Arrays.asList(503);

	// Millis to wait before an individual http request times out (3 seconds)
	int requestTimeoutMillis = 3000;

	// Millis to wait in between http requests (3 seconds)
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

	public String getLogMsgPrefix() {
		return logMsgPrefix;
	}

	public void setLogMsgPrefix(String logMsgPrefix) {
		this.logMsgPrefix = logMsgPrefix;
	}

}
