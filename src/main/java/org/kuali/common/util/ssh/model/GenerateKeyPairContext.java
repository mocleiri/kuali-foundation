package org.kuali.common.util.ssh.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.spring.env.EnvUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;

public final class GenerateKeyPairContext {

	private final String name;
	private final Algorithm algorithm;
	private final int size;

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private Algorithm algorithm = Algorithm.RSA;
		private int size = 2048;

		private Optional<EnvironmentService> env = EnvUtils.ABSENT;
		private static final String NAME_KEY = "ssh.keyName";
		private static final String ALGORITHM_KEY = "ssh.algorithm";
		private static final String SIZE_KEY = "ssh.keySize";

		public Builder(String name) {
			this(EnvUtils.ABSENT, name);
		}

		public Builder(EnvironmentService env, String name) {
			this(Optional.of(env), name);
		}

		private Builder(Optional<EnvironmentService> env, String name) {
			this.env = env;
			if (env.isPresent()) {
				this.name = env.get().getString(NAME_KEY, name);
			} else {
				this.name = name;
			}
		}

		public Builder algorithm(Algorithm algorithm) {
			this.algorithm = algorithm;
			return this;
		}

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		private void validate(GenerateKeyPairContext context) {
			Assert.noBlanks(context.getName());
			Assert.positive(context.getSize());
		}

		private void override() {
			if (env.isPresent()) {
				algorithm(env.get().getProperty(ALGORITHM_KEY, Algorithm.class, algorithm));
				size(env.get().getInteger(SIZE_KEY, size));
			}
		}

		public GenerateKeyPairContext build() {
			override();
			GenerateKeyPairContext context = new GenerateKeyPairContext(this);
			validate(context);
			return context;
		}

	}

	private GenerateKeyPairContext(Builder builder) {
		this.name = builder.name;
		this.algorithm = builder.algorithm;
		this.size = builder.size;
	}

	public String getName() {
		return name;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public int getSize() {
		return size;
	}

}
