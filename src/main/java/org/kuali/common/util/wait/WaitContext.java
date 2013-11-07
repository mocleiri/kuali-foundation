package org.kuali.common.util.wait;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;

public final class WaitContext {

	private final long timeoutMillis;
	private final long sleepMillis;
	private final long initialPauseMillis;

	public static class Builder {

		// Required
		private final long timeoutMillis;

		// Optional
		private long sleepMillis = FormatUtils.getMillis("1s"); // 1 second
		private long initialPauseMillis = 0;

		public Builder(long timeoutMillis) {
			this.timeoutMillis = timeoutMillis;
		}

		public Builder initialPauseMillis(long initialPauseMillis) {
			this.initialPauseMillis = initialPauseMillis;
			return this;
		}

		public Builder sleepMillis(long sleepMillis) {
			this.sleepMillis = sleepMillis;
			return this;
		}

		public WaitContext build() {
			Assert.noNegatives(timeoutMillis, sleepMillis, initialPauseMillis);
			return new WaitContext(this);
		}

	}

	private WaitContext(Builder builder) {
		this.timeoutMillis = builder.timeoutMillis;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
	}

	public long getTimeoutMillis() {
		return timeoutMillis;
	}

	public long getSleepMillis() {
		return sleepMillis;
	}

	public long getInitialPauseMillis() {
		return initialPauseMillis;
	}

}
