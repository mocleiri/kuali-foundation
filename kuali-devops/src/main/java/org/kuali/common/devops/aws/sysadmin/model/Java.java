package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.nullify.NullUtils;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class Java {

	private final Optional<String> home;
	private final List<String> options;
	private final List<String> args;

	public static class Builder {

		// Optional
		private Optional<String> home = Optional.absent();
		private List<String> options = ImmutableList.of();
		private List<String> args = ImmutableList.of();

		public Builder home(String home) {
			this.home = Optional.fromNullable(NullUtils.trimToNull(home));
			return this;
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
			Assert.noNulls(home, options, args);
			this.options = ImmutableList.copyOf(options);
			this.args = ImmutableList.copyOf(args);
			return new Java(this);
		}
	}

	private Java(Builder builder) {
		this.home = builder.home;
		this.options = builder.options;
		this.args = builder.args;
	}

	public Optional<String> getHome() {
		return home;
	}

	public List<String> getOptions() {
		return options;
	}

	public List<String> getArgs() {
		return args;
	}

}
