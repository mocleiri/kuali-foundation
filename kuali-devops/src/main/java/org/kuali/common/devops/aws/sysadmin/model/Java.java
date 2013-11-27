package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class Java {

	private final List<String> options;
	private final List<String> args;
	private final String javaClass;

	public static class Builder {

		// Required
		private final String javaClass;

		// Optional
		private List<String> options = ImmutableList.of();
		private List<String> args = ImmutableList.of();

		public Builder(String javaClass) {
			this.javaClass = javaClass;
		}

		public Builder options(List<String> options) {
			this.options = options;
			return this;
		}

		public Builder args(List<String> args) {
			this.args = args;
			return this;
		}

		public Java build() {
			Assert.noBlanks(javaClass);
			Assert.noNulls(options, args);
			this.options = ImmutableList.copyOf(options);
			this.args = ImmutableList.copyOf(args);
			return new Java(this);
		}
	}

	private Java(Builder builder) {
		this.javaClass = builder.javaClass;
		this.options = builder.options;
		this.args = builder.args;
	}

	public List<String> getOptions() {
		return options;
	}

	public List<String> getArgs() {
		return args;
	}

	public String getJavaClass() {
		return javaClass;
	}

}
