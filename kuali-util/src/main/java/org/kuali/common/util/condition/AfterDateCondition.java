package org.kuali.common.util.condition;

import java.util.Date;

import org.kuali.common.util.Assert;

public final class AfterDateCondition implements Condition {

	public AfterDateCondition(Date date) {
		Assert.noNulls(date);
		this.date = date;
	}

	private final Date date;

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() > date.getTime();
	}

	public Date getDate() {
		return date;
	}

}
