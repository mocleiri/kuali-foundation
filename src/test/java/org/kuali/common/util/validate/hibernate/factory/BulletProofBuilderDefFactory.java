package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.BulletProofBuilder;

public class BulletProofBuilderDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<BulletProofBuilder>, BulletProofBuilder> {

	@Override
	public Class<BulletProofBuilder> getAnnotationType() {
		return BulletProofBuilder.class;
	}

	@Override
	protected GenericConstraintDef<BulletProofBuilder> getConstraintDef(BulletProofBuilder annotation) {
		GenericConstraintDef<BulletProofBuilder> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
