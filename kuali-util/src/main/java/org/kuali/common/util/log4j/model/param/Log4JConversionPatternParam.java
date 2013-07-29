package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Log4JParam;

public class Log4JConversionPatternParam extends Log4JParam {

	public static final String NAME = "ConversionPattern";

	public Log4JConversionPatternParam(String pattern) {
		super(NAME, pattern);
	}

}
