package org.kuali.common.aws.ec2;

import java.util.List;

import org.kuali.common.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public final class EC2Service {

	private static final Logger logger = LoggerFactory.getLogger(EC2Service.class);

	private final AmazonEC2Client client;

	public EC2Service(AWSCredentials credentials) {
		Assert.noNulls(credentials);
		this.client = new AmazonEC2Client(credentials);
	}

	public EC2Service(String accessKey, String secretKey) {
		this(new BasicAWSCredentials(accessKey, secretKey));
	}

	public Instance getSingleEC2Instance(RunInstancesRequest request) {
		RunInstancesResult result = client.runInstances(request);
		Reservation r = result.getReservation();
		List<Instance> instances = r.getInstances();
		return instances.get(0);
	}

}
