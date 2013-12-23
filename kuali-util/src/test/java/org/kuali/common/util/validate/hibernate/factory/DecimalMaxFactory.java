package org.kuali.common.util.validate.hibernate.factory;

import javax.validation.constraints.DecimalMax;

import org.hibernate.validator.cfg.defs.DecimalMaxDef;

public class DecimalMaxFactory extends AbstractConstraintDefFactory<DecimalMaxDef, DecimalMax> {

	@Override
	public Class<DecimalMax> getAnnotationType() {
		return DecimalMax.class;
	}

	@Override
	protected DecimalMaxDef getConstraintDef(DecimalMax annotation) {
		DecimalMaxDef def = new DecimalMaxDef();
		def.value(annotation.value());
		def.inclusive(annotation.inclusive());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
