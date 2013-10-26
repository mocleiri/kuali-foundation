package org.kuali.common.aws.ec2.model;

import org.kuali.common.util.Assert;

public final class WaitResult {

	private final String state;
	private final long start;
	private final long elapsed;

	public static class Builder {

		private final String state;
		private final long start;
		private final long elapsed;

		public Builder(String state, long start, long elapsed) {
			this.state = state;
			this.start = start;
			this.elapsed = elapsed;
		}

		public WaitResult build() {
			Assert.noBlanks(state);
			Assert.noNegatives(start, elapsed);
			return new WaitResult(this);
		}

	}

	private WaitResult(Builder builder) {
		this.state = builder.state;
		this.start = builder.start;
		this.elapsed = builder.elapsed;
	}

	public String getState() {
		return state;
	}

	public long getStart() {
		return start;
	}

	public long getElapsed() {
		return elapsed;
	}

}
