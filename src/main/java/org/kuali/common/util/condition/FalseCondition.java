package org.kuali.common.util.condition;

public final class FalseCondition implements Condition {

	@Override
	public boolean isTrue() {
		return false;
	}

}
