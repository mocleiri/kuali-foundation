package org.kuali.common.http;

import java.util.List;

public class WaitResult {

	long start;
	long stop;
	long elapsed;
	List<RequestResult> requestResults;
	ResultStatus status;

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

	public List<RequestResult> getRequestResults() {
		return requestResults;
	}

	public void setRequestResults(List<RequestResult> requestResults) {
		this.requestResults = requestResults;
	}

	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

}
