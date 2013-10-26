package org.kuali.common.util.wait;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.condition.Condition;

public final class WaitContext {

	private final long timeoutMillis;
	private final long sleepMillis;
	private final long initialPauseMillis;
	private final Condition condition;

	public static class Builder {

		// Required
		private final long timeoutMillis;
		private final Condition condition;

		// Optional
		private long sleepMillis = FormatUtils.getMillis("3s"); // 3 seconds
		private long initialPauseMillis = FormatUtils.getMillis("1s"); // 1 second

		public Builder(Condition condition, long timeoutMillis) {
			this.condition = condition;
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
			Assert.noNulls(condition);
			Assert.noNegatives(timeoutMillis, sleepMillis, initialPauseMillis);
			return new WaitContext(this);
		}

	}

	private WaitContext(Builder builder) {
		this.timeoutMillis = builder.timeoutMillis;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.condition = builder.condition;
	}

	public Condition getCondition() {
		return condition;
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
