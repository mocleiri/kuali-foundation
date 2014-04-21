/**
 * Copyright 2010-2014 The Kuali Foundation
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
package org.springframework.jdbc.datasource;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

@IdiotProofImmutable
public final class Versioned {

	private final String name;
	private final String version;

	private Versioned(Builder builder) {
		this.name = builder.name;
		this.version = builder.version;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Versioned> {

		private String name;
		private String version;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		@Override
		public Versioned build() {
			return validate(new Versioned(this));
		}
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

}
