package org.kuali.common.util.log.log4j;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.log4j.model.Log4JConfiguration;

public final class Log4JExecutable implements Executable {

	public static final boolean DEFAULT_SKIP = false;

	private final boolean skip;
	private final Log4JConfiguration context;
	private final Log4JService service;

	public Log4JExecutable(Log4JService service, Log4JConfiguration context) {
		this(service, context, DEFAULT_SKIP);
	}

	public Log4JExecutable(Log4JService service, Log4JConfiguration context, boolean skip) {
		Assert.noNulls(service, context);
		this.service = service;
		this.context = context;
		this.skip = skip;
	}

	@Override
	public void execute() {

		// Might have nothing to do
		if (skip) {
			return;
		}

		// Configure log4j as indicated by the context
		service.configure(context);
	}

	public boolean isSkip() {
		return skip;
	}

	public Log4JService getService() {
		return service;
	}

	public Log4JConfiguration getContext() {
		return context;
	}

}
