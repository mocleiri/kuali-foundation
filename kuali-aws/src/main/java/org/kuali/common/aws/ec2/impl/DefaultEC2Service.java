package org.kuali.common.aws.ec2.impl;

import java.util.Collections;
import java.util.List;

import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.model.LaunchInstanceRequest;
import org.kuali.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

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
	public Instance launchInstance(LaunchInstanceRequest request) {
		RunInstancesRequest rir = getRunInstancesRequest(request);
		RunInstancesResult result = client.runInstances(rir);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		Assert.isTrue(instances.size() == 1);
		Instance instance = instances.get(0);
		logger.debug("Launched Instance: [{}]", instance.getInstanceId());
		return instance;
	}

	protected void createTags(Instance instance, LaunchInstanceRequest request) {
		if (request.getTags().size() == 0) {
			return;
		}
		List<String> resources = Collections.singletonList(instance.getInstanceId());

		CreateTagsRequest ctr = new CreateTagsRequest(resources, request.getTags());
		ctr.setResources(Collections.singletonList(instance.getInstanceId()));
		ctr.setTags(request.getTags());
		client.createTags(ctr);
	}

	protected RunInstancesRequest getRunInstancesRequest(LaunchInstanceRequest request) {
		RunInstancesRequest rir = new RunInstancesRequest();
		rir.setMaxCount(1);
		rir.setMinCount(1);
		rir.setImageId(request.getAmi());
		rir.setKeyName(request.getKey());
		rir.setSecurityGroups(request.getSecurityGroups());
		return rir;
	}

}
