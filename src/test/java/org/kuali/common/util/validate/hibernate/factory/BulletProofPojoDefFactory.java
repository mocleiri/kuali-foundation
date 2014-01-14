package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.BulletProofPojo;

public class BulletProofPojoDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<BulletProofPojo>, BulletProofPojo> {

	@Override
	public Class<BulletProofPojo> getAnnotationType() {
		return BulletProofPojo.class;
	}

	@Override
	protected GenericConstraintDef<BulletProofPojo> getConstraintDef(BulletProofPojo annotation) {
		GenericConstraintDef<BulletProofPojo> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
