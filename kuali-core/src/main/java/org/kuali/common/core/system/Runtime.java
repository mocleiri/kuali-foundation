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
