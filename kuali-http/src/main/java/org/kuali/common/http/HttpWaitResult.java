package org.kuali.common.http;

import java.util.List;

public class HttpWaitResult {

	long start;
	long stop;
	long elapsed;
	List<HttpRequestResult> requestResults;
	HttpStatus status;

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

}
