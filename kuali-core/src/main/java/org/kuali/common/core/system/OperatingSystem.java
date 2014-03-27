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
@JsonDeserialize(builder = OperatingSystem.Builder.class)
public final class OperatingSystem {

	private final String name;
	private final String arch;
	private final String version;

	private OperatingSystem(Builder builder) {
		this.name = builder.name;
		this.arch = builder.arch;
		this.version = builder.version;
	}

	public static class Builder extends ValidatingBuilder<OperatingSystem> {

		private String name;
		private String arch;
		private String version;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withArch(String arch) {
			this.arch = arch;
			return this;
		}

		public Builder withVersion(String version) {
			this.version = version;
			return this;
		}

		@Override
		public OperatingSystem build() {
			return validate(new OperatingSystem(this));
		}
	}

	public String getName() {
		return name;
	}

	public String getArch() {
		return arch;
	}

	public String getVersion() {
		return version;
	}
}
