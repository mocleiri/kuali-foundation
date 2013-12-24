package org.kuali.common.util.taxpayer;

public class Subtype extends SelfReferentialType<Subtype> {

	@Override
	protected Subtype getThis() {
		return this;
	}
	
}