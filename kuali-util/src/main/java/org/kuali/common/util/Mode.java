package org.kuali.common.util;

public enum Mode {
	IGNORE, // Proceed silently, emit no logging messages
	DEBUG, // Log a debug message
	INFORM, // Log an info level message
	WARN, // Log a warn level message
	ERROR; // Throw an exception
}
