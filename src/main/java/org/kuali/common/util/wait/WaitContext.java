package org.kuali.common.util.wait;

import static org.kuali.common.util.base.Precondition.checkMin;

import org.kuali.common.util.FormatUtils;

public final class WaitContext {

	private final long timeoutMillis;
	private final long sleepMillis;
	private final long initialPauseMillis;

	public static WaitContext create(long timeoutMillis) {
		return builder(timeoutMillis).build();
	}

	public static Builder builder(long timeoutMillis) {
		return new Builder(timeoutMillis);
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<WaitContext> {

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

		@Override
		public WaitContext build() {
			WaitContext instance = new WaitContext(this);
			validate(instance);
			return instance;
		}

		private static void validate(WaitContext instance) {
			checkMin(instance.timeoutMillis, 0, "timeoutMillis");
			checkMin(instance.sleepMillis, 0, "sleepMillis");
			checkMin(instance.initialPauseMillis, 0, "initialPauseMillis");
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
