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
package org.kuali.common.http;

import java.util.List;

/**
 * @deprecated
 */
@Deprecated
public class HttpWaitResult {

	long start;
	long stop;
	long elapsed;
	List<HttpRequestResult> requestResults;
	HttpStatus status;
	HttpRequestResult finalRequestResult;

	public HttpWaitResult() {
		this(0);

	}

	public HttpWaitResult(long start) {
		super();
		this.start = start;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getStop() {
		return stop;
	}

	public void setStop(long stop) {
		this.stop = stop;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

	public List<HttpRequestResult> getRequestResults() {
		return requestResults;
	}

	public void setRequestResults(List<HttpRequestResult> requestResults) {
		this.requestResults = requestResults;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HttpRequestResult getFinalRequestResult() {
		return finalRequestResult;
	}

	public void setFinalRequestResult(HttpRequestResult finalRequestResult) {
		this.finalRequestResult = finalRequestResult;
	}

}
