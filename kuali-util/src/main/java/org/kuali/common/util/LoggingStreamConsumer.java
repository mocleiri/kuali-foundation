package org.kuali.common.util;

import org.codehaus.plexus.util.cli.StreamConsumer;
import org.slf4j.Logger;

public class LoggingStreamConsumer implements StreamConsumer {

	Logger logger;
	LoggerLevel level;

	public LoggingStreamConsumer() {
		this(null);
	}

	public LoggingStreamConsumer(Logger logger) {
		this(logger, LoggerLevel.INFO);
	}

	public LoggingStreamConsumer(Logger logger, LoggerLevel level) {
		super();
		this.logger = logger;
		this.level = level;
	}

	@Override
	public void consumeLine(String line) {
		switch (level) {
		case TRACE:
			logger.trace(line);
			return;
		case DEBUG:
			logger.debug(line);
			return;
		case INFO:
			logger.info(line);
			return;
		case WARN:
			logger.warn(line);
			return;
		case ERROR:
			logger.error(line);
			return;
		default:
			throw new IllegalStateException("Logger level " + level + " is unknown");
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
