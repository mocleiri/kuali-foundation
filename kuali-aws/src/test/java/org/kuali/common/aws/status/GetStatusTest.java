package org.kuali.common.aws.status;

import java.util.List;

import org.junit.Test;
import org.kuali.common.aws.ec2.api.EC2Service;
import org.kuali.common.aws.ec2.impl.DefaultEC2Service;
import org.kuali.common.aws.ec2.model.EC2ServiceContext;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.wait.DefaultWaitService;
import org.kuali.common.util.wait.WaitService;
import org.slf4j.Logger;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.model.Instance;
import com.google.common.collect.Lists;

public class GetStatusTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		try {
			List<AWSCredentials> creds = Auth.getCredentials();
			logger.info(String.format("Located %s sets of credentials", creds.size()));
			WaitService ws = new DefaultWaitService();
			List<Instance> instances = Lists.newArrayList();
			for (AWSCredentials credentials : creds) {
				EC2ServiceContext context = EC2ServiceContext.create(credentials);
				EC2Service service = new DefaultEC2Service(context, ws);
				instances.addAll(service.getInstances());
				logger.info(String.format("Located %s instances for %s", instances.size(), credentials.getAWSAccessKeyId()));
			}
			for (Instance instance : instances) {
				instance.getTags();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
