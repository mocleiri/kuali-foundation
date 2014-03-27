/**
 * Copyright 2014-2014 The Kuali Foundation
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

import static com.google.common.base.Optional.absent;
import static java.lang.System.currentTimeMillis;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.kuali.common.util.base.Precondition.checkMin;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.io.IOException;

import org.kuali.common.http.json.IOExceptionSerializer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;

@JsonPropertyOrder(alphabetic = true)
@JsonDeserialize(builder = HttpRequestResult.Builder.class)
public final class HttpRequestResult {

	private final Optional<Integer> statusCode;
	private final Optional<String> responseBody;
	private final String statusText;
	@JsonSerialize(using = IOExceptionSerializer.class)
	private final Optional<IOException> exception;
	private final long start;
	private final long stop;
	private final long elapsed;

	public static HttpRequestResult newHttpRequestResult(String statusText, int statusCode, Optional<String> responseBody, long start) {
		return builder(statusText, statusCode, responseBody, start).build();
	}

	public static HttpRequestResult newHttpRequestResult(IOException exception, long start) {
		return builder(exception, start).build();
	}

	public static Builder builder(String statusText, int statusCode, Optional<String> responseBody, long start) {
		return new Builder(statusText, statusCode, responseBody, start);
	}

	public static Builder builder(IOException exception, long start) {
		return new Builder(exception, start);
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder implements org.apache.commons.lang3.builder.Builder<HttpRequestResult> {

		private String statusText;
		private long start;

		private long stop; // filled in automatically
		private long elapsed; // filled in automatically

		// Optional
		private Optional<Integer> statusCode = absent();
		private Optional<String> responseBody = absent();
		private Optional<IOException> exception = absent();

		@JsonCreator
		public Builder(@JsonProperty("statusText") String statusText, @JsonProperty("statusCode") Optional<Integer> statusCode,
				@JsonProperty("responseBody") Optional<String> responseBody, @JsonProperty("start") long start) {
			this.statusText = statusText;
			this.statusCode = statusCode;
			this.responseBody = responseBody;
			this.start = start;
			this.stop = currentTimeMillis();
			this.elapsed = stop - start;
		}

		public Builder(String statusText, int statusCode, Optional<String> responseBody, long start) {
			this(statusText, Optional.of(statusCode), responseBody, start);
		}

		public Builder(IOException exception, long start) {
			this.exception = Optional.of(exception);
			this.statusText = isBlank(exception.getMessage()) ? "n/a" : exception.getMessage();
			this.start = start;
			this.stop = currentTimeMillis();
			this.elapsed = stop - start;
		}

		public Builder exception(Optional<IOException> exception) {
			this.exception = exception;
			return this;
		}

		public Builder elapsed(long elapsed) {
			this.elapsed = elapsed;
			return this;
		}

		public Builder stop(long stop) {
			this.stop = stop;
			return this;
		}

		@Override
		public HttpRequestResult build() {
			HttpRequestResult instance = new HttpRequestResult(this);
			validate(instance);
			return instance;
		}

		private static void validate(HttpRequestResult instance) {
			checkNotNull(instance.statusCode, "statusCode");
			checkNotNull(instance.exception, "exception");
			checkNotNull(instance.responseBody, "responseBody");
			checkNotBlank(instance.statusText, "statusText");
			checkMin(instance.statusCode, 0, "statusCode");
			checkMin(instance.stop, instance.start, "stop"); // Make sure stop is >= start
			checkMin(instance.elapsed, 0, "elapsed");
		}

	}

	private HttpRequestResult(Builder builder) {
		this.statusText = builder.statusText;
		this.start = builder.start;
		this.stop = builder.stop;
		this.elapsed = builder.elapsed;
		this.exception = builder.exception;
		this.statusCode = builder.statusCode;
		this.responseBody = builder.responseBody;
	}

	public Optional<String> getResponseBody() {
		return responseBody;
	}

	public Optional<Integer> getStatusCode() {
		return statusCode;
	}

	public String getStatusText() {
		return statusText;
	}

	public Optional<IOException> getException() {
		return exception;
	}

	public long getStart() {
		return start;
	}

	public long getStop() {
		return stop;
	}

	public long getElapsed() {
		return elapsed;
	}

}
