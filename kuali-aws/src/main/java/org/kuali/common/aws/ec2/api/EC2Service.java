package org.kuali.common.aws.ec2.api;

import org.kuali.common.aws.ec2.model.LaunchInstanceRequest;

import com.amazonaws.services.ec2.model.Instance;

public interface EC2Service {

	public Instance launchInstance(LaunchInstanceRequest request);

	public Instance getInstance(String instanceId);

}
