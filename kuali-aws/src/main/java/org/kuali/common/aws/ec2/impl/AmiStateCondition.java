package org.kuali.common.aws.ec2.impl;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.util.condition.Condition;

import com.amazonaws.services.ec2.model.Image;

/**
 * 
 */
public final class AmiStateCondition implements Condition {

	public AmiStateCondition(EC2Service service, String imageId, String state) {
		this.service = checkNotNull(service, "service");
		this.imageId = checkNotBlank(imageId, "imageId");
		this.state = checkNotBlank(state, "state");
	}

	private final EC2Service service;
	private final String imageId;
	private final String state;

	@Override
	public boolean isTrue() {
		Image image = service.getImage(imageId);
		return this.state.equals(image.getState());
	}

}
