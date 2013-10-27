package org.kuali.common.aws.ec2.impl;

import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.InstanceStateEnum;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.Reachability;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.condition.Condition;
import org.kuali.common.util.condition.ConditionsCondition;
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
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

/**
 * <p>
 * This service provides a layer of abstraction above the EC2 API calls provided by Amazon intended to be useful for common tasks.
 * </p>
 * 
 * <p>
 * For example, the launch instance method creates a single EC2 instance and blocks until the launched instance comes online and is ready for service.
 * </p>
 */
public final class DefaultEC2Service implements EC2Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultEC2Service.class);

	private final AmazonEC2Client client;
	private final WaitService service;
	private final int launchSleepMillis;

	public static class Builder {

		// Required
		private final AWSCredentials credentials;
		private final WaitService service;

		// Optional
		private int launchSleepMillis = FormatUtils.getMillisAsInt("10s"); // 10 seconds
		private Optional<Integer> timeOffsetInSeconds = Optional.absent();
		private Optional<Region> region = Optional.absent();
		private Optional<String> endpoint = Optional.absent();
		private Optional<ClientConfiguration> configuration = Optional.absent();

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

		public Builder launchSleepMillis(int launchSleepMillis) {
			this.launchSleepMillis = launchSleepMillis;
			return this;
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

		public DefaultEC2Service build() {
			Assert.noNulls(service, credentials, timeOffsetInSeconds, region, endpoint, configuration);
			Assert.notNegative(launchSleepMillis);
			this.client = getClient(credentials);
			Assert.noNulls(client);
			return new DefaultEC2Service(this);
		}

	}

	private DefaultEC2Service(Builder builder) {
		this.client = builder.client;
		this.service = builder.service;
		this.launchSleepMillis = builder.launchSleepMillis;
	}

	@Override
	public Instance getInstance(String instanceId) {
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
	public Reachability getReachability(String instanceId) {
		DescribeInstanceStatusRequest request = new DescribeInstanceStatusRequest();
		request.setInstanceIds(Collections.singletonList(instanceId));
		DescribeInstanceStatusResult result = client.describeInstanceStatus(request);
		List<InstanceStatus> list = result.getInstanceStatuses();
		String system = getStatus(list, Reachability.STATUS_NAME, StatusType.SYSTEM);
		String instance = getStatus(list, Reachability.STATUS_NAME, StatusType.INSTANCE);
		return new Reachability(system, instance);
	}

	@Override
	public Instance launchInstance(LaunchInstanceContext context) {
		Instance instance = getInstance(context);
		wait(instance, context);
		tag(instance.getInstanceId(), context.getTags());
		return getInstance(instance.getInstanceId());
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

	protected String getStatus(List<InstanceStatus> statuses, String name, StatusType type) {
		for (InstanceStatus status : statuses) {
			InstanceStatusSummary summary = getSummary(status, type);
			Optional<String> detail = getStatusDetail(summary, name);
			if (detail.isPresent()) {
				return detail.get();
			}
		}
		return Reachability.STATUS_UNKNOWN;
	}

	protected InstanceStatusSummary getSummary(InstanceStatus status, StatusType type) {
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

	protected Instance getInstance(LaunchInstanceContext context) {
		RunInstancesRequest request = getRunInstancesRequest(context);
		RunInstancesResult result = client.runInstances(request);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		return instances.get(0);
	}

	protected Instance wait(Instance instance, LaunchInstanceContext context) {
		InstanceStateEnum running = InstanceStateEnum.RUNNING;
		WaitContext wc = new WaitContext.Builder(context.getTimeoutMillis()).sleepMillis(launchSleepMillis).build();
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instance.getInstanceId(), running.getValue() };
		logger.info("Waiting up to {} for [{}] to come online", args);
		InstanceStateCondition state = new InstanceStateCondition(this, instance.getInstanceId(), running);
		IsReachableCondition status = new IsReachableCondition(this, instance.getInstanceId());
		Condition condition = new ConditionsCondition(ImmutableList.of(state, status));
		WaitResult result = service.wait(wc, condition);
		Object[] resultArgs = { instance.getInstanceId(), FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] is now online - {}", resultArgs);
		return getInstance(instance.getInstanceId());
	}

	protected RunInstancesRequest getRunInstancesRequest(LaunchInstanceContext context) {
		RunInstancesRequest rir = new RunInstancesRequest();
		rir.setMaxCount(1);
		rir.setMinCount(1);
		rir.setImageId(context.getAmi());
		rir.setKeyName(context.getKeyName());
		rir.setSecurityGroups(context.getSecurityGroups());
		rir.setInstanceType(context.getType());
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

	public int getLaunchSleepMillis() {
		return launchSleepMillis;
	}

}
