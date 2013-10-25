package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public final class WaitControl {

	private final boolean wait;
	private final int timeoutMillis;
	private final int sleepMillis;
	private final int initialPauseMillis;
	private final String state;

	public static class Builder {

		private final String state;
		private final int timeoutMillis;

		private final int sleepMillis = 5000;
		private final int initialPauseMillis = 1500;
		private boolean wait = true;

		public Builder(String state, int timeoutMillis) {
			this.state = state;
			this.timeoutMillis = timeoutMillis;
		}

		public WaitControl build() {
			Assert.noBlanks(state);
			Assert.isTrue(sleepMillis >=0);
			return new WaitControl(this);
		}

	}

	private WaitControl(Builder builder) {
		this.wait = builder.wait;
		this.timeoutMillis = builder.timeoutMillis;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.state = builder.state;
	}

	public boolean isWait() {
		return wait;
	}

	public String getState() {
		return state;
	}

	public int getTimeoutMillis() {
		return timeoutMillis;
	}

	public int getSleepMillis() {
		return sleepMillis;
	}

	public int getInitialPauseMillis() {
		return initialPauseMillis;
	}

}
