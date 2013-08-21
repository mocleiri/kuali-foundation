package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Param;

/**
 * @deprecated
 */
@Deprecated
public class ConversionPatternParam extends Param {

	public static final String NAME = "ConversionPattern";

	public ConversionPatternParam(String pattern) {
		super(NAME, pattern);
	}

}
