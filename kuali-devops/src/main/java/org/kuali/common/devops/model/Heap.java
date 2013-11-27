package org.kuali.common.devops.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.builder.BuilderUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

public final class Heap {

	private final long maxSizeInBytes;
	private final long minSizeInBytes;
	private final long maxPermSizeInBytes;
	private final boolean dumpOnOutOfMemoryError;
	private final boolean enableLogging;

	public static class Builder {

		// Optional
		private long maxSizeInBytes = FormatUtils.getBytes("5g"); // 5 gigabytes
		private long minSizeInBytes = maxSizeInBytes; // Default min to be the same as max
		private long maxPermSizeInBytes = FormatUtils.getBytes("512m"); // 512 megabytes
		private boolean dumpOnOutOfMemoryError = false;
		private boolean enableLogging = true;

		public Builder enableLogging(boolean enableLogging) {
			this.enableLogging = enableLogging;
			return this;
		}

		public Builder dumpOnOutOfMemoryError(boolean dumpOnOutOfMemoryError) {
			this.dumpOnOutOfMemoryError = dumpOnOutOfMemoryError;
			return this;
		}

		public Builder minSizeInBytes(long minSizeInBytes) {
			this.minSizeInBytes = minSizeInBytes;
			return this;
		}

		public Builder maxSizeInBytes(long maxSizeInBytes) {
			this.maxSizeInBytes = maxSizeInBytes;
			return this;
		}

		public Builder maxPermSizeInBytes(long maxPermSizeInBytes) {
			this.maxPermSizeInBytes = maxPermSizeInBytes;
			return this;
		}

		/**
		 * Override provided values with values from the environment
		 */
		public Builder override(EnvironmentService env) {
			this.maxPermSizeInBytes = BuilderUtils.getBytes(env, "heap.max", maxPermSizeInBytes);
			this.minSizeInBytes = BuilderUtils.getBytes(env, "heap.min", minSizeInBytes);
			this.maxPermSizeInBytes = BuilderUtils.getBytes(env, "heap.maxPermSize", maxPermSizeInBytes);
			return this;
		}

		public Heap build() {
			// None of them can be negative
			Assert.noNegatives(maxPermSizeInBytes, minSizeInBytes, maxSizeInBytes);

			// Max must be greater than or equal to min
			Assert.isTrue(maxSizeInBytes >= minSizeInBytes);

			// Max must be greater than or equal to the perm size
			Assert.isTrue(maxSizeInBytes >= maxPermSizeInBytes);

			// Return the fully constructed object
			return new Heap(this);
		}
	}

	private Heap(Builder builder) {
		this.dumpOnOutOfMemoryError = builder.dumpOnOutOfMemoryError;
		this.minSizeInBytes = builder.minSizeInBytes;
		this.maxSizeInBytes = builder.maxSizeInBytes;
		this.maxPermSizeInBytes = builder.maxPermSizeInBytes;
		this.enableLogging = builder.enableLogging;
	}

	public long getMaxSizeInBytes() {
		return maxSizeInBytes;
	}

	public long getMinSizeInBytes() {
		return minSizeInBytes;
	}

	public long getMaxPermSizeInBytes() {
		return maxPermSizeInBytes;
	}

	public boolean isDumpOnOutOfMemoryError() {
		return dumpOnOutOfMemoryError;
	}

	public boolean isEnableLogging() {
		return enableLogging;
	}

}
