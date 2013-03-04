package org.kuali.common.http;

public class WaitResult {

	long start;
	long stop;
	long elapsed;
	RequestResult requestResult;

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

	public RequestResult getRequestResult() {
		return requestResult;
	}

	public void setRequestResult(RequestResult requestResult) {
		this.requestResult = requestResult;
	}

}
