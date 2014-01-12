package org.kuali.common.util.execute.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerContext;
import org.kuali.common.util.log.LoggerExecutable;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

public class SetSystemPropertyExecutable implements Executable {

	private static final Logger logger = LoggerUtils.make();

	private final String key;
	private final String value;
	private final Optional<LoggerContext> context;
	private final boolean skip;

	@Override
	public void execute() {
		if (skip) {
			return;
		}
		if (context.isPresent()) {
			LoggerExecutable.builder(context.get()).build().execute();
		}
		System.setProperty(key, value);
	}

	private SetSystemPropertyExecutable(Builder builder) {
		this.key = builder.key;
		this.value = builder.value;
		this.skip = builder.skip;
		this.context = builder.context;
	}

	public static Builder builder(String key, String value) {
		return new Builder(key, value);
	}

	public static class Builder {

		// Required
		private final String key;
		private final String value;

		// Optional
		private Optional<LoggerContext> context = Optional.absent();
		private boolean skip = false;

		public Builder(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public Builder log(String msg) {
			return log(msg, ImmutableList.of());
		}

		public Builder log(String msg, List<Object> args) {
			return context(LoggerContext.builder(logger, msg).args(args).build());
		}

		public Builder context(LoggerContext context) {
			this.context = Optional.of(context);
			return this;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public SetSystemPropertyExecutable build() {
			SetSystemPropertyExecutable instance = new SetSystemPropertyExecutable(this);
			validate(instance);
			return instance;
		}

		private static void validate(SetSystemPropertyExecutable instance) {
			checkArgument(!StringUtils.isBlank(instance.key), "key cannot be blank");
			checkArgument(!StringUtils.isBlank(instance.value), "value cannot be blank");
			checkNotNull(instance.context, "context cannot be null");
		}

		public Optional<LoggerContext> getContext() {
			return context;
		}

		public void setContext(Optional<LoggerContext> context) {
			this.context = context;
		}

		public boolean isSkip() {
			return skip;
		}

		public void setSkip(boolean skip) {
			this.skip = skip;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public Optional<LoggerContext> getContext() {
		return context;
	}

	public boolean isSkip() {
		return skip;
	}

}
