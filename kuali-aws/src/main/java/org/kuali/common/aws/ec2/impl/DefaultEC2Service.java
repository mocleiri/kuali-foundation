package org.kuali.common.aws.ec2.impl;

import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.InstanceStateName;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.status.InstanceStatusType;
import org.kuali.common.aws.ec2.model.status.InstanceStatusValue;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.kuali.common.util.condition.Condition;
import org.kuali.common.util.wait.WaitContext;
import org.kuali.common.util.wait.WaitResult;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
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
	private final WaitService service;
	private final int sleepMillis;
	private final int initialPauseMillis;
	private final int terminationTimeoutMillis;

	public static class Builder {

		// Required
		private final AWSCredentials credentials;
		private final WaitService service;

		// Optional
		private int sleepMillis = FormatUtils.getMillisAsInt("15s"); // 15 seconds
		private int initialPauseMillis = FormatUtils.getMillisAsInt("1s"); // 1 second
		private int terminationTimeoutMillis = FormatUtils.getMillisAsInt("15m"); // 15 minutes

		// These are all optional ways to customize the configuration of the AmazonEC2Client object
		private Optional<Region> region = Optional.absent(); // Every AWS account has a default region
		private Optional<String> endpoint = Optional.absent(); // Every AWS account has a default endpoint
		private Optional<ClientConfiguration> configuration = Optional.absent();
		private Optional<Integer> timeOffsetInSeconds = Optional.absent();

		// Filled in by the build() method
		private AmazonEC2Client client;

		public Builder(String accessKey, String secretKey, WaitService service) {
			this(new BasicAWSCredentials(accessKey, secretKey), service);
		}

		public Builder(AWSCredentials credentials, WaitService service) {
			this.credentials = credentials;
			this.service = service;
		}

		public Builder timeOffsetInSeconds(Integer timeOffsetInSeconds) {
			this.timeOffsetInSeconds = Optional.fromNullable(timeOffsetInSeconds);
			return this;
		}

		public Builder region(Region region) {
			this.region = Optional.fromNullable(region);
			return this;
		}

		public Builder endpoint(String endpoint) {
			this.endpoint = Optional.fromNullable(endpoint);
			return this;
		}

		public Builder configuration(ClientConfiguration configuration) {
			this.configuration = Optional.fromNullable(configuration);
			return this;
		}

		public Builder sleepMillis(int sleepMillis) {
			this.sleepMillis = sleepMillis;
			return this;
		}

		public Builder initialPauseMillis(int initialPauseMillis) {
			this.initialPauseMillis = initialPauseMillis;
			return this;
		}

		public Builder terminationTimeoutMillis(int terminationTimeoutMillis) {
			this.terminationTimeoutMillis = terminationTimeoutMillis;
			return this;
		}

		public DefaultEC2Service build() {
			Assert.noNulls(service, credentials, timeOffsetInSeconds, region, endpoint, configuration);
			Assert.noNegatives(sleepMillis, initialPauseMillis, terminationTimeoutMillis);
			this.client = getClient(credentials);
			Assert.noNulls(client);
			return new DefaultEC2Service(this);
		}

		protected AmazonEC2Client getClient(AWSCredentials credentials) {
			AmazonEC2Client client = new AmazonEC2Client(credentials);
			if (timeOffsetInSeconds.isPresent()) {
				client.setTimeOffset(timeOffsetInSeconds.get());
			}
			if (region.isPresent()) {
				client.setRegion(region.get());
			}
			if (endpoint.isPresent()) {
				client.setEndpoint(endpoint.get());
			}
			if (configuration.isPresent()) {
				client.setConfiguration(configuration.get());
			}
			return client;
		}

	}

	private DefaultEC2Service(Builder builder) {
		this.client = builder.client;
		this.service = builder.service;
		this.sleepMillis = builder.sleepMillis;
		this.initialPauseMillis = builder.initialPauseMillis;
		this.terminationTimeoutMillis = builder.terminationTimeoutMillis;
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
		// Granted, this was in early 2011 and it may no longer be an issue
		// Since it generally takes a few minutes for the instance to spin up, pausing here for 1 second should be ok
		ThreadUtils.sleep(initialPauseMillis);

		// Tag the instance
		tag(instance.getInstanceId(), context.getTags());

		// Wait for the instance to come online
		waitForOnlineConfirmation(instance, context);

		// Double check that the instance is online
		Assert.isTrue(isOnline(instance.getInstanceId()), "Instance [" + instance.getInstanceId() + "] is not online");

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
		WaitContext wc = new WaitContext.Builder(terminationTimeoutMillis).sleepMillis(sleepMillis).initialPauseMillis(initialPauseMillis).build();
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instanceId, InstanceStateName.TERMINATED.getValue() };
		logger.info("Waiting up to {} for [{}] to terminate", args);
		Condition condition = new InstanceStateCondition(this, instanceId, InstanceStateName.TERMINATED);
		WaitResult result = service.wait(wc, condition);
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

	protected void preventTermination(String instanceId, boolean preventTermination) {
		Assert.noBlanks(instanceId);
		ModifyInstanceAttributeRequest request = new ModifyInstanceAttributeRequest();
		request.withInstanceId(instanceId);

		// EC2 instances can normally be terminated by a single API call
		// Disabling API termination forces 2 API calls. (1 to enable termination, and a 2nd one to actually terminate the instance)
		request.withDisableApiTermination(preventTermination);

		client.modifyInstanceAttribute(request);
	}

	@Override
	public String getStatus(String instanceId, InstanceStatusType type, String statusName) {
		List<InstanceStatus> statuses = getStatusList(instanceId);
		return getStatus(statuses, type, statusName);
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

	protected Instance waitForOnlineConfirmation(Instance instance, LaunchInstanceContext context) {
		InstanceStateName running = InstanceStateName.RUNNING;
		WaitContext wc = new WaitContext.Builder(context.getTimeoutMillis()).sleepMillis(sleepMillis).initialPauseMillis(initialPauseMillis).build();
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instance.getInstanceId(), running.getValue() };
		logger.info("Waiting up to {} for [{}] to come online", args);
		Condition online = new IsOnlineCondition(this, instance.getInstanceId());
		WaitResult result = service.wait(wc, online);
		Object[] resultArgs = { instance.getInstanceId(), FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] is now online - {}", resultArgs);
		return getInstance(instance.getInstanceId());
	}

	@Override
	public boolean isOnline(String instanceId) {
		return new IsOnlineCondition(this, instanceId).isTrue();
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
		return rir;
	}

	public WaitService getService() {
		return service;
	}

	public int getSleepMillis() {
		return sleepMillis;
	}

	public int getInitialPauseMillis() {
		return initialPauseMillis;
	}

	public int getTerminationTimeoutMillis() {
		return terminationTimeoutMillis;
	}

}
