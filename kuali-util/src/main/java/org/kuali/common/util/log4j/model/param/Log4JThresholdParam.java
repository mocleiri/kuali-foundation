package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Log4JParam;
import org.kuali.common.util.log4j.model.Log4JLevelValue;


public class Log4JThresholdParam extends Log4JParam {

	public static final String NAME = "Threshold";

	public Log4JThresholdParam(Log4JLevelValue value) {
		super(NAME, value.name());
	}

}
