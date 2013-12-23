package org.kuali.common.util.validate.hibernate.factory;

import javax.validation.constraints.Size;

import org.hibernate.validator.cfg.defs.SizeDef;

public class SizeDefFactory extends AbstractConstraintDefFactory<SizeDef, Size> {

	@Override
	public Class<Size> getAnnotationType() {
		return Size.class;
	}

	@Override
	protected SizeDef getConstraintDef(Size annotation) {
		SizeDef def = new SizeDef();
		def.min(annotation.min());
		def.max(annotation.max());
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
