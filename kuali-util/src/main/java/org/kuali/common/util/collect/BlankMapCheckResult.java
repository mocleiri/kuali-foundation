package org.kuali.common.util.collect;

import com.google.common.base.Preconditions;

public class BlankMapCheckResult {

	public BlankMapCheckResult(int blankKeyCount, int blankValueCount) {
		Preconditions.checkArgument(blankKeyCount >= 0, "'blankKeyCount' can't be negative");
		Preconditions.checkArgument(blankValueCount >= 0, "'blankValueCount' can't be negative");
		this.blankKeyCount = blankKeyCount;
		this.blankValueCount = blankValueCount;
	}

	public int getBlankKeyCount() {
		return blankKeyCount;
	}

	public int getBlankValueCount() {
		return blankValueCount;
	}

	private final int blankKeyCount;
	private final int blankValueCount;

}
