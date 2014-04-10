/**
 * Copyright 2014-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	private final String publicKey;
	private final String privateKey;
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

		private final String name;
		private String publicKey;
		private String privateKey;
		private Optional<String> fingerprint = absent();

		public Builder(String name) {
			this.name = name;
		}

		public Builder withPublicKey(String publicKey) {
			this.publicKey = publicKey;
			return this;
		}

		public Builder withPrivateKey(String privateKey) {
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

		public String getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(String publicKey) {
			this.publicKey = publicKey;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(String privateKey) {
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

	public String getPublicKey() {
		return publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public Optional<String> getFingerprint() {
		return fingerprint;
	}
}
