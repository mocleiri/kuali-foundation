package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.ValidPort;

public class ValidPortDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<ValidPort>, ValidPort> {

	@Override
	public Class<ValidPort> getAnnotationType() {
		return ValidPort.class;
	}

	@Override
	protected GenericConstraintDef<ValidPort> getConstraintDef(ValidPort annotation) {
		GenericConstraintDef<ValidPort> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
