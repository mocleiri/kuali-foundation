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
