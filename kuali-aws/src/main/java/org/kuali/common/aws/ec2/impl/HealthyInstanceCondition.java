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

	private boolean stateOk = false;
	private boolean logState = true;

	@Override
	public boolean isTrue() {

		if (!stateOk) {
			stateOk = state.isTrue();
			return stateOk;
		}

		if (logState) {
			logger.info("[{}] is now '{}'.  Now just waiting for it to become reachable.", state.getInstanceId(), state.getTargetState().getValue());
			logState = false;
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
