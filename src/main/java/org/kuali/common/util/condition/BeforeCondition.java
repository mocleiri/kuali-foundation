package org.kuali.common.util.condition;

import java.util.Date;

import com.google.common.base.Preconditions;

public final class BeforeCondition implements Condition {

	public BeforeCondition(Date date) {
		this(date.getTime());
	}

	public BeforeCondition(long millis) {
		Preconditions.checkArgument(millis >= 0, "'millis' must be >= 0");
		this.millis = millis;
	}

	private final long millis;

	@Override
	public boolean isTrue() {
		return System.currentTimeMillis() < millis;
	}

	public long getMillis() {
		return millis;
	}

	public Date getDate() {
		return new Date(millis);
	}

}
