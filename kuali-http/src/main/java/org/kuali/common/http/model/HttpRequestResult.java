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

import java.io.IOException;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public final class HttpRequestResult {

	private final Optional<Integer> statusCode;
	private final Optional<String> responseBody;
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

		private long stop; // filled in automatically
		private long elapsed; // filled in automatically

		// Optional
		private Optional<Integer> statusCode = Optional.absent();
		private Optional<String> responseBody = Optional.absent();
		private Optional<IOException> exception = Optional.absent();

		public Builder(IOException exception, long start) {
			this.exception = Optional.of(exception);
			this.statusText = getStatusText(exception);
			this.start = start;
		}

		public Builder(String statusText, int statusCode, String responseBody, long start) {
			this.statusText = statusText;
			this.statusCode = Optional.of(statusCode);
			this.responseBody = Optional.fromNullable(responseBody);
			this.start = start;
		}

		public HttpRequestResult build() {
			Assert.noNulls(statusCode, exception, responseBody);
			Assert.noBlanks(statusText);
			Assert.isTrue(start > 0, "start is negative");
			if (statusCode.isPresent()) {
				Assert.isTrue(statusCode.get() > 0, "status code must be a positive integer");
			}
			this.stop = System.currentTimeMillis();
			this.elapsed = stop - start;
			return new HttpRequestResult(this);
		}

		protected static String getStatusText(IOException exception) {
			String name = exception.getClass().getName();
			int pos = name.lastIndexOf(".");
			if (pos != -1) {
				name = name.substring(pos + 1);
			}
			return name + ": " + exception.getMessage();
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

}
