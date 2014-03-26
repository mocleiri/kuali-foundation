package org.kuali.common.aws.ec2.impl;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.util.condition.Condition;

import com.amazonaws.services.ec2.model.Snapshot;

/**
 * 
 */
public final class SnapshotStateCondition implements Condition {

	public SnapshotStateCondition(EC2Service service, String snapshotId, String state) {
		this.service = checkNotNull(service, "service");
		this.snapshotId = checkNotBlank(snapshotId, "snapshotId");
		this.state = checkNotBlank(state, "state");
	}

	private final EC2Service service;
	private final String snapshotId;
	private final String state;

	@Override
	public boolean isTrue() {
		Snapshot snapshot = service.getSnapshot(snapshotId);
		return this.state.equals(snapshot.getState());
	}

}
