package org.kuali.common.util.log4j;

import static org.kuali.common.util.CollectionUtils.toEmptyList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Appender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DefaultLog4JService implements Log4JService {

	@Override
	public void reset() {
		LogManager.shutdown();
		LogManager.resetConfiguration();
	}

	@Override
	public void configure(List<LoggerContext> contexts) {
		for (LoggerContext context : toEmptyList(contexts)) {
			configure(context);
		}
	}

	@Override
	public void configure(LoggerContext context) {
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
