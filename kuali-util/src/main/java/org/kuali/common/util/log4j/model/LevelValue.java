package org.kuali.common.util.log4j.model;

public enum LevelValue {

	ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF; // NULL
	// The string "null" (lowercase) is actually a supported value inside log4j.xml
	// However, the log4j parser can't deal with uppercase "NULL". (It issues an ominous sounding WARN message)
	// It quite happily parses all of the other uppercase values

}
