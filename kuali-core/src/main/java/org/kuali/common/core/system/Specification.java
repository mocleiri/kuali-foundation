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
package org.kuali.common.core.system;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = Specification.Builder.class)
public final class Specification {

	private final String vendor;
	private final String version;
	private final String name;

	private Specification(Builder builder) {
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.name = builder.name;
	}

	public static class Builder extends ValidatingBuilder<Specification> {

		private String vendor;
		private String version;
		private String name;

		public Builder withVendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		@Override
		public Specification build() {
			return validate(new Specification(this));
		}
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

}
