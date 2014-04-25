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
@JsonDeserialize(builder = PrivateKey.Builder.class)
public final class PrivateKey {

	private final String name;
	private final String value;

	private PrivateKey(Builder builder) {
		this.name = builder.name;
		this.value = builder.value;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<PrivateKey> {

		private String name;
		private String value;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withValue(String content) {
			this.value = content;
			return this;
		}

		@Override
		public PrivateKey build() {
			return validate(new PrivateKey(this));
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String content) {
			this.value = content;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

}
