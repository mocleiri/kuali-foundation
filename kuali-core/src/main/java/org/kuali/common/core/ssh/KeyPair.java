package org.kuali.common.core.ssh;

import static com.google.common.base.Optional.absent;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Optional;

@IdiotProofImmutable
@JsonDeserialize(builder = KeyPair.Builder.class)
public final class KeyPair {

	private final String name;
	private final Optional<String> publicKey;
	private final Optional<String> privateKey;
	private final Optional<String> fingerprint;

	private KeyPair(Builder builder) {
		this.name = builder.name;
		this.publicKey = builder.publicKey;
		this.privateKey = builder.privateKey;
		this.fingerprint = builder.fingerprint;
	}

	public static Builder builder(String name) {
		return new Builder(name);
	}

	public static class Builder extends ValidatingBuilder<KeyPair> {

		// Required
		private final String name;

		// Optional
		private Optional<String> publicKey = absent();
		private Optional<String> privateKey = absent();
		private Optional<String> fingerprint = absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder withPublicKey(String publicKey) {
			return withPublicKey(Optional.of(publicKey));
		}

		@JsonSetter
		public Builder withPublicKey(Optional<String> publicKey) {
			this.publicKey = publicKey;
			return this;
		}

		public Builder withPrivateKey(String privateKey) {
			return withPrivateKey(Optional.of(privateKey));
		}

		@JsonSetter
		public Builder withPrivateKey(Optional<String> privateKey) {
			this.privateKey = privateKey;
			return this;
		}

		public Builder withFingerprint(String fingerprint) {
			return withFingerprint(Optional.of(fingerprint));
		}

		@JsonSetter
		public Builder withFingerprint(Optional<String> fingerprint) {
			this.fingerprint = fingerprint;
			return this;
		}

		@Override
		public KeyPair build() {
			return validate(new KeyPair(this));
		}

		public Optional<String> getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(Optional<String> publicKey) {
			this.publicKey = publicKey;
		}

		public Optional<String> getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(Optional<String> privateKey) {
			this.privateKey = privateKey;
		}

		public Optional<String> getFingerprint() {
			return fingerprint;
		}

		public void setFingerprint(Optional<String> fingerprint) {
			this.fingerprint = fingerprint;
		}

		public String getName() {
			return name;
		}
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
