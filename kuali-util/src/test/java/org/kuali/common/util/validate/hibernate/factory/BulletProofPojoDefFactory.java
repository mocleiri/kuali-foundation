package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.Immutable;

public class BulletProofPojoDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<Immutable>, Immutable> {

	@Override
	public Class<Immutable> getAnnotationType() {
		return Immutable.class;
	}

	@Override
	protected GenericConstraintDef<Immutable> getConstraintDef(Immutable annotation) {
		GenericConstraintDef<Immutable> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
