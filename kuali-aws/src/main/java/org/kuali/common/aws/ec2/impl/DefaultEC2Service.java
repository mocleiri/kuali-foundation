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

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
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

public final class DefaultEC2Service implements EC2Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultEC2Service.class);

	private final AmazonEC2Client client;
	private final WaitService service;
	private final int launchSleepMillis;

	public static class Builder {

		// Required
		private final AWSCredentials credentials;
		private final WaitService service;
		private final AmazonEC2Client client;

		// Optional
		private int launchSleepMillis = FormatUtils.getMillisAsInt("10s"); // 10 seconds

		public Builder(String accessKey, String secretKey, WaitService service) {
			this(new BasicAWSCredentials(accessKey, secretKey), service);
		}

		public Builder(AWSCredentials credentials, WaitService service) {
			this.credentials = credentials;
			this.client = new AmazonEC2Client(credentials);
			this.service = service;
		}

		public Builder launchSleepMillis(int launchSleepMillis) {
			this.launchSleepMillis = launchSleepMillis;
			return this;
		}

		public DefaultEC2Service build() {
			Assert.noNulls(client, service, credentials);
			Assert.notNegative(launchSleepMillis);
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
		String system = getSystemStatus(list, Reachability.STATUS_NAME);
		String instance = getInstanceStatus(list, Reachability.STATUS_NAME);
		return new Reachability(system, instance);
	}

	protected String getSystemStatus(List<InstanceStatus> statuses, String name) {
		for (InstanceStatus status : statuses) {
			InstanceStatusSummary summary = status.getSystemStatus();
			Optional<String> detail = getStatusDetail(summary, name);
			if (detail.isPresent()) {
				return detail.get();
			}
		}
		return Reachability.STATUS_UNKNOWN;
	}

	protected String getInstanceStatus(List<InstanceStatus> statuses, String name) {
		for (InstanceStatus status : statuses) {
			InstanceStatusSummary summary = status.getInstanceStatus();
			Optional<String> detail = getStatusDetail(summary, name);
			if (detail.isPresent()) {
				return detail.get();
			}
		}
		return Reachability.STATUS_UNKNOWN;
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

	protected Instance getInstance(LaunchInstanceContext context) {
		RunInstancesRequest rir = getRunInstancesRequest(context);
		RunInstancesResult result = client.runInstances(rir);
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

	public AmazonEC2Client getClient() {
		return client;
	}

	public WaitService getService() {
		return service;
	}

	public int getLaunchSleepMillis() {
		return launchSleepMillis;
	}

}
