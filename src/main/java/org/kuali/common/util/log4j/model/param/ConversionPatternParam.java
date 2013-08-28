package org.kuali.common.util.log4j.model.param;


/**
 * @deprecated
 */
@Deprecated
public class ConversionPatternParam extends org.kuali.common.util.log4j.model.Param {

	public static final String NAME = "ConversionPattern";

	public ConversionPatternParam(String pattern) {
		super(NAME, pattern);
	}

}
