package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.IdiotProofImmutable;

public class BulletProofPojoDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<IdiotProofImmutable>, IdiotProofImmutable> {

	@Override
	public Class<IdiotProofImmutable> getAnnotationType() {
		return IdiotProofImmutable.class;
	}

	@Override
	protected GenericConstraintDef<IdiotProofImmutable> getConstraintDef(IdiotProofImmutable annotation) {
		GenericConstraintDef<IdiotProofImmutable> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
