package org.kuali.common.util.log.log4j.model;

public enum Threshold {

	ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF, NULL;

	public static final Threshold DEFAULT_REPOSITORY_VALUE = NULL;
	public static final Threshold DEFAULT_LOGGER_VALUE = DEBUG;

}
