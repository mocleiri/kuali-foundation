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

import java.io.IOException;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public class HttpRequestResult {

	private final Optional<Integer> statusCode;
	private final String statusText;
	private final Optional<IOException> exception;
	private final long start;
	private final long stop;
	private final long elapsed;

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

	public static class Builder {

		// Required
		private final String statusText;
		private final long start;
		private final long stop;
		private long elapsed; // filled in automatically

		// Optional
		private Optional<Integer> statusCode = Optional.absent();
		private Optional<IOException> exception = Optional.absent();

		// Filled in automatically
		public Builder(String statusText, long start, long stop) {
			this.statusText = statusText;
			this.start = start;
			this.stop = stop;
			this.elapsed = stop - start;
		}

		public Builder statusCode(int statusCode) {
			this.statusCode = Optional.of(statusCode);
			return this;
		}

		public Builder exception(IOException exception) {
			this.exception = Optional.of(exception);
			return this;
		}

		public HttpRequestResult build() {
			Assert.noNulls(statusCode, exception);
			Assert.noBlanks(statusText);
			Assert.isTrue(start > 0, "start is negative");
			Assert.isTrue(stop >= start, "stop is less than start");
			if (statusCode.isPresent()) {
				Assert.isTrue(statusCode.get() > 0, "status code must be a positive integer");
			}
			return new HttpRequestResult(this);
		}

	}

	private HttpRequestResult(Builder builder) {
		this.statusText = builder.statusText;
		this.start = builder.start;
		this.stop = builder.stop;
		this.elapsed = builder.elapsed;
		this.exception = builder.exception;
		this.statusCode = builder.statusCode;
	}

}
