package org.kuali.common.util.condition;

import org.kuali.common.util.Assert;

public final class AfterTimeCondition implements Condition {

	public AfterTimeCondition(long targetTimeInMillis) {
		Assert.notNegative(targetTimeInMillis);
		this.targetTimeInMillis = targetTimeInMillis;
	}

	private final long targetTimeInMillis;

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() > targetTimeInMillis;
	}

	public long getTargetTimeInMillis() {
		return targetTimeInMillis;
	}

}
