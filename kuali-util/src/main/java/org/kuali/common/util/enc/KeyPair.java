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

		// Used by the builder logic
		private Optional<EnvironmentService> env = Optional.absent();
		private Optional<EncryptionService> enc = Optional.absent();
		private static final String NAME_KEY = "ssh.keyName";
		private static final String PUBLIC_KEY = "ssh.publicKey";
		private static final String PRIVATE_KEY = "ssh.privateKey";
		private static final String FINGERPRINT_KEY = "ssh.fingerprint";
		private static final Optional<EncryptionService> ABSENT = Optional.absent();

		public Builder(String name) {
			this(Optional.<EnvironmentService> absent(), ABSENT, name);
		}

		public Builder(EnvironmentService env, String name) {
			this(Optional.of(env), ABSENT, name);
		}

		public Builder(EnvironmentService env, EncryptionService enc, String name) {
			this(Optional.of(env), Optional.of(enc), name);
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

		private void override() {
			if (env.isPresent()) {
				publicKey(SpringUtils.getString(env.get(), PUBLIC_KEY, publicKey).orNull());
				privateKey(SpringUtils.getString(env.get(), PRIVATE_KEY, privateKey).orNull());
				fingerprint(SpringUtils.getString(env.get(), FINGERPRINT_KEY, fingerprint).orNull());
			}
		}

		private void decrypt() {
			privateKey(EncUtils.decrypt(enc, privateKey).orNull());
		}

		private void finish() {
			override();
			decrypt();
		}

		@SuppressWarnings("unchecked")
		private void validate(KeyPair pair, Optional<EncryptionService> enc) {
			Assert.noBlanks(pair.getName());
			Assert.noNulls(pair.getPublicKey(), pair.getPrivateKey(), pair.getFingerprint());
			Assert.noBlanksIfPresent(pair.getPublicKey(), pair.getPrivateKey(), pair.getFingerprint());
			boolean assertDecryptedPrivateKey = pair.getPrivateKey().isPresent() && enc.isPresent();
			if (assertDecryptedPrivateKey) {
				Assert.decrypted(pair.getPrivateKey().get());
			}
		}

		public KeyPair build() {
			Optional<EncryptionService> enc = this.enc;
			finish();
			KeyPair pair = new KeyPair(this);
			validate(pair, enc);
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
