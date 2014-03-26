package org.kuali.common.core.system;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = VirtualRuntime.Builder.class)
public final class VirtualRuntime {

	private final int processors;
	private final Memory memory;

	private VirtualRuntime(Builder builder) {
		this.processors = builder.processors;
		this.memory = builder.memory;
	}

	public static VirtualRuntime create() {
		int processors = Runtime.getRuntime().availableProcessors();
		Memory memory = Memory.create();
		return builder().withMemory(memory).withProcessors(processors).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<VirtualRuntime> {

		private int processors;
		private Memory memory;

		public Builder withProcessors(int processors) {
			this.processors = processors;
			return this;
		}

		public Builder withMemory(Memory memory) {
			this.memory = memory;
			return this;
		}

		@Override
		public VirtualRuntime build() {
			return validate(new VirtualRuntime(this));
		}
	}

	public int getProcessors() {
		return processors;
	}

	public Memory getMemory() {
		return memory;
	}

}
