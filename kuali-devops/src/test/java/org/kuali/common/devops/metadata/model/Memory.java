package org.kuali.common.devops.metadata.model;

import javax.validation.constraints.Min;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Memory {
	// used=0.62g, free=1.16g, allocated=0.94g, max=1.78g

	@Min(0)
	private final long used;
	@Min(0)
	private final long free;
	@Min(0)
	private final long allocated;
	@Min(0)
	private final long max;

	private Memory(Builder builder) {
		this.used = builder.used;
		this.free = builder.free;
		this.allocated = builder.allocated;
		this.max = builder.max;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Memory> {

		private long used;
		private long free;
		private long allocated;
		private long max;

		public Builder used(long used) {
			this.used = used;
			return this;
		}

		public Builder free(long free) {
			this.free = free;
			return this;
		}

		public Builder allocated(long allocated) {
			this.allocated = allocated;
			return this;
		}

		public Builder max(long max) {
			this.max = max;
			return this;
		}

		@Override
		public Memory getInstance() {
			return new Memory(this);
		}

	}

	public long getUsed() {
		return used;
	}

	public long getFree() {
		return free;
	}

	public long getAllocated() {
		return allocated;
	}

	public long getMax() {
		return max;
	}

}
