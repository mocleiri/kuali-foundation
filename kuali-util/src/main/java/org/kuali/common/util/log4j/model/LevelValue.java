package org.kuali.common.util.log4j.model;

public enum LevelValue {

	ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF; // NULL
	// The string "null" (lowercase) is actually a supported value inside log4j
	// However, the log4j parser can't deal with uppercase "NULL".
	// Log4J issues a WARN message if you set value = "NULL"
	// The log4j behavior if value == null, is the same as if you set it to "null"

}
