package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;

public final class Package {

	private final String title;
	private final String name;
	private final Optional<String> description;

	public static class Builder {

		// Required
		private final String title;
		private final String name;

		// Optional
		private Optional<String> description = Optional.absent();

		public Builder(String name) {
			this(name, name);
		}

		public Builder(String title, String name) {
			this.title = title;
			this.name = name;
		}

		public Builder description(String description) {
			this.description = Optional.fromNullable(NullUtils.trimToNull(description));
			return this;
		}

		public Package build() {
			Assert.noBlanks(title, name);
			return new Package(this);
		}
	}

	private Package(Builder builder) {
		this.title = builder.title;
		this.name = builder.name;
		this.description = builder.description;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public Optional<String> getDescription() {
		return description;
	}

}
