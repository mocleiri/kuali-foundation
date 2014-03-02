package org.kuali.common.util.validate.hibernate.factory;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.cfg.defs.AssertTrueDef;

public class AssertTrueDefFactory extends AbstractConstraintDefFactory<AssertTrueDef, AssertTrue> {

	@Override
	public Class<AssertTrue> getAnnotationType() {
		return AssertTrue.class;
	}

	@Override
	protected AssertTrueDef getConstraintDef(AssertTrue annotation) {
		AssertTrueDef def = new AssertTrueDef();
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
