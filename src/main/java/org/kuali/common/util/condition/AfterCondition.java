package org.kuali.common.util.condition;

import java.util.Date;

public final class AfterCondition extends AbstractDateCondition {

	public AfterCondition(Date date) {
		this(date.getTime());
	}

	public AfterCondition(long millis) {
		super(millis);
	}

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() > getMillis();
	}

}
