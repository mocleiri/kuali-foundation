package org.kuali.common.util.validate.hibernate.factory;

import javax.validation.constraints.Min;

import org.hibernate.validator.cfg.defs.MinDef;

public class MinDefFactory extends AbstractConstraintDefFactory<MinDef, Min> {

	@Override
	public Class<Min> getAnnotationType() {
		return Min.class;
	}

	@Override
	protected MinDef getConstraintDef(Min annotation) {
		MinDef def = new MinDef();
		def.value(annotation.value());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
