package org.kuali.common.util.validate.hibernate.programmatic;

import javax.validation.constraints.Min;

public class MinDef extends org.hibernate.validator.cfg.defs.MinDef {

	public MinDef(Min min) {
		super();
		value(min.value());
	}

}
