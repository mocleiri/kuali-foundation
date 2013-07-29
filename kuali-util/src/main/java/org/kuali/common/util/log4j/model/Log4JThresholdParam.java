package org.kuali.common.util.log4j.model;


public class Log4JThresholdParam extends Log4JParam {

	public static final String NAME = "Threshold";

	public Log4JThresholdParam(Log4JThreshold value) {
		super(NAME, value.name());
	}

}
