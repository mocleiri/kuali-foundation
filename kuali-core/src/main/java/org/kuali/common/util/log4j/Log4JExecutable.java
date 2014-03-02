package org.kuali.common.util.log4j;

import org.kuali.common.util.Assert;
import org.kuali.common.util.execute.Executable;

/**
 * @deprecated
 */
@Deprecated
public class Log4JExecutable implements Executable {

	boolean skip;
	org.kuali.common.util.log4j.model.Log4JContext context;
	Log4JService service;

	public Log4JExecutable() {
		this(null, null);
	}

	public Log4JExecutable(Log4JService service, org.kuali.common.util.log4j.model.Log4JContext context) {
		this(service, context, false);
	}

	public Log4JExecutable(Log4JService service, org.kuali.common.util.log4j.model.Log4JContext context, boolean skip) {
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
		Assert.notNull(context, "context is null");

		// Configure log4j as indicated by the context
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

	public org.kuali.common.util.log4j.model.Log4JContext getContext() {
		return context;
	}

	public void setContext(org.kuali.common.util.log4j.model.Log4JContext context) {
		this.context = context;
	}

}
