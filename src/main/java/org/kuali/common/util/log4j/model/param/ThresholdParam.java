package org.kuali.common.util.log4j.model.param;



/**
 * @deprecated
 */
@Deprecated
public class ThresholdParam extends org.kuali.common.util.log4j.model.Param {

	public static final String NAME = "Threshold";

	public ThresholdParam(org.kuali.common.util.log4j.model.Value value) {
		super(NAME, value.name());
	}

}
