package org.kuali.common.util.validate.hibernate.factory;

import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.cfg.defs.DecimalMinDef;

public class DecimalMinDefFactory extends AbstractConstraintDefFactory<DecimalMinDef, DecimalMin> {

	@Override
	public Class<DecimalMin> getAnnotationType() {
		return DecimalMin.class;
	}

	@Override
	protected DecimalMinDef getConstraintDef(DecimalMin annotation) {
		DecimalMinDef def = new DecimalMinDef();
		def.value(annotation.value());
		def.inclusive(annotation.inclusive());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
