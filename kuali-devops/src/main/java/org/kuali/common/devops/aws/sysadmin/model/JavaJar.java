package org.kuali.common.devops.aws.sysadmin.model;

import java.io.File;
import java.util.List;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public final class JavaJar {

	private final List<String> options;
	private final File jar;
	private final List<String> args;

	public static class Builder {

		// Required
		private final File jar;

		// Optional
		private List<String> options = ImmutableList.of();
		private List<String> args = ImmutableList.of();

		public Builder(File jar) {
			this.jar = jar;
		}

		public Builder options(List<String> options) {
			this.options = options;
			return this;
		}

		public Builder args(List<String> args) {
			this.args = args;
			return this;
		}

		public JavaJar build() {
			Assert.noNulls(options, args, jar);
			Assert.exists(jar);
			this.options = ImmutableList.copyOf(options);
			this.args = ImmutableList.copyOf(args);
			return new JavaJar(this);
		}
	}

	private JavaJar(Builder builder) {
		this.jar = builder.jar;
		this.options = builder.options;
		this.args = builder.args;
	}

	public List<String> getOptions() {
		return options;
	}

	public List<String> getArgs() {
		return args;
	}

	public File getJar() {
		return jar;
	}

}
