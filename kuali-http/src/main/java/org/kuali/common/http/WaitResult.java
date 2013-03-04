package org.kuali.common.http;

public class WaitResult {

	long start;
	long stop;
	long elapsed;
	RequestResultEnum requestResult;
	String message;

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

	public RequestResultEnum getRequestResult() {
		return requestResult;
	}

	public void setRequestResult(RequestResultEnum requestResult) {
		this.requestResult = requestResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
