package org.kuali.common.util.vm;

import java.io.File;

import org.kuali.common.util.build.AwesomeBuilder;
import org.kuali.common.util.spring.binder.CanonicalFileFormat;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class User {

	private final String name;
	private final File home;
	private final File dir;

	private User(Builder builder) {
		this.name = builder.name;
		this.home = builder.home;
		this.dir = builder.dir;
	}

	public static User create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends AwesomeBuilder<User> {

		private String name;

		@CanonicalFileFormat
		private File home;

		@CanonicalFileFormat
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
		public User getInstance() {
			return new User(this);
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
