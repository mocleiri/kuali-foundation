package org.kuali.common.util.taxpayer;

public abstract class SelfReferentialType<T extends SelfReferentialType<T>> {

	private SomeOtherType<T> ref;

	@SuppressWarnings("unchecked")
	private T getThis() {
		return (T) this;
	}

	public void aMethod() {
		ref.myMethod(getThis());
	} // error: incompatible types

}