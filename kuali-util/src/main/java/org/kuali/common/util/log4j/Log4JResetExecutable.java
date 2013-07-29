package org.kuali.common.util.log4j;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;

public class Log4JResetExecutable implements Executable {

	boolean skip;
	List<LoggerContext> contexts;
	Log4JService service;

	public Log4JResetExecutable() {
		this((LoggerContext) null);
	}

	public Log4JResetExecutable(LoggerContext context) {
		this(CollectionUtils.toEmptyList(context));
	}

	public Log4JResetExecutable(Log4JService service, LoggerContext context) {
		this(service, CollectionUtils.toEmptyList(context));
	}

	public Log4JResetExecutable(List<LoggerContext> contexts) {
		this(null, contexts);
	}

	public Log4JResetExecutable(Log4JService service, List<LoggerContext> contexts) {
		super();
		this.service = service;
		this.contexts = contexts;
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
		service.reset();

		// Re-configure log4j
		service.configure(contexts);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<LoggerContext> getContexts() {
		return contexts;
	}

	public void setContexts(List<LoggerContext> contexts) {
		this.contexts = contexts;
	}

	public Log4JService getService() {
		return service;
	}

	public void setService(Log4JService service) {
		this.service = service;
	}

}
