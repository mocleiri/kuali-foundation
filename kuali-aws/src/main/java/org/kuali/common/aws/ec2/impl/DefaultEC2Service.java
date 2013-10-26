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

public final class DefaultEC2Service implements EC2Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultEC2Service.class);

	private final AmazonEC2Client client;
	private final WaitService service;

	public DefaultEC2Service(AWSCredentials credentials, WaitService service) {
		Assert.noNulls(credentials, service);
		this.client = new AmazonEC2Client(credentials);
		this.service = service;
	}

	public DefaultEC2Service(String accessKey, String secretKey, WaitService service) {
		this(new BasicAWSCredentials(accessKey, secretKey), service);
	}

	@Override
	public Instance getInstance(String id) {
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		request.setInstanceIds(Collections.singletonList(id));
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
		String system = getRequiredSystemStatus(list, Reachability.STATUS_NAME);
		String instance = getRequiredInstanceStatus(list, Reachability.STATUS_NAME);
		return new Reachability(system, instance);
	}

	protected String getRequiredSystemStatus(List<InstanceStatus> list, String name) {
		for (InstanceStatus element : list) {
			InstanceStatusSummary summary = element.getSystemStatus();
			String detail = getStatusDetail(summary, name);
			if (detail != null) {
				return detail;
			}
		}
		throw new IllegalStateException("Unable to locate status for [" + name + "]");
	}

	protected String getRequiredInstanceStatus(List<InstanceStatus> list, String name) {
		for (InstanceStatus element : list) {
			InstanceStatusSummary summary = element.getInstanceStatus();
			String detail = getStatusDetail(summary, name);
			if (detail != null) {
				return detail;
			}
		}
		throw new IllegalStateException("Unable to locate status for [" + name + "]");
	}

	protected String getStatusDetail(InstanceStatusSummary summary, String name) {
		List<InstanceStatusDetails> details = summary.getDetails();
		for (InstanceStatusDetails detail : details) {
			if (name.equals(detail.getName())) {
				return detail.getStatus();
			}
		}
		return null;
	}

	@Override
	public Instance launchInstance(LaunchInstanceContext context) {
		Instance instance = getInstance(context);
		if (context.getWaitContext().isPresent()) {
			wait(instance, context.getWaitContext().get(), context.getRequiredState());
		}
		tag(instance.getInstanceId(), context.getTags());
		return instance;
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

	protected Instance wait(Instance instance, WaitContext context, InstanceStateEnum requiredState) {
		Object[] args = { FormatUtils.getTime(context.getTimeoutMillis()), instance.getInstanceId(), requiredState.getValue() };
		logger.info("Waiting up to {} for [{}] to reach the state [{}]", args);
		Condition condition = new InstanceStateCondition(this, instance.getInstanceId(), requiredState);
		WaitResult result = service.wait(context, condition);
		Object[] resultArgs = { instance.getInstanceId(), requiredState.getValue(), FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] reached the state [{}] in {}", resultArgs);
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

}
