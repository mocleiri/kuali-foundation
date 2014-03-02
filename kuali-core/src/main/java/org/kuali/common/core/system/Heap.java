package org.kuali.common.core.system;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Heap.Builder.class)
public final class Heap {
	// used=0.62g, free=1.16g, allocated=0.94g, max=1.78g

	@Min(0)
	private final long used;

	@Min(0)
	private final long free;

	@Min(0)
	private final long allocated;

	@Min(0)
	private final long max;

	private Heap(Builder builder) {
		this.used = builder.used;
		this.free = builder.free;
		this.allocated = builder.allocated;
		this.max = builder.max;
	}

	public static Heap create() {
		Runtime runtime = Runtime.getRuntime();

		// Total amount of memory the JVM is allowed to use
		long max = runtime.maxMemory();

		// Total amount of memory currently allocated
		long allocated = runtime.totalMemory();

		// The JDK method "freeMemory()" reports what is free in the currently allocated heap
		// The amount of memory currently being used by the JVM is the difference between what has been allocated and what is still free
		long used = allocated - runtime.freeMemory();

		// The true amount of free memory is the difference between max and what is currently being used
		long free = max - used;

		// Create a memory object based on the information we've calculated from the JDK Runtime object
		return builder().withAllocated(allocated).withFree(free).withMax(max).withUsed(used).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Heap> {

		private long used;
		private long free;
		private long allocated;
		private long max;

		public Builder withUsed(long used) {
			this.used = used;
			return this;
		}

		public Builder withFree(long free) {
			this.free = free;
			return this;
		}

		public Builder withAllocated(long allocated) {
			this.allocated = allocated;
			return this;
		}

		public Builder withMax(long max) {
			this.max = max;
			return this;
		}

		@Override
		public Heap build() {
			return validate(new Heap(this));
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
