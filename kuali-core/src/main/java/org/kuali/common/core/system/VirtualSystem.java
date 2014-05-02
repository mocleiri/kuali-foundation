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

import static org.kuali.common.core.system.VirtualSystemHelper.createVirtualSystem;

import java.util.Properties;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;
import org.kuali.common.core.validate.annotation.IgnoreBlanks;
import org.kuali.common.util.property.ImmutableProperties;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Strongly typed and immutable version of {@code java.lang.System.getProperties()}
 */
@IdiotProofImmutable
@JsonDeserialize(builder = VirtualSystem.Builder.class)
public final class VirtualSystem {

	private final User user;
	private final OperatingSystem os;
	private final Java java;
	@IgnoreBlanks
	private final String lineSeparator;
	private final String pathSeparator;
	private final String fileSeparator;
	private final ImmutableProperties properties;
	private final ImmutableProperties environment;

	/**
	 * Create a new immutable VirtualSystem instance representing the current state of the system we are running on.
	 */
	public static VirtualSystem create() {
		return createVirtualSystem();
	}

	public static Builder builder() {
		return new Builder();
	}

	private VirtualSystem(Builder builder) {
		this.user = builder.user;
		this.os = builder.os;
		this.java = builder.java;
		this.lineSeparator = builder.lineSeparator;
		this.pathSeparator = builder.pathSeparator;
		this.fileSeparator = builder.fileSeparator;
		this.properties = ImmutableProperties.copyOf(builder.properties);
		this.environment = ImmutableProperties.copyOf(builder.environment);
	}

	public static class Builder extends ValidatingBuilder<VirtualSystem> {

		private User user;
		private OperatingSystem os;
		private Java java;
		private String lineSeparator;
		private String pathSeparator;
		private String fileSeparator;
		private Properties properties = new Properties();
		private Properties environment = new Properties();

		public Builder withUser(User user) {
			this.user = user;
			return this;
		}

		public Builder withOs(OperatingSystem os) {
			this.os = os;
			return this;
		}

		public Builder withJava(Java java) {
			this.java = java;
			return this;
		}

		public Builder withLineSeparator(String lineSeparator) {
			this.lineSeparator = lineSeparator;
			return this;
		}

		public Builder withPathSeparator(String pathSeparator) {
			this.pathSeparator = pathSeparator;
			return this;
		}

		public Builder withFileSeparator(String fileSeparator) {
			this.fileSeparator = fileSeparator;
			return this;
		}

		public Builder withProperties(Properties properties) {
			this.properties = properties;
			return this;
		}

		public Builder withEnvironment(Properties environment) {
			this.environment = environment;
			return this;
		}

		@Override
		public VirtualSystem build() {
			return validate(new VirtualSystem(this));
		}

	}

	public User getUser() {
		return user;
	}

	public OperatingSystem getOs() {
		return os;
	}

	public Java getJava() {
		return java;
	}

	public String getLineSeparator() {
		return lineSeparator;
	}

	public String getPathSeparator() {
		return pathSeparator;
	}

	public String getFileSeparator() {
		return fileSeparator;
	}

	public Properties getProperties() {
		return properties;
	}

	public Properties getEnvironment() {
		return environment;
	}

}
