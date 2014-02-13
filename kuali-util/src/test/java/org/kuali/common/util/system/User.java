package org.kuali.common.util.system;

import static org.kuali.common.util.validate.Validation.checkConstraints;

import java.io.File;

import org.kuali.common.util.bind.api.Alias;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class User {

	private final String name;
	private final File home;
	@Alias("directory")
	private final File dir;

	private User(Builder builder) {
		this.name = builder.name;
		this.home = builder.home;
		this.dir = builder.dir;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder implements org.apache.commons.lang3.builder.Builder<User> {

		private String name;
		private File home;
		private File dir;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder home(File home) {
			this.home = home;
			return this;
		}

		public Builder dir(File dir) {
			this.dir = dir;
			return this;
		}

		@Override
		public User build() {
			return checkConstraints(new User(this));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public File getHome() {
			return home;
		}

		public void setHome(File home) {
			this.home = home;
		}

		public File getDir() {
			return dir;
		}

		public void setDir(File dir) {
			this.dir = dir;
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
