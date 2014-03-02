package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.IdiotProofBuilder;

public class BulletProofBuilderDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<IdiotProofBuilder>, IdiotProofBuilder> {

	@Override
	public Class<IdiotProofBuilder> getAnnotationType() {
		return IdiotProofBuilder.class;
	}

	@Override
	protected GenericConstraintDef<IdiotProofBuilder> getConstraintDef(IdiotProofBuilder annotation) {
		GenericConstraintDef<IdiotProofBuilder> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
