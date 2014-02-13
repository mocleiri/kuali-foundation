package org.kuali.common.devops.metadata.model;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class RemoteEnvironment {

	private final ImmutableProperties system;
	private final ImmutableProperties environment;
	private final Optional<Integer> processors;
	private final Optional<Long> currentTimeMillis;
	private final Optional<Memory> memory;

	private RemoteEnvironment(Builder builder) {
		this.processors = builder.processors;
		this.system = ImmutableProperties.copyOf(builder.system);
		this.environment = ImmutableProperties.copyOf(builder.environment);
		this.currentTimeMillis = builder.currentTimeMillis;
		this.memory = builder.memory;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<RemoteEnvironment> {

		private Optional<Integer> processors;
		private Properties system;
		private Properties environment;
		private Optional<Long> currentTimeMillis;
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

		public Builder currentTimeMillis(Optional<Long> currentTimeMillis) {
			this.currentTimeMillis = currentTimeMillis;
			return this;
		}

		public Builder memory(Optional<Memory> memory) {
			this.memory = memory;
			return this;
		}

		@Override
		public RemoteEnvironment build() {
			return checkConstraints(validator, new RemoteEnvironment(this));
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

	public Optional<Long> getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	public Optional<Memory> getMemory() {
		return memory;
	}

}
