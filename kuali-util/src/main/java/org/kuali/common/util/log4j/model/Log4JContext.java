package org.kuali.common.util.log4j.model;

import java.util.Map;

import org.apache.log4j.Appender;

public class Log4JContext {

	boolean reset;
	Debug debug;
	Threshold threshold;

	Map<String, ? extends Appender> appenders;

}
