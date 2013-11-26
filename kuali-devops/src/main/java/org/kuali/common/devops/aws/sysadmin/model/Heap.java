package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;

public final class Heap {

	private final long maxSizeInBytes;
	private final long minSizeInBytes;
	private final long maxPermSizeInBytes;
	private final boolean dumpOnOutOfMemoryError;
	private final boolean enableLogging;

	public static class Builder {

		// Optional
		private long maxSizeInBytes = FormatUtils.getBytes("5g"); // 5 gigabytes
		private long minSizeInBytes = maxSizeInBytes; // Default them to be the same size
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

		public Heap build() {
			Assert.noNegatives(maxPermSizeInBytes, minSizeInBytes, maxSizeInBytes);
			Assert.isTrue(maxSizeInBytes >= minSizeInBytes);
			Assert.isTrue(maxSizeInBytes >= maxPermSizeInBytes);
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
