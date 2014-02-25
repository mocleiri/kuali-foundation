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

import static java.lang.System.currentTimeMillis;
import static org.kuali.common.util.base.Precondition.checkMin;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.collect.ImmutableList;

@JsonPropertyOrder(alphabetic = true)
@JsonDeserialize(builder = HttpWaitResult.Builder.class)
public final class HttpWaitResult {

	private final long start;
	private final long stop;
	private final long elapsed;
	private final List<HttpRequestResult> requestResults;
	private final HttpStatus status;
	private final HttpRequestResult finalRequestResult;

	public static HttpWaitResult newHttpWaitResult(HttpStatus status, HttpRequestResult finalRequestResult, long start) {
		return builder(status, finalRequestResult, start).build();
	}

	public static Builder builder(HttpStatus status, HttpRequestResult finalRequestResult, long start) {
		return new Builder(status, finalRequestResult, start);
	}

	@JsonPOJOBuilder(withPrefix = "")
	public static class Builder implements org.apache.commons.lang3.builder.Builder<HttpWaitResult> {

		private final HttpStatus status;
		private final HttpRequestResult finalRequestResult;
		private final long start;
		private long stop; // filled in automatically
		private long elapsed; // filled in automatically

		private List<HttpRequestResult> requestResults = ImmutableList.of();

		@JsonCreator
		public Builder(@JsonProperty("status") HttpStatus status, @JsonProperty("finalRequestResult") HttpRequestResult finalRequestResult, @JsonProperty("start") long start) {
			this.status = status;
			this.finalRequestResult = finalRequestResult;
			this.start = start;
			this.stop = currentTimeMillis();
			this.elapsed = stop - start;
		}

		public Builder elapsed(long elapsed) {
			this.elapsed = elapsed;
			return this;
		}

		public Builder stop(long stop) {
			this.stop = stop;
			return this;
		}

		public Builder requestResults(List<HttpRequestResult> requestResults) {
			this.requestResults = requestResults;
			return this;
		}

		@Override
		public HttpWaitResult build() {
			HttpWaitResult instance = new HttpWaitResult(this);
			validate(instance);
			return instance;
		}

		private static void validate(HttpWaitResult instance) {
			checkNotNull(instance.status, "status");
			checkNotNull(instance.finalRequestResult, "finalRequestResult");
			checkNotNull(instance.requestResults, "requestResults");
			checkNotNull(instance.finalRequestResult, "finalRequestResult");
			checkMin(instance.start, 0, "start");
			checkMin(instance.stop, instance.start, "stop");
		}

	}

	private HttpWaitResult(Builder builder) {
		this.status = builder.status;
		this.finalRequestResult = builder.finalRequestResult;
		this.start = builder.start;
		this.stop = builder.stop;
		this.elapsed = builder.elapsed;
		this.requestResults = builder.requestResults;
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

	public List<HttpRequestResult> getRequestResults() {
		return requestResults;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public HttpRequestResult getFinalRequestResult() {
		return finalRequestResult;
	}

}
