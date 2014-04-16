package org.kuali.common.util.encrypt.openssl;

import static org.kuali.common.util.base.Precondition.checkMin;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.encrypt.EncryptionStrength.STRONG_ENCRYPTION_STRENGTH;

import org.kuali.common.util.encrypt.EncryptionStrength;

public final class OpenSSLContext {

	private final int iterations;
	private final String saltPrefix;
	private final int saltSize;
	private final int keySizeBits;
	private final String transformation;
	private final String digestAlgorithm;
	private final String algorithm;

	private OpenSSLContext(Builder builder) {
		this.iterations = builder.iterations;
		this.saltSize = builder.saltSize;
		this.saltPrefix = builder.saltPrefix;
		this.keySizeBits = builder.keySizeBits;
		this.transformation = builder.transformation;
		this.digestAlgorithm = builder.digestAlgorithm;
		this.algorithm = builder.algorithm;
	}

	/**
	 * <p>
	 * <code>EncryptionStrength.STRONG_ENCRYPTION_STRENGTH</code> means AES 256, otherwise AES 128.
	 * <p>
	 * 
	 * <p>
	 * <strong>WARNING:</strong> AES 256 causes an exception to be thrown unless you've installed java's "unlimited strength jurisdiction policy files"
	 * </p>
	 */
	public static OpenSSLContext buildOpenSSLContext(EncryptionStrength strength) {
		if (STRONG_ENCRYPTION_STRENGTH.equals(strength)) {
			return builder().withKeySizeBits(Builder.STRONG_KEY_SIZE_BITS).build();
		} else {
			return buildDefaultOpenSSLContext();
		}
	}

	/**
	 * Returns a context for 128 bit AES encryption compatible with OpenSSL
	 */
	public static OpenSSLContext buildDefaultOpenSSLContext() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<OpenSSLContext> {

		private static final int STRONG_KEY_SIZE_BITS = 256;

		private int iterations = 1;
		private String saltPrefix = "Salted__";
		private int saltSize = 8;
		private int keySizeBits = 128;
		private String transformation = "AES/CBC/PKCS5Padding";
		private String digestAlgorithm = "MD5";
		private String algorithm = "AES";

		private static OpenSSLContext validate(OpenSSLContext instance) {
			checkMin(instance.iterations, 0, "iterations");
			checkNotBlank(instance.saltPrefix, "saltPrefix");
			checkMin(instance.saltSize, 0, "saltSize");
			checkMin(instance.keySizeBits, 0, "keySizeBits");
			checkNotBlank(instance.transformation, "transformation");
			checkNotBlank(instance.digestAlgorithm, "digest");
			checkNotBlank(instance.algorithm, "algorithm");
			return instance;
		}

		@Override
		public OpenSSLContext build() {
			return validate(new OpenSSLContext(this));
		}

		public Builder withAlgorithm(String algorithm) {
			this.algorithm = algorithm;
			return this;
		}

		public Builder withSaltPrefix(String saltPrefix) {
			this.saltPrefix = saltPrefix;
			return this;
		}

		public Builder withDigestAlgorithm(String digestAlgorithm) {
			this.digestAlgorithm = digestAlgorithm;
			return this;
		}

		public Builder withTransformation(String transformation) {
			this.transformation = transformation;
			return this;
		}

		public Builder withIterations(int iterations) {
			this.iterations = iterations;
			return this;
		}

		public Builder withSaltSize(int saltSize) {
			this.saltSize = saltSize;
			return this;
		}

		public Builder withKeySizeBits(int keySizeBits) {
			this.keySizeBits = keySizeBits;
			return this;
		}

		public int getIterations() {
			return iterations;
		}

		public void setIterations(int iterations) {
			this.iterations = iterations;
		}

		public String getSaltPrefix() {
			return saltPrefix;
		}

		public void setSaltPrefix(String saltPrefix) {
			this.saltPrefix = saltPrefix;
		}

		public int getSaltSize() {
			return saltSize;
		}

		public void setSaltSize(int saltSize) {
			this.saltSize = saltSize;
		}

		public int getKeySizeBits() {
			return keySizeBits;
		}

		public void setKeySizeBits(int keySizeBits) {
			this.keySizeBits = keySizeBits;
		}

		public String getTransformation() {
			return transformation;
		}

		public void setTransformation(String transformation) {
			this.transformation = transformation;
		}

		public String getDigestAlgorithm() {
			return digestAlgorithm;
		}

		public void setDigestAlgorithm(String digest) {
			this.digestAlgorithm = digest;
		}

		public String getAlgorithm() {
			return algorithm;
		}

		public void setAlgorithm(String algorithm) {
			this.algorithm = algorithm;
		}
	}

	public int getIterations() {
		return iterations;
	}

	public int getSaltSize() {
		return saltSize;
	}

	public int getKeySizeBits() {
		return keySizeBits;
	}

	public String getTransformation() {
		return transformation;
	}

	public String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public String getSaltPrefix() {
		return saltPrefix;
	}
}
