package org.kuali.common.util.validate.hibernate.programmatic;

import javax.validation.constraints.Size;

import org.hibernate.validator.cfg.defs.SizeDef;

public class SizeDefFactory extends AbstractConstraintDefFactory<SizeDef, Size> {

	@Override
	public Class<Size> getAnnotatedType() {
		return Size.class;
	}

	@Override
	protected SizeDef getConstraintDef(Size size) {
		return new KualiSizeDef(size);
	}

}
