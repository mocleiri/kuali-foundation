/**
 * Copyright 2013-2014 The Kuali Foundation
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

import static org.kuali.common.util.FormatUtils.getMillisAsInt;
import static org.kuali.common.util.base.Assertions.assertNotBlank;
import static org.kuali.common.util.base.Assertions.assertNotNegative;
import static org.kuali.common.util.base.Assertions.assertNotNull;
import static org.kuali.common.util.base.Assertions.assertPositive;

import java.util.List;

import org.kuali.common.util.Encodings;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class HttpContext {

	private final String url; // Url to contact
	private final String logMsgPrefix; // Gets printed as a prefix to any log messages emitted by the HttpService
	private final ImmutableList<Integer> successCodes; // HTTP codes signifying success
	private final int requestTimeoutMillis; // Millis to wait before an individual http request times out (15 seconds)
	private final int sleepIntervalMillis; // Millis to wait in between http requests (15 seconds)
	private final int overallTimeoutMillis; // Total number of millis to wait before timing out (30 minutes)
	private final String encoding;
	// If true, no log messages are emitted and timing out before getting a success code does not throw an exception
	// You are on your own to examine the HttpWaitResult object and figure out what to do from there
	private final boolean quiet;
	private final Optional<Integer> maxResponseBodyBytes;
	private final Optional<Integer> maxRetries;
	private final boolean asynchronousClose;

	// If Tomcat is fronted by an Apache web server, and Apache is up and running but Tomcat is still starting, http 503 is returned by Apache
	// We don't want to fail if we get a 503, just continue waiting
	private final ImmutableList<Integer> continueWaitingCodes;

	public static HttpContext create(String url) {
		return builder(url).build();
	}

	public static Builder builder(String url) {
		return new Builder(url);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<HttpContext> {

		private static final int OK = 200;
		private static final int SERVICE_UNAVAILABLE = 503;

		// Required
		private final String url;

		// Optional
		private String logMsgPrefix = "Waiting for";
		private List<Integer> successCodes = ImmutableList.of(OK);
		private List<Integer> continueWaitingCodes = ImmutableList.of(SERVICE_UNAVAILABLE);
		private int requestTimeoutMillis = getMillisAsInt("15s"); // 15 seconds
		private int sleepIntervalMillis = getMillisAsInt("15s"); // 15 seconds
		private int overallTimeoutMillis = getMillisAsInt("30m"); // 30 minutes
		private String encoding = Encodings.UTF8;
		private boolean quiet = false;
		private Optional<Integer> maxResponseBodyBytes = Optional.absent();
		private Optional<Integer> maxRetries = Optional.absent();
		private boolean asynchronousClose = false;

		public Builder(String url) {
			this.url = url;
		}

		public Builder asynchronousClose(boolean asynchronousClose) {
			this.asynchronousClose = asynchronousClose;
			return this;
		}

		public Builder maxRetries(Optional<Integer> maxRetries) {
			this.maxRetries = maxRetries;
			return this;
		}

		public Builder maxRetries(int maxRetries) {
			return maxRetries(Optional.of(maxRetries));
		}

		public Builder maxResponseBodyBytes(Optional<Integer> maxResponseBodyBytes) {
			this.maxResponseBodyBytes = maxResponseBodyBytes;
			return this;
		}

		public Builder maxResponseBodyBytes(int maxResponseBodyBytes) {
			return maxResponseBodyBytes(Optional.of(maxResponseBodyBytes));
		}

		public Builder quiet(boolean quiet) {
			this.quiet = quiet;
			return this;
		}

		public Builder logMsgPrefix(String logMsgPrefix) {
			this.logMsgPrefix = logMsgPrefix;
			return this;
		}

		public Builder encoding(String encoding) {
			this.encoding = encoding;
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

		public Builder requestTimeout(String requestTimeout) {
			this.requestTimeoutMillis = getMillisAsInt(requestTimeout);
			return this;
		}

		public Builder requestTimeoutMillis(int requestTimeoutMillis) {
			this.requestTimeoutMillis = requestTimeoutMillis;
			return this;
		}

		public Builder sleepInterval(String sleepInterval) {
			this.sleepIntervalMillis = getMillisAsInt(sleepInterval);
			return this;
		}

		public Builder sleepIntervalMillis(int sleepIntervalMillis) {
			this.sleepIntervalMillis = sleepIntervalMillis;
			return this;
		}

		public Builder overallTimeout(String overallTimeout) {
			this.overallTimeoutMillis = getMillisAsInt(overallTimeout);
			return this;
		}

		public Builder overallTimeoutMillis(int overallTimeoutMillis) {
			this.overallTimeoutMillis = overallTimeoutMillis;
			return this;
		}

		@Override
		public HttpContext build() {
			HttpContext instance = new HttpContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(HttpContext instance) {
			assertNotBlank(instance.url, "url");
			assertNotBlank(instance.encoding, "encoding");
			assertNotNull(instance.successCodes, "successCodes");
			assertNotNull(instance.continueWaitingCodes, "continueWaitingCodes");
			assertNotNull(instance.maxResponseBodyBytes, "maxResponseBodyBytes");
			if (instance.maxResponseBodyBytes.isPresent()) {
				assertPositive(instance.maxResponseBodyBytes.get(), "maxResponseBodyBytes");
			}
			assertNotNull(instance.maxRetries, "maxRetries");
			if (instance.maxRetries.isPresent()) {
				assertNotNegative(instance.maxRetries.get(), "maxRetries");
			}
			assertNotNull(instance.logMsgPrefix, "logMsgPrefix");
			assertPositive(instance.requestTimeoutMillis, "requestTimeoutMillis");
			assertPositive(instance.overallTimeoutMillis, "overallTimeoutMillis");
			assertPositive(instance.sleepIntervalMillis, "sleepIntervalMillis");
		}

		public String getLogMsgPrefix() {
			return logMsgPrefix;
		}

		public void setLogMsgPrefix(String logMsgPrefix) {
			this.logMsgPrefix = logMsgPrefix;
		}

		public List<Integer> getSuccessCodes() {
			return successCodes;
		}

		public void setSuccessCodes(List<Integer> successCodes) {
			this.successCodes = successCodes;
		}

		public List<Integer> getContinueWaitingCodes() {
			return continueWaitingCodes;
		}

		public void setContinueWaitingCodes(List<Integer> continueWaitingCodes) {
			this.continueWaitingCodes = continueWaitingCodes;
		}

		public int getRequestTimeoutMillis() {
			return requestTimeoutMillis;
		}

		public void setRequestTimeoutMillis(int requestTimeoutMillis) {
			this.requestTimeoutMillis = requestTimeoutMillis;
		}

		public int getSleepIntervalMillis() {
			return sleepIntervalMillis;
		}

		public void setSleepIntervalMillis(int sleepIntervalMillis) {
			this.sleepIntervalMillis = sleepIntervalMillis;
		}

		public int getOverallTimeoutMillis() {
			return overallTimeoutMillis;
		}

		public void setOverallTimeoutMillis(int overallTimeoutMillis) {
			this.overallTimeoutMillis = overallTimeoutMillis;
		}

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public boolean isQuiet() {
			return quiet;
		}

		public void setQuiet(boolean quiet) {
			this.quiet = quiet;
		}

		public String getUrl() {
			return url;
		}

		public Optional<Integer> getMaxResponseBodyBytes() {
			return maxResponseBodyBytes;
		}

		public void setMaxResponseBodyBytes(Optional<Integer> maxResponseBodyBytes) {
			this.maxResponseBodyBytes = maxResponseBodyBytes;
		}

		public Optional<Integer> getMaxRetries() {
			return maxRetries;
		}

		public void setMaxRetries(Optional<Integer> maxRetries) {
			this.maxRetries = maxRetries;
		}

		public boolean isAsynchronousClose() {
			return asynchronousClose;
		}

		public void setAsynchronousClose(boolean asynchronousClose) {
			this.asynchronousClose = asynchronousClose;
		}

	}

	private HttpContext(Builder builder) {
		this.url = builder.url;
		this.encoding = builder.encoding;
		this.logMsgPrefix = builder.logMsgPrefix;
		this.successCodes = ImmutableList.copyOf(builder.successCodes);
		this.continueWaitingCodes = ImmutableList.copyOf(builder.continueWaitingCodes);
		this.requestTimeoutMillis = builder.requestTimeoutMillis;
		this.sleepIntervalMillis = builder.sleepIntervalMillis;
		this.overallTimeoutMillis = builder.overallTimeoutMillis;
		this.quiet = builder.quiet;
		this.maxResponseBodyBytes = builder.maxResponseBodyBytes;
		this.maxRetries = builder.maxRetries;
		this.asynchronousClose = builder.asynchronousClose;
	}

	public String getUrl() {
		return url;
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

	public String getLogMsgPrefix() {
		return logMsgPrefix;
	}

	public String getEncoding() {
		return encoding;
	}

	public boolean isQuiet() {
		return quiet;
	}

	public Optional<Integer> getMaxResponseBodyBytes() {
		return maxResponseBodyBytes;
	}

	public Optional<Integer> getMaxRetries() {
		return maxRetries;
	}

	public boolean isAsynchronousClose() {
		return asynchronousClose;
	}

}
