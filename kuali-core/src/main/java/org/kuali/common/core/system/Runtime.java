package org.kuali.common.core.system;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Runtime.Builder.class)
public final class Runtime {

	private final int processors;
	private final Memory memory;

	private Runtime(Builder builder) {
		this.processors = builder.processors;
		this.memory = builder.memory;
	}

	public static Runtime create() {
		int processors = java.lang.Runtime.getRuntime().availableProcessors();
		Memory memory = Memory.create();
		return builder().withMemory(memory).withProcessors(processors).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Runtime> {

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
		public Runtime build() {
			return validate(new Runtime(this));
		}
	}

	public int getProcessors() {
		return processors;
	}

	public Memory getMemory() {
		return memory;
	}

}
