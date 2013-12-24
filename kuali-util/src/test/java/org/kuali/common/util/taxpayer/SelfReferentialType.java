package org.kuali.common.util.taxpayer;

public abstract class SelfReferentialType<T extends SelfReferentialType<T>> {

	private SomeOtherType<T> ref;

	protected abstract T getThis();

	public void aMethod() {
		ref.myMethod(getThis());
	}
}