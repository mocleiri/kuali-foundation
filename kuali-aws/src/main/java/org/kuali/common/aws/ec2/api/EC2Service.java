package org.kuali.common.aws.ec2.api;

import java.util.List;

import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.security.KualiSecurityGroup;
import org.kuali.common.aws.ec2.model.status.InstanceStatusType;

import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Tag;

/**
 * <p>
 * This service provides a layer of abstraction around the EC2 API calls provided by Amazon that are useful for common tasks.
 * </p>
 * 
 * <p>
 * For example, launchInstance() starts a single EC2 instance and waits until Amazon has confirmed that the instance is online and functioning.
 * </p>
 */
public interface EC2Service {

	/**
	 * Launch a single Amazon EC2 instance and wait until Amazon confirms that the instance is online and functioning.
	 * 
	 * @see terminateInstance
	 */
	public Instance launchInstance(LaunchInstanceContext context);

	/**
	 * Terminate a single Amazon EC2 instance and wait until Amazon confirms that the instance has been terminated.
	 * 
	 * @see launchInstance
	 */
	public void terminateInstance(String instanceId);

	/**
	 * <p>
	 * This method returns true if the Amazon EC2 instance meets three conditions:
	 * </p>
	 * 
	 * <ol>
	 * <li>The instance is in the <code>running</code> state.</li>
	 * <li>Amazon has verified that the instance is reachable. Amazon has verified that they are able to get network packets to the instance.</li>
	 * <li>Amazon has verified that the instance's operating system is accepting traffic.</li>
	 * </ol>
	 * 
	 * <p>
	 * If this method returns false, the instance cannot be used in any meaningful way.
	 * </p>
	 */
	public boolean isOnline(String instanceId);

	/**
	 * Set a flag that prevent's an Amazon EC2 instance from being terminated.
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
	 * Return an Amazon EC2 instance object given an instance id.
	 */
	public Instance getInstance(String instanceId);

	/**
	 * Create tags on the indicated Amazon EC2 resource (instance, volume, snapshot, ami, etc).
	 */
	public void tag(String resourceId, List<Tag> tags);

	/**
	 * Return a list containing the names of all the security groups.
	 */
	public List<String> getSecurityGroupNames();

	/**
	 * Create a new security group (it must not exist yet)
	 */
	public void createSecurityGroup(KualiSecurityGroup group);

	/**
	 * <p>
	 * Each Amazon EC2 instance has a list of statuses associated with it.
	 * </p>
	 * 
	 * <p>
	 * Each status has a name and value and is associated with either the ability of Amazon's internal systems to determine that status (external to the instance itself) or with
	 * the status of something going on internally to the instance.
	 * </p>
	 * 
	 * <p>
	 * A SYSTEM status is analogous to saying "is powered on", or "is connected to the network", but says nothing about the state of any software running on the instance.
	 * </p>
	 * 
	 * <p>
	 * An INSTANCE status indicates something about the state of software running on the instance, aka "is operating system running"
	 * </p>
	 */
	public String getStatus(String instanceId, InstanceStatusType type, String statusName);

}
