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

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class HttpWaitResult {

	private final long start;
	private final long stop;
	private final long elapsed;
	private final List<HttpRequestResult> requestResults;
	private final HttpStatus status;
	private final HttpRequestResult finalRequestResult;

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

	public static class Builder {

		private final HttpStatus status;
		private final HttpRequestResult finalRequestResult;
		private final long start;
		private final long stop; // filled in automatically
		private final long elapsed; // filled in automatically

		private List<HttpRequestResult> requestResults = ImmutableList.of();

		public Builder(HttpStatus status, HttpRequestResult finalRequestResult, long start) {
			this.status = status;
			this.finalRequestResult = finalRequestResult;
			this.start = start;
			this.stop = System.currentTimeMillis();
			this.elapsed = stop - start;
		}

		public Builder requestResults(List<HttpRequestResult> requestResults) {
			this.requestResults = requestResults;
			return this;
		}

		public HttpWaitResult build() {
			Assert.noNulls(status, finalRequestResult, requestResults);
			Assert.isTrue(start > 0, "start is negative");
			Assert.isTrue(stop >= start, "stop is less than start");
			this.requestResults = ImmutableList.copyOf(requestResults);
			return new HttpWaitResult(this);
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

}
