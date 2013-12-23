package org.kuali.common.util.validate.hibernate.programmatic;

import javax.validation.constraints.Size;

import org.hibernate.validator.cfg.defs.SizeDef;

public class KualiSizeDef extends SizeDef {

	public KualiSizeDef(Size size) {
		super();
		this.min(size.min());
		this.max(size.max());
		this.groups(size.groups());
		this.message(size.message());
		this.payload(size.payload());
	}

}
