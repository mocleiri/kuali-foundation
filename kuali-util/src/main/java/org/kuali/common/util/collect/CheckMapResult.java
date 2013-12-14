package org.kuali.common.util.collect;

import com.google.common.base.Preconditions;

public class CheckMapResult {

	public CheckMapResult(int blankKeyCount, int blankValueCount) {
		Preconditions.checkArgument(blankKeyCount >= 0, "'blankKeyCount' can't be negative");
		Preconditions.checkArgument(blankValueCount >= 0, "'blankValueCount' can't be negative");
		this.badKeys = blankKeyCount;
		this.badValues = blankValueCount;
	}

	public int getBadKeys() {
		return badKeys;
	}

	public int getBadValues() {
		return badValues;
	}

	private final int badKeys;
	private final int badValues;

}
