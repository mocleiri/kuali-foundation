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

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = PublicKey.Builder.class)
public final class PublicKey {

	private final String name;
	private final String publicKey;

	private PublicKey(Builder builder) {
		this.name = builder.name;
		this.publicKey = builder.publicKey;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<PublicKey> {

		private String name;
		private String publicKey;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withPublicKey(String publicKey) {
			this.publicKey = publicKey;
			return this;
		}

		@Override
		public PublicKey build() {
			return validate(new PublicKey(this));
		}

		public String getPublicKey() {
			return publicKey;
		}

		public void setPublicKey(String publicKey) {
			this.publicKey = publicKey;
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

}
