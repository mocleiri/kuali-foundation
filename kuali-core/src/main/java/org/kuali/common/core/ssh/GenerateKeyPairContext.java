package org.kuali.common.core.ssh;

import javax.validation.constraints.Min;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class GenerateKeyPairContext {

	private final String name;
	private final Algorithm algorithm;

	@Min(0)
	private final int size;

	public static GenerateKeyPairContext create(String name) {
		return builder(name).build();
	}

	public static Builder builder(String name) {
		return new Builder(name);
	}

	public static class Builder extends ValidatingBuilder<GenerateKeyPairContext> {

		// Required
		private final String name;

		// Optional
		private Algorithm algorithm = Algorithm.RSA;
		private int size = 2048;

		public Builder(String name) {
			this.name = name;
		}

		public Builder withAlgorithm(Algorithm algorithm) {
			this.algorithm = algorithm;
			return this;
		}

		public Builder withSize(int size) {
			this.size = size;
			return this;
		}

		@Override
		public GenerateKeyPairContext build() {
			return validate(new GenerateKeyPairContext(this));
		}

		public Algorithm getAlgorithm() {
			return algorithm;
		}

		public void setAlgorithm(Algorithm algorithm) {
			this.algorithm = algorithm;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public String getName() {
			return name;
		}
	}

	private GenerateKeyPairContext(Builder builder) {
		this.algorithm = builder.algorithm;
		this.name = builder.name;
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
