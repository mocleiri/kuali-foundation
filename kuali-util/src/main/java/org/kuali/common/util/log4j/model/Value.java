package org.kuali.common.util.log4j.model;

/**
 * @deprecated
 */
@Deprecated
public enum Value {

	ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF, NULL;
	// Log4j only supports "null" (lowercase) as a text value inside log4j.xml
	// "NULL" (uppercase) is not supported and causes log4j to emit a WARN level logging message as an unknown level.
	// The best solution would be to get log4j to recognize "NULL" as a synonym for "null"
	// Failing that, another solution would be to get JAXB to translate "NULL" to "null" and vice versa when writing/reading xml.
	// What happens at the moment, is we detect "NULL" and set it to null when creating xml from an object.
	// This causes JAXB to omit the "value" attribute entirely from the xml.
	// When going the other way (ie creating an object from xml) "NULL" is the default value if the "value" attribute is not present.
	// Thus we have a method that works in both directions for dealing with "NULL" vs "null"
	// Granted, this is a tad bit crazy and supremely brittle...

}
