package org.kuali.common.util.wait;

import org.kuali.common.util.Assert;

public final class WaitResult {

	private final long start;
	private final long elapsed;

	public static class Builder {

		private final long start;
		private final long elapsed;

		public Builder(long start, long elapsed) {
			this.start = start;
			this.elapsed = elapsed;
		}

		public WaitResult build() {
			Assert.noNegatives(start, elapsed);
			return new WaitResult(this);
		}

	}

	private WaitResult(Builder builder) {
		this.start = builder.start;
		this.elapsed = builder.elapsed;
	}

	public long getStart() {
		return start;
	}

	public long getElapsed() {
		return elapsed;
	}

}
