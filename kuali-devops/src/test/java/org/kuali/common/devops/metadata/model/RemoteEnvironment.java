package org.kuali.common.devops.metadata.model;

import java.util.Properties;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.property.ImmutableProperties;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class RemoteEnvironment {

	private final int processors;
	private final ImmutableProperties system;
	private final ImmutableProperties environment;
	private final long timestamp;
	private final Memory memory;

	private RemoteEnvironment(Builder builder) {
		this.processors = builder.processors;
		this.system = ImmutableProperties.copyOf(builder.system);
		this.environment = ImmutableProperties.copyOf(builder.environment);
		this.timestamp = builder.timestamp;
		this.memory = builder.memory;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<RemoteEnvironment> {

		private int processors;
		private Properties system;
		private Properties environment;
		private long timestamp;
		private Memory memory;

		public Builder processors(int processors) {
			this.processors = processors;
			return this;
		}

		public Builder system(ImmutableProperties system) {
			this.system = system;
			return this;
		}

		public Builder environment(ImmutableProperties environment) {
			this.environment = environment;
			return this;
		}

		public Builder timestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder memory(Memory memory) {
			this.memory = memory;
			return this;
		}

		@Override
		public RemoteEnvironment getInstance() {
			return new RemoteEnvironment(this);
		}

		public int getProcessors() {
			return processors;
		}

		public void setProcessors(int processors) {
			this.processors = processors;
		}

		public Properties getSystem() {
			return system;
		}

		public void setSystem(Properties system) {
			this.system = system;
		}

		public Properties getEnvironment() {
			return environment;
		}

		public void setEnvironment(Properties environment) {
			this.environment = environment;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public Memory getMemory() {
			return memory;
		}

		public void setMemory(Memory memory) {
			this.memory = memory;
		}

	}

	public int getProcessors() {
		return processors;
	}

	public Properties getSystem() {
		return system;
	}

	public Properties getEnvironment() {
		return environment;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Memory getMemory() {
		return memory;
	}

}
