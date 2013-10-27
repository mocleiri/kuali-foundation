package org.kuali.common.aws.ec2.impl;

import org.kuali.common.util.Assert;
import org.kuali.common.util.condition.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HealthyInstanceCondition implements Condition {

	private static final Logger logger = LoggerFactory.getLogger(HealthyInstanceCondition.class);

	public HealthyInstanceCondition(InstanceStateCondition state, ReachabilityCondition status) {
		Assert.noNulls(state, status);
		this.state = state;
		this.status = status;
	}

	private final InstanceStateCondition state;
	private final ReachabilityCondition status;

	private boolean logState = true;

	@Override
	public boolean isTrue() {
		
		if (!state.isTrue()) {
			return false;
		}

		if (logState) {
			logger.info("[{}] reached state [{}]", state.getInstanceId(), state.getTargetState().getValue());
			logState = false;
		}

		return status.isTrue();
	}

}
