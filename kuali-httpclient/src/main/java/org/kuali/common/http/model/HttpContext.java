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

import static com.google.common.base.Preconditions.checkNotNull;
import static org.kuali.common.util.FormatUtils.getMillisAsInt;
import static org.kuali.common.util.base.Precondition.checkMin;
import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.util.List;

import org.kuali.common.util.Encodings;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.nullify.NullUtils;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@JsonPropertyOrder(alphabetic = true)
@JsonDeserialize(builder = HttpContext.Builder.class)
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
	// The max number of retries to attempt
	private final Optional<Integer> maxRetries;
	// The max number of bytes to read
	private final Optional<Long> maxBytes;

	// If Tomcat is fronted by an Apache web server, and Apache is up and running but Tomcat is still starting, http 503 is returned by Apache
	// We don't want to fail if we get a 503, just continue waiting
	private final ImmutableList<Integer> continueWaitingCodes;

	public static HttpContext newHttpContext() {
		return builder().build();
	}

	public static HttpContext newHttpContext(String url) {
		return builder(url).build();
	}

	public static HttpContext create(String url) {
		return newHttpContext(url);
	}

	public static HttpContext create() {
		return newHttpContext();
	}

	public static Builder builder(String url) {
		return new Builder(url);
	}

	public static Builder builder() {
		return new Builder();
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder implements org.apache.commons.lang3.builder.Builder<HttpContext> {

		private static final int OK = 200;
		private static final int SERVICE_UNAVAILABLE = 503;

		private String url;

		// Optional
		private String logMsgPrefix = "Waiting for";
		private List<Integer> successCodes = ImmutableList.of(OK);
		private List<Integer> continueWaitingCodes = ImmutableList.of(SERVICE_UNAVAILABLE);
		private int requestTimeoutMillis = getMillisAsInt("15s"); // 15 seconds
		private int sleepIntervalMillis = getMillisAsInt("15s"); // 15 seconds
		private int overallTimeoutMillis = getMillisAsInt("30m"); // 30 minutes
		private String encoding = Encodings.UTF8;
		private boolean quiet = false;
		private Optional<Long> maxBytes = Optional.absent();
		private Optional<Integer> maxRetries = Optional.absent();

		public Builder() {
			this(NullUtils.NONE);
		}

		public Builder(String url) {
			this.url = url;
		}

		@JsonSetter
		public Builder maxRetries(Optional<Integer> maxRetries) {
			this.maxRetries = maxRetries;
			return this;
		}

		public Builder maxRetries(int maxRetries) {
			return maxRetries(Optional.of(maxRetries));
		}

		@JsonSetter
		public Builder maxBytes(Optional<Long> maxBytes) {
			this.maxBytes = maxBytes;
			return this;
		}

		public Builder maxBytes(String maxBytes) {
			return maxBytes(Optional.of(FormatUtils.getBytes(maxBytes)));
		}

		public Builder maxBytes(long maxBytes) {
			return maxBytes(Optional.of(maxBytes));
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
			checkNotNull(instance.successCodes, "successCodes");
			checkNotNull(instance.continueWaitingCodes, "continueWaitingCodes");
			checkNotNull(instance.maxBytes, "maxBytes");
			checkNotNull(instance.maxRetries, "maxRetries");
			checkNotNull(instance.logMsgPrefix, "logMsgPrefix");
			checkNotBlank(instance.url, "url");
			checkNotBlank(instance.encoding, "encoding");
			checkMin(instance.maxBytes, 0, "maxBytes");
			checkMin(instance.maxRetries, 0, "maxRetries");
			checkMin(instance.requestTimeoutMillis, 0, "requestTimeoutMillis");
			checkMin(instance.overallTimeoutMillis, 0, "overallTimeoutMillis");
			checkMin(instance.sleepIntervalMillis, 0, "sleepIntervalMillis");
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

		public Optional<Long> getMaxBytes() {
			return maxBytes;
		}

		public void setMaxBytes(Optional<Long> maxResponseBodyBytes) {
			this.maxBytes = maxResponseBodyBytes;
		}

		public Optional<Integer> getMaxRetries() {
			return maxRetries;
		}

		public void setMaxRetries(Optional<Integer> maxRetries) {
			this.maxRetries = maxRetries;
		}

		public void setUrl(String url) {
			this.url = url;
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
		this.maxBytes = builder.maxBytes;
		this.maxRetries = builder.maxRetries;
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

	public Optional<Long> getMaxBytes() {
		return maxBytes;
	}

	public Optional<Integer> getMaxRetries() {
		return maxRetries;
	}

}
