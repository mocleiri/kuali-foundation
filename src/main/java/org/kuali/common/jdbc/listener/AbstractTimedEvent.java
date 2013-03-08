package org.kuali.common.jdbc.listener;

public class AbstractTimedEvent {

	long startTimeMillis;
	long stopTimeMillis;

	public AbstractTimedEvent() {
		this(0, 0);
	}

	public AbstractTimedEvent(long startTimeMillis, long stopTimeMillis) {
		super();
		this.startTimeMillis = startTimeMillis;
		this.stopTimeMillis = stopTimeMillis;
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

}
