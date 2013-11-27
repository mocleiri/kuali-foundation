package org.kuali.common.devops.aws.sysadmin.model;

import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public final class Java {

	private final Optional<String> home;
	private final List<String> options;
	private final List<String> args;

	public static class Builder {

		// Optional
		private final Optional<String> home = Optional.absent();
		private final List<String> options = ImmutableList.of();
		private final List<String> args = ImmutableList.of();

		public Java build() {
			Assert.noNulls(home, options, args);
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
