package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.IdiotProof;

public class BulletProofPojoDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<IdiotProof>, IdiotProof> {

	@Override
	public Class<IdiotProof> getAnnotationType() {
		return IdiotProof.class;
	}

	@Override
	protected GenericConstraintDef<IdiotProof> getConstraintDef(IdiotProof annotation) {
		GenericConstraintDef<IdiotProof> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
