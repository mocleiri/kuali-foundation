package org.kuali.common.util.log4j;

import static org.kuali.common.util.CollectionUtils.toEmptyList;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.kuali.common.util.execute.Executable;

public class ResetLog4JExecutable implements Executable {

	boolean skip;
	List<LoggerContext> contexts;

	public ResetLog4JExecutable() {
		this((LoggerContext) null);
	}

	public ResetLog4JExecutable(LoggerContext context) {
		this(Arrays.asList(context));
	}

	public ResetLog4JExecutable(List<LoggerContext> contexts) {
		super();
		this.contexts = contexts;
	}

	@Override
	public void execute() {

		// Might have nothing to do
		if (skip) {
			return;
		}

		// Remove all existing log4j configuration
		LogManager.shutdown();

		// Re-configure
		for (LoggerContext context : toEmptyList(contexts)) {
			configure(context);
		}
	}

	protected void configure(LoggerContext context) {
		// Get a handle to the the appropriate logger
		Logger logger = getLogger(context);

		// Set the logging level
		logger.setLevel(context.getLevel());

		// Add appenders
		for (Appender appender : toEmptyList(context.getAppenders())) {
			logger.addAppender(appender);
		}

		// Add other configuration
		logger.setResourceBundle(context.getResourceBundle());
		logger.setAdditivity(context.isAdditive());
	}

	protected Logger getLogger(LoggerContext context) {
		if (context.isRootLogger()) {
			return Logger.getRootLogger();
		} else if (!StringUtils.isBlank(context.getLoggerName())) {
			return Logger.getLogger(context.getLoggerName());
		} else {
			return Logger.getLogger(context.getLoggerClass());
		}
	}

}
