/**
 * Copyright 2014-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.core.system;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Memory.Builder.class)
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

	public static Memory create() {
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

	public static class Builder extends ValidatingBuilder<Memory> {

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
		public Memory build() {
			return validate(new Memory(this));
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
