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
		this.reachable = status;
	}

	private final InstanceStateCondition state;
	private final ReachabilityCondition reachable;

	private boolean correctState = false;
	private boolean logStateChange = true;

	@Override
	public boolean isTrue() {

		if (!correctState) {
			this.correctState = state.isTrue();
		}

		if (!correctState) {
			return false;
		}

		if (correctState && logStateChange) {
			logger.info("[{}] is '{}'", state.getInstanceId(), state.getTargetState().getValue());
			this.logStateChange = false;
		}

		return reachable.isTrue();
	}

	public InstanceStateCondition getState() {
		return state;
	}

	public ReachabilityCondition getReachable() {
		return reachable;
	}

}
