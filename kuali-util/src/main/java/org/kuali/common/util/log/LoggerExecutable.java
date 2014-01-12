package org.kuali.common.util.log;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.execute.Executable;

public class LoggerExecutable implements Executable {

	private static final Object[] EMPTY_OBJECT_ARRAY = {};

	private final LoggerContext context;
	private final boolean skip;

	@Override
	public void execute() {
		if (!skip) {
			LoggerUtils.logMsg(context.getMsg(), context.getArgs().toArray(EMPTY_OBJECT_ARRAY), context.getLogger(), context.getLevel());
		}
	}

	private LoggerExecutable(Builder builder) {
		this.context = builder.context;
		this.skip = builder.skip;
	}

	public static Builder builder(LoggerContext context) {
		return new Builder(context);
	}

	public static class Builder {

		// Required
		private final LoggerContext context;

		// Optional
		private boolean skip = false;

		public Builder(LoggerContext context) {
			this.context = context;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public LoggerExecutable build() {
			LoggerExecutable instance = new LoggerExecutable(this);
			validate(instance);
			return instance;
		}

		private static void validate(LoggerExecutable instance) {
			checkNotNull(instance.context, "context may not be null");
		}
	}

	public LoggerContext getContext() {
		return context;
	}

	public boolean isSkip() {
		return skip;
	}

}
