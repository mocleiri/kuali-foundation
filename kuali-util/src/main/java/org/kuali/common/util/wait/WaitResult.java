package org.kuali.common.util.wait;

import static org.kuali.common.util.base.Precondition.checkMin;

public final class WaitResult {

	private final long start;
	private final long stop;
	private final long elapsed;

	public static WaitResult create(long start, long stop) {
		return builder(start, stop).build();
	}

	public static Builder builder(long start, long stop) {
		return new Builder(start, stop);
	}

	public static class Builder {

		private final long start;
		private final long stop;
		private final long elapsed;

		public Builder(long start, long stop) {
			this.start = start;
			this.stop = stop;
			this.elapsed = stop - start;
		}

		public WaitResult build() {
			WaitResult instance = new WaitResult(this);
			validate(instance);
			return instance;
		}

		private static void validate(WaitResult instance) {
			checkMin(instance.start, 0, "start");
			checkMin(instance.stop, instance.start, "stop");
			checkMin(instance.elapsed, 0, "elapsed");
		}

	}

	private WaitResult(Builder builder) {
		this.start = builder.start;
		this.stop = builder.stop;
		this.elapsed = builder.elapsed;
	}

	public long getStart() {
		return start;
	}

	public long getElapsed() {
		return elapsed;
	}

	public long getStop() {
		return stop;
	}

}
