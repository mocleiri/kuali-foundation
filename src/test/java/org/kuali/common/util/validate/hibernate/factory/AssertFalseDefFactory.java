package org.kuali.common.util.validate.hibernate.factory;

import javax.validation.constraints.AssertFalse;

import org.hibernate.validator.cfg.defs.AssertFalseDef;

public class AssertFalseDefFactory extends AbstractConstraintDefFactory<AssertFalseDef, AssertFalse> {

	@Override
	public Class<AssertFalse> getAnnotationType() {
		return AssertFalse.class;
	}

	@Override
	protected AssertFalseDef getConstraintDef(AssertFalse annotation) {
		AssertFalseDef def = new AssertFalseDef();
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
