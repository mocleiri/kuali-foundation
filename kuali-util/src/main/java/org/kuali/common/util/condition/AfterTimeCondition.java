package org.kuali.common.util.condition;

import com.google.common.base.Preconditions;

/**
 * @deprecated
 */
@Deprecated
public final class AfterTimeCondition implements Condition {

	public AfterTimeCondition(long targetTimeInMillis) {
		Preconditions.checkArgument(targetTimeInMillis >= 0, "'targetTimeInMillis' must be >= 0");
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
