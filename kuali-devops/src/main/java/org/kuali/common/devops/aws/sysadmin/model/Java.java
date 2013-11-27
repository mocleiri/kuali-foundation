package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Java {

	private final String home;
	private final List<String> options;

	public static class Builder {

		// Required
		private final String home;

		// Optional
		private List<String> options = ImmutableList.of();

		public Builder(String home) {
			this.home = home;
		}

		public Builder options(List<String> options) {
			this.options = options;
			return this;
		}

		public Java build() {
			Assert.noBlanks(home);
			Assert.noNulls(options);
			this.options = ImmutableList.copyOf(options);
			return new Java(this);
		}
	}

	private Java(Builder builder) {
		this.home = builder.home;
		this.options = builder.options;
	}

	public List<String> getOptions() {
		return options;
	}

	public String getHome() {
		return home;
	}

}
