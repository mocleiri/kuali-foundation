package org.kuali.common.util.log4j.model;

public enum LevelValue {

	ALL, TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF, NULL;
	// Log4j only supports "null" (lowercase) as a value inside log4j.xml
	// "NULL" (uppercase) is not supported and causes log4j to emit a WARN level logging message as an unknown level.
	// A proper solution here would be to get JAXB to translate "NULL" to "null" and vice versa when reading/writing XML.
	// What happens at the moment, is we detect values set to "NULL" and set them to null on the way out.
	// This causes JAXB to omit them when creating XML from an object.
	// When creating an object from the XML "NULL" is the default value when nothing is present in the XML.
	// This is a tad bit crazy and quite brittle...

}
