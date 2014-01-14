package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.GenericConstraintDef;
import org.kuali.common.util.validate.BasicImmutable;

public class BulletProofPojoDefFactory extends AbstractConstraintDefFactory<GenericConstraintDef<BasicImmutable>, BasicImmutable> {

	@Override
	public Class<BasicImmutable> getAnnotationType() {
		return BasicImmutable.class;
	}

	@Override
	protected GenericConstraintDef<BasicImmutable> getConstraintDef(BasicImmutable annotation) {
		GenericConstraintDef<BasicImmutable> def = newGenericConstraintDef(getAnnotationType());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
