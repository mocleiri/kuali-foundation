package org.kuali.common.util.runonce.smart;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public final class RunOnceIndicator {

	public RunOnceIndicator(String reason, boolean runOnce) {
		Preconditions.checkArgument(!StringUtils.isBlank(reason), "Reason cannot be blank");
		this.reason = reason;
		this.runOnce = runOnce;
	}

	private final String reason;
	private final boolean runOnce;

	public String getReason() {
		return reason;
	}

	public boolean isRunOnce() {
		return runOnce;
	}

}
