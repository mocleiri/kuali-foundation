package org.kuali.common.devops.aws.sysadmin.model;

import org.kuali.common.util.Assert;

public final class Package {

	private final String title;
	private final String name;

	public static class Builder {

		// Required
		private final String title;
		private final String name;

		public Builder(String name) {
			this(name, name);
		}

		public Builder(String title, String name) {
			this.title = title;
			this.name = name;
		}

		public Package build() {
			Assert.noBlanks(title, name);
			return new Package(this);
		}
	}

	private Package(Builder builder) {
		this.title = builder.title;
		this.name = builder.name;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

}
