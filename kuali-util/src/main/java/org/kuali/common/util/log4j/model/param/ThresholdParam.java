package org.kuali.common.util.log4j.model.param;

import org.kuali.common.util.log4j.model.Param;
import org.kuali.common.util.log4j.model.LevelValue;


public class ThresholdParam extends Param {

	public static final String NAME = "Threshold";

	public ThresholdParam(LevelValue value) {
		super(NAME, value.name());
	}

}
