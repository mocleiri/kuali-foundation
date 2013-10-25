package org.kuali.common.aws.ec2.api;

import java.util.List;

import org.kuali.common.aws.ec2.model.LaunchInstanceRequest;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

public interface EC2Service {

	public Instance launchInstance(LaunchInstanceRequest request);

	public Instance getInstance(String id);

	public void tag(String resourceId, List<Tag> tags);

}
