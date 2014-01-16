package org.kuali.common.util.bind.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.bind.api.BindMapping;
import org.kuali.common.util.bind.api.Bound;

public class User {

	private final String name;
	private final File home;
	private final File dir;

	private User(Builder builder) {
		this.name = builder.name;
		this.home = builder.home;
		this.dir = builder.dir;
	}

	public static Builder builder() {
		return new Builder();
	}

	@Bound
	public static class Builder {

		private String name;
		private File home;
		@BindMapping("directory")
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

		public User build() {
			User instance = new User(this);
			validate(instance);
			return instance;
		}

		private static void validate(User instance) {
			checkArgument(!StringUtils.isBlank(instance.name), "'name' cannot be blank");
			checkNotNull(instance.home, "'home' cannot be null");
			checkNotNull(instance.dir, "'dir' cannot be null");
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
