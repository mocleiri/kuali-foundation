package org.kuali.common.util.ssh.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.EncryptionService;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvUtils;
import org.kuali.common.util.spring.env.EnvironmentService;

import com.google.common.base.Optional;

public final class KeyPair {

	private final String name;
	private final Optional<String> publicKey;
	private final Optional<String> privateKey;
	private final Optional<String> fingerprint;

	public static class Builder {

		// Required
		private final String name; // No default
		private final Optional<EnvironmentService> env; // Defaults to Optional.absent() if not supplied
		private final Optional<EncryptionService> enc; // Defaults to Optional.absent() if not supplied

		// Optional
		private Optional<String> publicKey = Optional.absent();
		private Optional<String> privateKey = Optional.absent();
		private Optional<String> fingerprint = Optional.absent();

		private static final String NAME_KEY = "ssh.keyName";
		private static final String PUBLIC_KEY = "ssh.publicKey";
		private static final String PRIVATE_KEY = "ssh.privateKey";
		private static final String FINGERPRINT_KEY = "ssh.fingerprint";

		public Builder(String name) {
			this(EnvUtils.ABSENT, EncUtils.ABSENT, name);
		}

		public Builder(EnvironmentService env, String name) {
			this(Optional.of(env), EncUtils.ABSENT, name);
		}

		public Builder(EnvironmentService env, EncryptionService enc, String name) {
			this(Optional.of(env), Optional.of(enc), name);
		}

		public Builder(EnvironmentService env, KeyPair provided) {
			this(Optional.of(env), EncUtils.ABSENT, provided.getName());
			initialize(provided);
		}

		public Builder(EnvironmentService env, EncryptionService enc, KeyPair provided) {
			this(Optional.of(env), Optional.of(enc), provided.getName());
			initialize(provided);
		}

		private Builder(Optional<EnvironmentService> env, Optional<EncryptionService> enc, String name) {
			if (env.isPresent()) {
				this.name = env.get().getString(NAME_KEY, name);
			} else {
				this.name = name;
			}
			this.env = env;
			this.enc = enc;
		}

		public Builder publicKey(String publicKey) {
			this.publicKey = NullUtils.toAbsent(publicKey);
			return this;
		}

		public Builder privateKey(String privateKey) {
			this.privateKey = NullUtils.toAbsent(privateKey);
			return this;
		}

		public Builder fingerprint(String fingerprint) {
			this.fingerprint = NullUtils.toAbsent(fingerprint);
			return this;
		}

		private void initialize(KeyPair provided) {
			publicKey(provided.getPublicKey().orNull());
			privateKey(provided.getPrivateKey().orNull());
			fingerprint(provided.getFingerprint().orNull());
		}

		private void override() {
			if (env.isPresent()) {
				publicKey(SpringUtils.getString(env.get(), PUBLIC_KEY, publicKey).orNull());
				privateKey(SpringUtils.getString(env.get(), PRIVATE_KEY, privateKey).orNull());
				fingerprint(SpringUtils.getString(env.get(), FINGERPRINT_KEY, fingerprint).orNull());
			}
		}

		private void finish() {
			override();
			privateKey(EncUtils.decrypt(enc, privateKey).orNull());
		}

		private void validate(KeyPair pair, boolean assertDecryptedPrivateKey) {
			Assert.noBlanks(pair.getName());
			Assert.noNulls(pair.getPublicKey(), pair.getPrivateKey(), pair.getFingerprint());
			Assert.noBlanks(pair.getPublicKey(), pair.getPrivateKey(), pair.getFingerprint());
			if (assertDecryptedPrivateKey && pair.getPrivateKey().isPresent()) {
				Assert.notEncrypted(pair.getPrivateKey().get());
			}
		}

		public KeyPair build() {
			finish();
			boolean assertDecryptedPrivateKey = enc.isPresent();
			KeyPair pair = new KeyPair(this);
			validate(pair, assertDecryptedPrivateKey);
			return pair;
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
