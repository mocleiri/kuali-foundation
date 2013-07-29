package org.kuali.common.util.log4j;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;

public class ResetLog4JExecutable implements Executable {

	boolean skip;
	List<LoggerContext> contexts;
	Log4JService service;

	public ResetLog4JExecutable() {
		this((LoggerContext) null);
	}

	public ResetLog4JExecutable(LoggerContext context) {
		this(CollectionUtils.toEmptyList(context));
	}

	public ResetLog4JExecutable(Log4JService service, LoggerContext context) {
		this(service, CollectionUtils.toEmptyList(context));
	}

	public ResetLog4JExecutable(List<LoggerContext> contexts) {
		this(null, contexts);
	}

	public ResetLog4JExecutable(Log4JService service, List<LoggerContext> contexts) {
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
		service.shutdown();

		// Re-configure log4j
		service.configure(contexts);
	}

}
