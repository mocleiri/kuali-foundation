package org.kuali.common.jdbc;

public class ExecutionResult {

	long updateCount;
	long startTimeMillis;
	long stopTimeMillis;
	long elapsed;

	public ExecutionResult() {
		this(0, 0, 0);
	}

	public ExecutionResult(long updateCount, long startTimeMillis, long stopTimeMillis) {
		super();
		this.updateCount = updateCount;
		this.startTimeMillis = startTimeMillis;
		this.stopTimeMillis = stopTimeMillis;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(long updateCount) {
		this.updateCount = updateCount;
	}

	public long getStartTimeMillis() {
		return startTimeMillis;
	}

	public void setStartTimeMillis(long startTimeMillis) {
		this.startTimeMillis = startTimeMillis;
	}

	public long getStopTimeMillis() {
		return stopTimeMillis;
	}

	public void setStopTimeMillis(long stopTimeMillis) {
		this.stopTimeMillis = stopTimeMillis;
	}

	public long getElapsed() {
		return elapsed;
	}

	public void setElapsed(long elapsed) {
		this.elapsed = elapsed;
	}

}
