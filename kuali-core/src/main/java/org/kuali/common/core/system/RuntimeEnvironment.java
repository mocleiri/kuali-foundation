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
@JsonDeserialize(builder = RuntimeEnvironment.Builder.class)
public final class RuntimeEnvironment {

	private final String vendor;
	private final String version;
	private final String vendorUrl;
	private final Specification specification;

	private RuntimeEnvironment(Builder builder) {
		this.specification = builder.specification;
		this.vendor = builder.vendor;
		this.version = builder.version;
		this.vendorUrl = builder.vendorUrl;
	}

	public static class Builder extends ValidatingBuilder<RuntimeEnvironment> {

		private String version;
		private String vendor;
		private String vendorUrl;
		private Specification specification;

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		public Builder withVendor(String vendor) {
			this.vendor = vendor;
			return this;
		}

		public Builder withVendorUrl(String vendorUrl) {
			this.vendorUrl = vendorUrl;
			return this;
		}

		public Builder withSpecification(Specification specification) {
			this.specification = specification;
			return this;
		}

		@Override
		public RuntimeEnvironment build() {
			return validate(new RuntimeEnvironment(this));
		}
	}

	public String getVendor() {
		return vendor;
	}

	public String getVersion() {
		return version;
	}

	public String getVendorUrl() {
		return vendorUrl;
	}

	public Specification getSpecification() {
		return specification;
	}

}
