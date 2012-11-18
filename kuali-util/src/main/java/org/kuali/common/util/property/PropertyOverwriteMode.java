package org.kuali.common.util.property;

public enum PropertyOverwriteMode {
	IGNORE, // Proceed silently, emit no logging messages
	DEBUG, // Log a debug message - log.debug()
	INFORM, // Log an info level message - log.info()
	WARN, // Log a warn level message - log.warn()
	ERROR; // Throw an exception
}
