package org.kuali.common.liquibase;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

public final class LiquibaseUpdateExecutable implements Executable {

	private final boolean skip;
	private final LiquibaseService service;
	private final LiquibaseContext context;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		service.update(context);

	}

	public static class Builder {

		// Required
		private final LiquibaseService service;
		private final LiquibaseContext context;

		// Optional
		private boolean skip = false;

		public Builder(LiquibaseService service, LiquibaseContext context) {
			this.service = service;
			this.context = context;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public LiquibaseUpdateExecutable build() {
			Assert.noNulls(service, context);
			return new LiquibaseUpdateExecutable(this);
		}

	}

	private LiquibaseUpdateExecutable(Builder builder) {
		this.skip = builder.skip;
		this.service = builder.service;
		this.context = builder.context;
	}

	public boolean isSkip() {
		return skip;
	}

	public LiquibaseService getService() {
		return service;
	}

	public LiquibaseContext getContext() {
		return context;
	}

}
