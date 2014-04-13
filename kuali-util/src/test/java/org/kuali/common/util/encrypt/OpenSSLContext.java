package org.kuali.common.util.encrypt;

import static org.kuali.common.util.base.Precondition.checkMin;

public final class OpenSSLContext {

	private final int iterations;
	private final int saltOffset;
	private final int saltSize;
	private final int ciphertextOffset;
	private final int keySizeBits;
	private final String transformation;

	private OpenSSLContext(Builder builder) {
		this.iterations = builder.iterations;
		this.saltOffset = builder.saltOffset;
		this.saltSize = builder.saltSize;
		this.ciphertextOffset = builder.ciphertextOffset;
		this.keySizeBits = builder.keySizeBits;
		this.transformation = builder.transformation;
	}

	public static OpenSSLContext buildDefaultOpenSSLContext() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<OpenSSLContext> {

		private int iterations = 1;
		private int saltOffset = 8;
		private int saltSize = 8;
		private int ciphertextOffset = saltOffset + saltSize;
		private int keySizeBits = 128;
		private String transformation = "AES/CBC/PKCS5Padding";

		public Builder withTransformation(String transformation) {
			this.transformation = transformation;
			return this;
		}

		public Builder withIterations(int iterations) {
			this.iterations = iterations;
			return this;
		}

		public Builder withSaltOffset(int saltOffset) {
			this.saltOffset = saltOffset;
			return this;
		}

		public Builder withSaltSize(int saltSize) {
			this.saltSize = saltSize;
			return this;
		}

		public Builder withCiphertextOffset(int ciphertextOffset) {
			this.ciphertextOffset = ciphertextOffset;
			return this;
		}

		public Builder withKeySizeBits(int keySizeBits) {
			this.keySizeBits = keySizeBits;
			return this;
		}

		@Override
		public OpenSSLContext build() {
			return validate(new OpenSSLContext(this));
		}

		private OpenSSLContext validate(OpenSSLContext instance) {
			checkMin(instance.iterations, 0, "iterations");
			checkMin(instance.saltOffset, 0, "saltOffset");
			checkMin(instance.saltSize, 0, "saltSize");
			checkMin(instance.ciphertextOffset, 0, "ciphertextOffset");
			checkMin(instance.keySizeBits, 0, "keySizeBits");
			return instance;
		}
	}

	public int getIterations() {
		return iterations;
	}

	public int getSaltOffset() {
		return saltOffset;
	}

	public int getSaltSize() {
		return saltSize;
	}

	public int getCiphertextOffset() {
		return ciphertextOffset;
	}

	public int getKeySizeBits() {
		return keySizeBits;
	}

	public String getTransformation() {
		return transformation;
	}
}
