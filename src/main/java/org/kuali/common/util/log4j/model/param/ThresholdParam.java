package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Param;
import org.kuali.common.util.log4j.model.Value;


public class ThresholdParam extends Param {

	public static final String NAME = "Threshold";

	public ThresholdParam(Value value) {
		super(NAME, value.name());
	}

}
