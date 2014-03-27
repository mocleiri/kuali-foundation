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

import java.io.File;

import org.kuali.common.core.build.ValidatingBuilder;
import org.kuali.common.core.validate.annotation.IdiotProofImmutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@IdiotProofImmutable
@JsonDeserialize(builder = User.Builder.class)
public final class User {

	private final String name;
	private final File home;
	private final File dir;

	private User(Builder builder) {
		this.name = builder.name;
		this.home = builder.home;
		this.dir = builder.dir;
	}

	public static class Builder extends ValidatingBuilder<User> {

		private String name;
		private File home;
		private File dir;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withHome(File home) {
			this.home = home;
			return this;
		}

		public Builder withDir(File dir) {
			this.dir = dir;
			return this;
		}

		@Override
		public User build() {
			return validate(new User(this));
		}
	}

	public String getName() {
		return name;
	}

	public File getHome() {
		return home;
	}

	public File getDir() {
		return dir;
	}

}
