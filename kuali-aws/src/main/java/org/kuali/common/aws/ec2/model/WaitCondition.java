package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;

public final class WaitCondition {

	private final int timeoutMillis;
	private final int sleepMillis;
	private final int initialPauseMillis;
	private final String state;

	public static class Builder {

		private final String state;
		private final int timeoutMillis;

		private int sleepMillis = FormatUtils.getMillisAsInt("5s"); // 5 seconds
		private int initialPauseMillis = FormatUtils.getMillisAsInt("2s"); // 2 seconds

		public Builder(String state, int timeoutMillis) {
			this.state = state;
			this.timeoutMillis = timeoutMillis;
		}

		public Builder initialPauseMillis(int initialPauseMillis) {
			this.initialPauseMillis = initialPauseMillis;
			return this;
		}

		public Builder sleepMillis(int sleepMillis) {
			this.sleepMillis = sleepMillis;
			return this;
		}

		public WaitCondition build() {
			Assert.noBlanks(state);
			Assert.noNegatives(timeoutMillis, sleepMillis, initialPauseMillis);
			return new WaitCondition(this);
		}

	}

	private WaitCondition(Builder builder) {
		this.timeoutMillis = builder.timeoutMillis;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.state = builder.state;
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
