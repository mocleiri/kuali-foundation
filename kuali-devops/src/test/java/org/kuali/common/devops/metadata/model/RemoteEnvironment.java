package org.kuali.common.devops.metadata.model;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class RemoteEnvironment {

	private final Optional<Integer> processors;
	private final ImmutableProperties system;
	private final ImmutableProperties environment;
	private final Optional<Long> timestamp;
	private final Optional<Memory> memory;

	private RemoteEnvironment(Builder builder) {
		this.processors = builder.processors;
		this.system = ImmutableProperties.copyOf(builder.system);
		this.environment = ImmutableProperties.copyOf(builder.environment);
		this.timestamp = builder.timestamp;
		this.memory = builder.memory;
	}

	public static class Builder extends ValidatingBuilder<RemoteEnvironment> {

		private Optional<Integer> processors;
		private Properties system;
		private Properties environment;
		private Optional<Long> timestamp;
		private Optional<Memory> memory;

		public Builder processors(Optional<Integer> processors) {
			this.processors = processors;
			return this;
		}

		public Builder system(Properties system) {
			this.system = system;
			return this;
		}

		public Builder environment(Properties environment) {
			this.environment = environment;
			return this;
		}

		public Builder timestamp(Optional<Long> timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder memory(Optional<Memory> memory) {
			this.memory = memory;
			return this;
		}

		@Override
		public RemoteEnvironment getInstance() {
			return new RemoteEnvironment(this);
		}
	}

	public Optional<Integer> getProcessors() {
		return processors;
	}

	public Properties getSystem() {
		return system;
	}

	public Properties getEnvironment() {
		return environment;
	}

	public Optional<Long> getTimestamp() {
		return timestamp;
	}

	public Optional<Memory> getMemory() {
		return memory;
	}

}
