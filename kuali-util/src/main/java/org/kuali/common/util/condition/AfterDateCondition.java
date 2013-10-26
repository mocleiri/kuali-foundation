package org.kuali.common.util.condition;

import java.util.Date;

import org.kuali.common.util.Assert;

public final class AfterDateCondition implements Condition {

	public AfterDateCondition(Date date) {
		Assert.noNulls(date);
		this.targetDate = date;
	}

	private final Date targetDate;

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() > targetDate.getTime();
	}

	public Date getTargetDate() {
		return targetDate;
	}

}
