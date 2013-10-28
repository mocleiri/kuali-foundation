package org.kuali.common.aws.ec2.api;

import java.util.List;

import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.Reachability;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

/**
 * <p>
 * This service provides a layer of abstraction around the EC2 API calls provided by Amazon intended to be useful for common tasks.
 * </p>
 * 
 * <p>
 * For example, launchInstance() creates a single EC2 instance but waits until Amazon has confirmed that the instance is online and functioning.
 * </p>
 */
public interface EC2Service {

	/**
	 * Launch a single Amazon EC2 instance and wait until Amazon has confirmed that the instance is online and functioning.
	 */
	public Instance launchInstance(LaunchInstanceContext context);

	/**
	 * Terminate a single Amazon EC2 instance and wait until Amazon has verified that the instance has been terminated.
	 */
	public void terminateInstance(String instanceId);

	/**
	 * Set a flag that prevent's an Amazon EC2 instance from being terminated
	 * 
	 * @see allowTermination
	 */
	public void preventTermination(String instanceId);

	/**
	 * Set a flag that allows an Amazon EC2 instance to be terminated. This does not terminate the instance. It just makes it possible for the instance to be terminated at some
	 * later point in time.
	 * 
	 * @see preventTermination
	 */
	public void allowTermination(String instanceId);

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
