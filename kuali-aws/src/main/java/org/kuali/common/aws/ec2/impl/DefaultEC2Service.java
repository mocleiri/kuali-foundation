package org.kuali.common.aws.ec2.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceContext;
import org.kuali.common.aws.ec2.model.WaitCondition;
import org.kuali.common.aws.ec2.model.WaitResult;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

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
		Reservation reservation = reservations.get(0);
		List<Instance> instances = reservation.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		Instance instance = instances.get(0);
		logger.debug("Retrieved Instance: [{}]", instance.getInstanceId());
		return instance;
	}

	@Override
	public Instance launchInstance(LaunchInstanceContext context) {
		Instance instance = getInstance(context);
		logger.debug("Launched Instance: [{}]", instance.getInstanceId());
		if (context.getWaitCondition().isPresent()) {
			WaitCondition condition = context.getWaitCondition().get();
			wait(instance, condition);
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
		CreateTagsRequest ctr = new CreateTagsRequest(resources, tags);
		client.createTags(ctr);
	}

	protected Instance getInstance(LaunchInstanceContext context) {
		RunInstancesRequest rir = getRunInstancesRequest(context);
		RunInstancesResult result = client.runInstances(rir);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		Assert.isTrue(instances.size() == 1, "Expected exactly 1 instance but there were " + instances.size() + " instead");
		return instances.get(0);
	}

	protected Instance wait(Instance instance, WaitCondition wc) {
		StateRetriever sr = new InstanceStateRetriever(this, instance.getInstanceId());
		Object[] args = { FormatUtils.getTime(wc.getTimeoutMillis()), instance.getInstanceId(), wc.getState() };
		logger.info("Waiting up to {} for [{}] to reach the state [{}]", args);
		WaitResult result = waitForState(sr, wc);
		Object[] resultArgs = { instance.getInstanceId(), wc.getState(), FormatUtils.getTime(result.getElapsed()) };
		logger.info("[{}] reached the state [{}] after {}", resultArgs);
		return getInstance(instance.getInstanceId());
	}

	protected WaitResult waitForState(StateRetriever retriever, WaitCondition wc) {
		long start = System.currentTimeMillis();
		long timeout = start + wc.getTimeoutMillis();
		// Wait a little bit before we query AWS for state information
		// If you query immediately it can sometimes flake out
		ThreadUtils.sleep(wc.getInitialPauseMillis());
		while (true) {
			String currentState = retriever.getState();
			if (StringUtils.equals(currentState, wc.getState())) {
				return new WaitResult.Builder(currentState, start, System.currentTimeMillis() - start).build();
			}
			long now = System.currentTimeMillis();
			Assert.isTrue(now <= timeout, "Timed out waiting for state [" + wc.getState() + "]");
			String remaining = FormatUtils.getTime(timeout - now);
			String elapsed = FormatUtils.getTime(now - start);
			Object[] args = { currentState, elapsed, remaining };
			logger.info("[state: {}  elapsed: {}  timeout: {}]", args);
			ThreadUtils.sleep(wc.getSleepMillis());
		}
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
