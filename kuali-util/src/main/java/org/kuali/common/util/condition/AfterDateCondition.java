package org.kuali.common.util.condition;

import java.util.Date;

import com.google.common.base.Preconditions;

public final class AfterDateCondition implements Condition {

	private final AfterTimeCondition condition;

	public AfterDateCondition(Date targetDate) {
		Preconditions.checkNotNull(targetDate, "'targetDate' cannot be null");
		this.condition = new AfterTimeCondition(targetDate.getTime());
	}

	@Override
	public boolean isTrue() {
		return condition.isTrue();
	}

	public Date getTargetDate() {
		return new Date(condition.getTargetTimeInMillis());
	}

}
