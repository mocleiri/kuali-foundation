package org.kuali.common.util.log4j;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log4j.model.Log4JContext;

public class Log4JConfigurationExecutable implements Executable {

	boolean skip;
	Log4JContext context;
	Log4JService service;

	public Log4JConfigurationExecutable() {
		this(null, null);
	}

	public Log4JConfigurationExecutable(Log4JService service, Log4JContext context) {
		this(service, context, false);
	}

	public Log4JConfigurationExecutable(Log4JService service, Log4JContext context, boolean skip) {
		super();
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

		// Make sure we are configured correctly
		Assert.notNull(service, "service is null");

		// Turn off all logging and remove all log4j configuration
		service.configure(context);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Log4JService getService() {
		return service;
	}

	public void setService(Log4JService service) {
		this.service = service;
	}

	public Log4JContext getContext() {
		return context;
	}

	public void setContext(Log4JContext context) {
		this.context = context;
	}

}
