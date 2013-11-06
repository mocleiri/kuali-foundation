package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class KeyPair {

	private final String name;
	private final Optional<String> publicKey;
	private final Optional<String> privateKey;
	private final Optional<String> fingerprint;

	public static class Builder {

		// Required
		private final String name;

		// Optional
		private Optional<String> publicKey = Optional.absent();
		private Optional<String> privateKey = Optional.absent();
		private Optional<String> fingerprint = Optional.absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder publicKey(String publicKey) {
			this.publicKey = Optional.fromNullable(NullUtils.trimToNull(publicKey));
			return this;
		}

		public Builder privateKey(String privateKey) {
			this.privateKey = Optional.fromNullable(NullUtils.trimToNull(privateKey));
			return this;
		}

		public Builder fingerprint(String fingerprint) {
			this.fingerprint = Optional.fromNullable(NullUtils.trimToNull(fingerprint));
			return this;
		}

		public KeyPair build() {
			Assert.noBlanks(name);
			Assert.noNulls(publicKey, privateKey);
			if (publicKey.isPresent()) {
				Assert.noBlanks(publicKey.get());
			}
			if (privateKey.isPresent()) {
				Assert.noBlanks(privateKey.get());
			}
			return new KeyPair(this);
		}

	}

	private KeyPair(Builder builder) {
		this.name = builder.name;
		this.publicKey = builder.publicKey;
		this.privateKey = builder.privateKey;
		this.fingerprint = builder.fingerprint;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getPublicKey() {
		return publicKey;
	}

	public Optional<String> getPrivateKey() {
		return privateKey;
	}

	public Optional<String> getFingerprint() {
		return fingerprint;
	}

}
