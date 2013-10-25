package org.kuali.common.aws.ec2.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.api.StateRetriever;
import org.kuali.common.aws.ec2.model.LaunchInstanceRequest;
import org.kuali.common.aws.ec2.model.WaitControl;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Placement;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

public final class DefaultEC2Service implements EC2Service {

	private static final Logger logger = LoggerFactory.getLogger(DefaultEC2Service.class);

	private final AmazonEC2Client client;

	public DefaultEC2Service(AWSCredentials credentials) {
		Assert.noNulls(credentials);
		this.client = new AmazonEC2Client(credentials);
	}

	public DefaultEC2Service(String accessKey, String secretKey) {
		this(new BasicAWSCredentials(accessKey, secretKey));
	}

	@Override
	public Instance getInstance(String id) {
		DescribeInstancesRequest request = new DescribeInstancesRequest();
		request.setInstanceIds(Collections.singletonList(id));
		DescribeInstancesResult result = client.describeInstances(request);
		List<Reservation> reservations = result.getReservations();
		Assert.isTrue(reservations.size() == 1, "Expected exactly 1 reservation but there were " + reservations.size() + " instead");
		Reservation r = reservations.get(0);
		List<Instance> instances = r.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		Instance instance = instances.get(0);
		logger.debug("Retrieved Instance: [{}]", instance.getInstanceId());
		return instance;
	}

	@Override
	public Instance launchInstance(LaunchInstanceRequest request) {
		Instance instance = getInstance(request);
		logger.debug("Launched Instance: [{}]", instance.getInstanceId());
		if (request.getWaitControl().isPresent()) {
			wait(instance, request.getWaitControl().get());
		}
		tag(instance.getInstanceId(), request.getTags());
		return instance;
	}

	protected Instance getInstance(LaunchInstanceRequest request) {
		RunInstancesRequest rir = getRunInstancesRequest(request);
		RunInstancesResult result = client.runInstances(rir);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		return instances.get(0);
	}

	@Override
	public void tag(String resourceId, List<Tag> tags) {
		Assert.noBlanks(resourceId);
		Assert.noNulls(tags);
		List<String> resources = Collections.singletonList(resourceId);
		CreateTagsRequest ctr = new CreateTagsRequest(resources, tags);
		client.createTags(ctr);
	}

	protected Instance wait(Instance instance, WaitControl wc) {
		StateRetriever sr = new InstanceStateRetriever(this, instance.getInstanceId());
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instance.getInstanceId(), wc.getState() };
		logger.info("Waiting up to {} for [{}] to reach the state [{}]", args);
		waitForState(sr, wc);
		return getInstance(instance.getInstanceId());
	}

	protected void waitForState(StateRetriever retriever, WaitControl wc) {
		long now = System.currentTimeMillis();
		long timeout = now + wc.getTimeoutMillis();
		// Wait a little bit before we query AWS for state information
		// If you query immediately it can sometimes flake out
		ThreadUtils.sleep(wc.getInitialPauseMillis());
		while (true) {
			String currentState = retriever.getState();
			if (StringUtils.equals(currentState, wc.getState())) {
				break;
			}
			now = System.currentTimeMillis();
			Assert.isTrue(now <= timeout, "Timed out waiting for state [" + wc.getState() + "]");
			String remaining = FormatUtils.getTime(timeout - now);
			logger.info("[{}] - remaining {}", currentState, remaining);
			ThreadUtils.sleep(wc.getSleepMillis());
		}
	}

	protected RunInstancesRequest getRunInstancesRequest(LaunchInstanceRequest request) {
		RunInstancesRequest rir = new RunInstancesRequest();
		rir.setMaxCount(1);
		rir.setMinCount(1);
		rir.setImageId(request.getAmi());
		rir.setKeyName(request.getKey());
		rir.setSecurityGroups(request.getSecurityGroups());
		if (request.getAvailabilityZone().isPresent()) {
			String zone = request.getAvailabilityZone().get();
			Placement placement = new Placement(zone);
			rir.setPlacement(placement);
		}
		return rir;
	}

}
