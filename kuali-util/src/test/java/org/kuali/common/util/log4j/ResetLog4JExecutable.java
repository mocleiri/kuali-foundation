package org.kuali.common.util.log4j;

import static org.kuali.common.util.CollectionUtils.toEmptyList;

import java.util.Arrays;
import java.util.List;

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

		if (skip) {
			// Don't do anything
			return;
		}

		// Remove all existing log4j configuration
		LogManager.shutdown();

		// Re-configure log4j as desired
		for (LoggerContext context : toEmptyList(contexts)) {

			// Get a handle to the the appropriate logger
			Logger logger = context.isRootLogger() ? Logger.getRootLogger() : Logger.getLogger(context.getLoggerClass());

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
	}

}
