package org.kuali.common.util.wait;

import org.kuali.common.util.Assert;

public final class WaitResult {

	private final long start;
	private final long stop;
	private final long elapsed;

	public static class Builder {

		private final long start;
		private final long stop;
		private final long elapsed;

		public Builder(long start, long stop) {
			this.start = start;
			this.stop = stop;
			this.elapsed = start - stop;
		}

		public WaitResult build() {
			Assert.noNegatives(start, stop, elapsed);
			Assert.isTrue(stop >= start, "stop is less than start");
			return new WaitResult(this);
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
