package org.kuali.common.util.enc;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

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
			this.publicKey = NullUtils.toNull(publicKey);
			return this;
		}

		public Builder privateKey(String privateKey) {
			this.privateKey = NullUtils.toNull(privateKey);
			return this;
		}

		public Builder fingerprint(String fingerprint) {
			this.fingerprint = NullUtils.toNull(fingerprint);
			return this;
		}

		public Builder override(EnvironmentService env) {
			publicKey(SpringUtils.getString(env, "ssh.publicKey", publicKey).orNull());
			privateKey(SpringUtils.getString(env, "ssh.privateKey", privateKey).orNull());
			fingerprint(SpringUtils.getString(env, "ssh.fingerprint", fingerprint).orNull());
			return this;
		}

		public Builder decrypt(EncryptionService enc) {
			if (privateKey.isPresent()) {
				privateKey(EncUtils.decrypt(enc, privateKey.get()));
			}
			return this;
		}

		@SuppressWarnings("unchecked")
		public KeyPair build() {
			Assert.noBlanks(name);
			Assert.noNulls(publicKey, privateKey, fingerprint);
			Assert.noBlanksIfPresent(publicKey, privateKey, fingerprint);
			if (privateKey.isPresent()) {
				Assert.decrypted(privateKey.get());
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
