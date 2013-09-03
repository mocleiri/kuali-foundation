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
package org.kuali.common.http.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;

public class HttpContext {

	private final String url; // Url to contact
	private final String logMsgPrefix; // Gets printed as a prefix to any log messages emitted by the HttpService
	private final List<Integer> successCodes; // HTTP codes signifying success
	private final int requestTimeoutMillis; // Millis to wait before an individual http request times out (3 seconds)
	private final int sleepIntervalMillis; // Millis to wait in between http requests (3 seconds)
	private final int overallTimeoutMillis; // Total number of millis to wait before timing out (3 minutes)

	// If Tomcat is fronted by an Apache web server, and Apache is up and running but Tomcat is still starting, http 503 is returned by Apache
	// We don't want to fail if we get a 503, just continue waiting
	private final List<Integer> continueWaitingCodes;

	public static class Builder {

		// Required
		private final String url;

		// Optional
		private String logMsgPrefix = "Waiting for";
		private List<Integer> successCodes = Collections.unmodifiableList(Arrays.asList(200));
		private List<Integer> continueWaitingCodes = Collections.unmodifiableList(Arrays.asList(503));
		private int requestTimeoutMillis = 3000;
		private int sleepIntervalMillis = 3000;
		private int overallTimeoutMillis = 1000 * 60 * 3;

		public Builder(String url) {
			this.url = url;
		}

		public Builder logMsgPrefix(String logMsgPrefix) {
			this.logMsgPrefix = logMsgPrefix;
			return this;
		}

		public Builder successCodes(List<Integer> successCodes) {
			this.successCodes = successCodes;
			return this;
		}

		public Builder continueWaitingCodes(List<Integer> continueWaitingCodes) {
			this.continueWaitingCodes = continueWaitingCodes;
			return this;
		}

		public Builder requestTimeoutMillis(int requestTimeoutMillis) {
			this.requestTimeoutMillis = requestTimeoutMillis;
			return this;
		}

		public Builder sleepIntervalMillis(int sleepIntervalMillis) {
			this.sleepIntervalMillis = sleepIntervalMillis;
			return this;
		}

		public Builder overallTimeoutMillis(int overallTimeoutMillis) {
			this.overallTimeoutMillis = overallTimeoutMillis;
			return this;
		}

		public HttpContext build() {
			Assert.noBlanks(url);
			Assert.noNulls(successCodes, continueWaitingCodes);
			Assert.isTrue(requestTimeoutMillis > 0, "requestTimeoutMillis must be a positive integer");
			Assert.isTrue(overallTimeoutMillis > 0, "overallTimeoutMillis must be a positive integer");
			Assert.isTrue(sleepIntervalMillis >= 0, "sleepIntervalMillis is negative");
			return new HttpContext(this);
		}

	}

	private HttpContext(Builder builder) {
		this.url = builder.url;
		this.logMsgPrefix = builder.logMsgPrefix;
		this.successCodes = of(builder.successCodes);
		this.continueWaitingCodes = of(builder.continueWaitingCodes);
		this.requestTimeoutMillis = builder.requestTimeoutMillis;
		this.sleepIntervalMillis = builder.sleepIntervalMillis;
		this.overallTimeoutMillis = builder.overallTimeoutMillis;
	}

	private static List<Integer> of(List<Integer> integers) {
		return Collections.unmodifiableList(integers);
	}

	public String getUrl() {
		return url;
	}

	public String getLogMsgPrefix() {
		return logMsgPrefix;
	}

	public List<Integer> getSuccessCodes() {
		return successCodes;
	}

	public int getRequestTimeoutMillis() {
		return requestTimeoutMillis;
	}

	public int getSleepIntervalMillis() {
		return sleepIntervalMillis;
	}

	public int getOverallTimeoutMillis() {
		return overallTimeoutMillis;
	}

	public List<Integer> getContinueWaitingCodes() {
		return continueWaitingCodes;
	}

}
