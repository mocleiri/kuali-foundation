package org.kuali.common.util.validate.hibernate.factory;

import org.hibernate.validator.cfg.defs.CreditCardNumberDef;
import org.hibernate.validator.constraints.CreditCardNumber;

public class CreditCardNumberDefFactory extends AbstractConstraintDefFactory<CreditCardNumberDef, CreditCardNumber> {

	@Override
	public Class<CreditCardNumber> getAnnotationType() {
		return CreditCardNumber.class;
	}

	@Override
	protected CreditCardNumberDef getConstraintDef(CreditCardNumber annotation) {
		CreditCardNumberDef def = new CreditCardNumberDef();
		def.message(annotation.message());
		def.groups(annotation.groups());
		def.payload(annotation.payload());
		return def;
	}

}
