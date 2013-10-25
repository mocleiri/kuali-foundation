package org.kuali.common.aws.ec2.api;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RunInstancesRequest;

public interface EC2Service {

	public Instance launchSingleInstance(RunInstancesRequest request);

}
