package org.kuali.common.aws.ec2.api;

import java.util.List;

import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.Reachability;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

public interface EC2Service {

	/**
	 * Terminate a single Amazon EC2 instance
	 */
	public void terminateInstance(String instanceId);

	/**
	 * Launch a single Amazon EC2 instance
	 */
	public Instance launchInstance(LaunchInstanceContext context);

	/**
	 * Return an Amazon EC2 instance object given an instance id
	 */
	public Instance getInstance(String id);

	/**
	 * Create tags on the indicated Amazon EC2 resource (instance, volume, snapshot, ami, etc)
	 */
	public void tag(String resourceId, List<Tag> tags);

	/**
	 * Return an object indicating the "reachability" of an Amazon EC2 instance
	 */
	public Reachability getReachability(String instanceId);

}
