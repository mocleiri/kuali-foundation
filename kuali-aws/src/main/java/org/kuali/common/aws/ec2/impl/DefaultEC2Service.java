package org.kuali.common.aws.ec2.impl;

import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.InstanceStateName;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.RootVolume;
import org.kuali.common.aws.ec2.model.status.InstanceStatusType;
import org.kuali.common.aws.ec2.model.status.InstanceStatusValue;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.condition.Condition;
import org.kuali.common.util.wait.WaitContext;
import org.kuali.common.util.wait.WaitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceStatus;
import com.amazonaws.services.ec2.model.InstanceStatusDetails;
import com.amazonaws.services.ec2.model.InstanceStatusSummary;
import com.amazonaws.services.ec2.model.ModifyInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.google.common.base.Optional;

/**
 * This service implementation performs operations using a single set of AWS credentials on a single EC2 region.
 */
public final class DefaultEC2Service implements EC2Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultEC2Service.class);

	private final AmazonEC2Client client;
	private final DefaultEC2ServiceContext context;

	public DefaultEC2Service(DefaultEC2ServiceContext context) {
		Assert.noNulls(context);
		this.context = context;
		this.client = getClient(context);
	}

	protected AmazonEC2Client getClient(DefaultEC2ServiceContext context) {
		AmazonEC2Client client = new AmazonEC2Client(context.getCredentials());
		if (context.getTimeOffsetInSeconds().isPresent()) {
			client.setTimeOffset(context.getTimeOffsetInSeconds().get());
		}
		if (context.getRegionName().isPresent()) {
			Region region = RegionUtils.getRegion(context.getRegionName().get());
			client.setRegion(region);
		}
		if (context.getEndpoint().isPresent()) {
			client.setEndpoint(context.getEndpoint().get());
		}
		if (context.getConfiguration().isPresent()) {
			client.setConfiguration(context.getConfiguration().get());
		}
		return client;
	}

	@Override
	public Instance getInstance(String instanceId) {
		Assert.noBlanks(instanceId);
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		request.setInstanceIds(Collections.singletonList(instanceId));
		DescribeInstancesResult result = client.describeInstances(request);
		List<Reservation> reservations = result.getReservations();
		Assert.isTrue(reservations.size() == 1, "Expected exactly 1 reservation but there were " + reservations.size() + " instead");
		Reservation reservation = reservations.get(0);
		List<Instance> instances = reservation.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		Instance instance = instances.get(0);
		logger.debug("Retrieved Instance: [{}]", instance.getInstanceId());
		return instance;
	}

	@Override
	public Instance launchInstance(LaunchInstanceContext context) {
		Assert.noNulls(context);

		// Connect to AWS and ask them to run an instance
		Instance instance = issueRunInstanceRequest(context);

		// Was getting some flaky behavior from AWS without a small delay after issuing the RunInstancesRequest
		// Since it generally takes a few minutes for the instance to spin up, pausing here for 1 second should be ok
		ThreadUtils.sleep(this.context.getInitialPauseMillis());

		// Tag the instance
		tag(instance.getInstanceId(), context.getTags());

		// Wait for confirmation that the instance is online and functioning
		waitForOnlineConfirmation(instance, context);

		// Return the fully populated instance object
		return getInstance(instance.getInstanceId());
	}

	@Override
	public void allowTermination(String instanceId) {
		Assert.noBlanks(instanceId);
		preventTermination(instanceId, false);
	}

	@Override
	public void preventTermination(String instanceId) {
		Assert.noBlanks(instanceId);
		preventTermination(instanceId, true);
	}

	@Override
	public void terminateInstance(String instanceId) {
		Assert.noBlanks(instanceId);
		TerminateInstancesRequest request = new TerminateInstancesRequest();
		request.setInstanceIds(Collections.singletonList(instanceId));
		client.terminateInstances(request);
		WaitContext wc = new WaitContext.Builder(context.getTerminationTimeoutMillis()).sleepMillis(context.getSleepMillis()).initialPauseMillis(context.getInitialPauseMillis())
				.build();
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instanceId, InstanceStateName.TERMINATED.getValue() };
		logger.info("Waiting up to {} for [{}] to terminate", args);
		Condition condition = new InstanceStateCondition(this, instanceId, InstanceStateName.TERMINATED);
		WaitResult result = context.getService().wait(wc, condition);
		Object[] resultArgs = { instanceId, FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] has been terminated - {}", resultArgs);
	}

	@Override
	public void tag(String resourceId, List<Tag> tags) {
		Assert.noBlanks(resourceId);
		Assert.noNulls(tags);
		if (CollectionUtils.isEmpty(tags)) {
			return;
		}
		List<String> resources = Collections.singletonList(resourceId);
		CreateTagsRequest request = new CreateTagsRequest(resources, tags);
		client.createTags(request);
	}

	@Override
	public boolean isOnline(String instanceId) {
		return new IsOnlineCondition(this, instanceId).isTrue();
	}

	@Override
	public String getStatus(String instanceId, InstanceStatusType type, String statusName) {
		List<InstanceStatus> statuses = getStatusList(instanceId);
		return getStatus(statuses, type, statusName);
	}

	protected void preventTermination(String instanceId, boolean preventTermination) {
		Assert.noBlanks(instanceId);
		ModifyInstanceAttributeRequest request = new ModifyInstanceAttributeRequest();
		request.withInstanceId(instanceId);

		// EC2 instances can normally be terminated by a single API call
		// Disabling API termination forces 2 API calls. (1 to re-enable termination, and a 2nd one to actually terminate the instance)
		request.withDisableApiTermination(preventTermination);

		client.modifyInstanceAttribute(request);
	}

	protected List<InstanceStatus> getStatusList(String instanceId) {
		DescribeInstanceStatusRequest request = new DescribeInstanceStatusRequest();
		request.setInstanceIds(Collections.singletonList(instanceId));
		DescribeInstanceStatusResult result = client.describeInstanceStatus(request);
		return result.getInstanceStatuses();
	}

	protected String getStatus(List<InstanceStatus> statuses, InstanceStatusType type, String name) {
		for (InstanceStatus status : statuses) {
			InstanceStatusSummary summary = getSummary(status, type);
			Optional<String> detail = getStatusDetail(summary, name);
			if (detail.isPresent()) {
				return detail.get();
			}
		}
		return InstanceStatusValue.UNKNOWN.getValue();
	}

	protected InstanceStatusSummary getSummary(InstanceStatus status, InstanceStatusType type) {
		switch (type) {
		case INSTANCE:
			return status.getInstanceStatus();
		case SYSTEM:
			return status.getSystemStatus();
		default:
			throw new IllegalArgumentException("[" + type + "] is unknown");
		}
	}

	protected Optional<String> getStatusDetail(InstanceStatusSummary summary, String name) {
		List<InstanceStatusDetails> details = summary.getDetails();
		for (InstanceStatusDetails detail : details) {
			if (name.equals(detail.getName())) {
				return Optional.of(detail.getStatus());
			}
		}
		return Optional.absent();
	}

	protected Instance issueRunInstanceRequest(LaunchInstanceContext context) {
		RunInstancesRequest request = getRunInstanceRequest(context);
		RunInstancesResult result = client.runInstances(request);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		return instances.get(0);
	}

	protected void waitForOnlineConfirmation(Instance instance, LaunchInstanceContext context) {
		InstanceStateName running = InstanceStateName.RUNNING;
		WaitContext wc = new WaitContext.Builder(context.getTimeoutMillis()).sleepMillis(this.context.getSleepMillis()).initialPauseMillis(this.context.getInitialPauseMillis())
				.build();
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instance.getInstanceId(), running.getValue() };
		logger.info("Waiting up to {} for [{}] to come online", args);
		Condition online = new IsOnlineCondition(this, instance.getInstanceId());
		WaitResult result = this.context.getService().wait(wc, online);
		Object[] resultArgs = { instance.getInstanceId(), FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] is now online - {}", resultArgs);
	}

	/**
	 * Return a request that spins up exactly one instance.
	 */
	protected RunInstancesRequest getRunInstanceRequest(LaunchInstanceContext context) {
		RunInstancesRequest rir = new RunInstancesRequest();
		rir.setMaxCount(1);
		rir.setMinCount(1);
		rir.setImageId(context.getAmi());
		rir.setKeyName(context.getKeyName());
		rir.setSecurityGroups(context.getSecurityGroups());
		rir.setInstanceType(context.getType());
		rir.setDisableApiTermination(context.isPreventTermination());
		rir.setEbsOptimized(context.isEbsOptimized());
		rir.setMonitoring(context.isEnableMonitoring());
		if (context.getAvailabilityZone().isPresent()) {
			String zone = context.getAvailabilityZone().get();
			Placement placement = new Placement(zone);
			rir.setPlacement(placement);
		}
		if (context.getRootVolume().isPresent()) {
			RootVolume rootVolume = context.getRootVolume().get();
			Image ami = getAmi(context.getAmi());
			BlockDeviceMapping mapping = getRootBlockDeviceMapping(ami);
			EbsBlockDevice device = mapping.getEbs();
			if (rootVolume.getSizeInGigabytes().isPresent()) {
				int sizeInGigabytes = rootVolume.getSizeInGigabytes().get();
				device.setVolumeSize(sizeInGigabytes);
			}
			device.setDeleteOnTermination(rootVolume.isDeleteOnTermination());
			List<BlockDeviceMapping> mappings = Collections.singletonList(mapping);
			rir.setBlockDeviceMappings(mappings);
		}
		return rir;
	}

	protected BlockDeviceMapping getRootBlockDeviceMapping(Image image) {
		String rootDeviceName = image.getRootDeviceName();
		List<BlockDeviceMapping> mappings = image.getBlockDeviceMappings();
		for (BlockDeviceMapping mapping : mappings) {
			String deviceName = mapping.getDeviceName();
			if (rootDeviceName.equals(deviceName)) {
				return mapping;
			}
		}
		throw new IllegalStateException("Could not locate the root block device mapping for AMI [" + image.getImageId() + "]");
	}

	public Image getAmi(String ami) {
		Assert.noBlanks(ami);
		DescribeImagesRequest request = new DescribeImagesRequest();
		request.setImageIds(Collections.singletonList(ami));
		DescribeImagesResult result = client.describeImages(request);
		List<Image> images = result.getImages();
		Assert.isTrue(images.size() == 1, "Expected exactly 1 image but there were " + images.size() + " instead");
		return images.get(0);
	}

	public DefaultEC2ServiceContext getContext() {
		return context;
	}

}
